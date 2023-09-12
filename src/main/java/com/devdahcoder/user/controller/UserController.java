package com.devdahcoder.user.controller;

import com.devdahcoder.user.model.CreateUserModel;
import com.devdahcoder.user.model.UserMapperModel;
import com.devdahcoder.user.response.UserIteratableApiResponse;
import com.devdahcoder.user.service.UserService;
import com.devdahcoder.util.ApiUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public ResponseEntity<UserIteratableApiResponse> findAllUsers(
            @RequestParam(defaultValue = "10", required = false, name = "limit", value = "limit") int limit,
            @RequestParam(defaultValue = "0", required = false, name = "offset", value = "offset") int offset,
            @RequestParam(defaultValue = "ASC", required = false, name = "order", value = "order") String order,
            @RequestParam(defaultValue = "1", required = false, name = "page", value = "page") int page
    ) {

        Iterable<UserMapperModel> allUsers = userService.findAllUsers(order, limit);

        int totalPage = ApiUtil.calculatePagination(userService.countUser(), limit);

        UserIteratableApiResponse<UserMapperModel> userIteratableApiResponse = new UserIteratableApiResponse<>(page, allUsers,
                limit, offset, userService.countUser(), totalPage, order, ApiUtil.hasNextPage(page, totalPage),
                10, "All users", ApiUtil.hasPreviousPage(page));

        return new ResponseEntity<>(userIteratableApiResponse, HttpStatus.OK);

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserModel user) {

        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);

    }

}
