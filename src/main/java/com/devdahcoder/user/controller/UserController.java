package com.devdahcoder.user.controller;

import com.devdahcoder.response.CreateApiResponse;
import com.devdahcoder.user.model.AuthenticateUserModel;
import com.devdahcoder.user.model.CreateUserModel;
import com.devdahcoder.user.model.UserMapperModel;
import com.devdahcoder.response.ListApiResponse;
import com.devdahcoder.user.service.UserService;
import com.devdahcoder.util.ApiUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final HttpServletRequest httpServletRequest;

    public UserController(UserService userService, HttpServletRequest httpServletRequest) {

        this.userService = userService;
        this.httpServletRequest = httpServletRequest;

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public ResponseEntity<ListApiResponse<UserMapperModel>> findAllUsers(
            @RequestParam(defaultValue = "10", required = false, name = "limit", value = "10") int limit,
            @RequestParam(defaultValue = "0", required = false, name = "offset", value = "0") int offset,
            @RequestParam(defaultValue = "ASC", required = false, name = "order", value = "ASC") String order,
            @RequestParam(defaultValue = "1", required = false, name = "page", value = "1") int page
    ) {

        List<UserMapperModel> allUsers = userService.findAllUsers(order, limit, ApiUtil.calculateOffSet(limit, page));

        int totalPage = ApiUtil.calculatePagination(userService.countUser(), limit);

        ListApiResponse<UserMapperModel> listApiResponse = new ListApiResponse<>(page,
                allUsers, limit, offset, userService.countUser(), totalPage, order, ApiUtil.hasNextPage(page, totalPage),
                allUsers.size(), "All users", ApiUtil.hasPreviousPage(page));

        return new ResponseEntity<>(listApiResponse, HttpStatus.OK);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<CreateApiResponse<CreateUserModel>> createUser(@RequestBody @Valid CreateUserModel user) {

        CreateUserModel createdUser = userService.createUser(user);

        CreateApiResponse<CreateUserModel> createApiResponse = new CreateApiResponse<>(
                HttpStatus.CREATED,
                Map.of("user", createdUser),
                Map.of("self", httpServletRequest.getServletPath())
        );

        return new ResponseEntity<>(createApiResponse, HttpStatus.CREATED);

    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody @Valid AuthenticateUserModel authenticateUserModel) {

        return new ResponseEntity<>(userService.authenticateUser(authenticateUserModel), HttpStatus.ACCEPTED);

    }

    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/{userId}")
    public ResponseEntity<Optional<UserMapperModel>> getUserById(@PathVariable(name = "userId", required = true) UUID userId) {

        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.FOUND);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{userId}")
    public String updateUserById(@PathVariable(name = "userId", required = true) UUID userId) {
        return "";
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable(name = "userId", required = true) UUID userId) {

        return "";

    }

}
