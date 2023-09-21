package com.devdahcoder.user.controller;

import com.devdahcoder.response.success.CreateApiResponseSuccess;
import com.devdahcoder.response.success.JwtTokenApiResponseSuccess;
import com.devdahcoder.user.model.AuthenticateUserModel;
import com.devdahcoder.user.model.CreateUserModel;
import com.devdahcoder.user.model.UserMapperModel;
import com.devdahcoder.response.data.ListApiResponseData;
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
    public ResponseEntity<ListApiResponseData<UserMapperModel>> findAllUsers(
            @RequestParam(defaultValue = "10", required = false, name = "limit") int limit,
            @RequestParam(defaultValue = "0", required = false, name = "offset") int offset,
            @RequestParam(defaultValue = "ASC", required = false, name = "order") String order,
            @RequestParam(defaultValue = "1", required = false, name = "page") int page
    ) {

        List<UserMapperModel> allUsers = userService.findAllUsers(order, limit, ApiUtil.calculateOffSet(limit, page));

        int totalPage = ApiUtil.calculatePagination(userService.countUser(), limit);

        ListApiResponseData<UserMapperModel> listApiResponseData = new ListApiResponseData<>(page,
                allUsers, limit, offset, userService.countUser(), totalPage, order, ApiUtil.hasNextPage(page, totalPage),
                allUsers.size(), "All users", ApiUtil.hasPreviousPage(page));

        return new ResponseEntity<>(listApiResponseData, HttpStatus.OK);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<CreateApiResponseSuccess<CreateUserModel>> createUser(@RequestBody @Valid CreateUserModel user) {

        CreateUserModel createdUser = userService.createUser(user);

        CreateApiResponseSuccess<CreateUserModel> createApiResponseSuccess = new CreateApiResponseSuccess<>(
                HttpStatus.CREATED,
                Map.of("user", createdUser),
                Map.of("self", httpServletRequest.getServletPath())
        );

        return new ResponseEntity<>(createApiResponseSuccess, HttpStatus.CREATED);

    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/login")
    public ResponseEntity<JwtTokenApiResponseSuccess> authenticateUser(@RequestBody @Valid AuthenticateUserModel authenticateUserModel) {

        String generatedJwtToken = userService.authenticateUser(authenticateUserModel);

        JwtTokenApiResponseSuccess jwtTokenApiResponseSuccess = new JwtTokenApiResponseSuccess(HttpStatus.OK.toString(), "JWT token generated successfully", generatedJwtToken);

        return new ResponseEntity<>(jwtTokenApiResponseSuccess, HttpStatus.ACCEPTED);

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
