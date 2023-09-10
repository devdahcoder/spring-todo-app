package com.devdahcoder.user.controller;

import com.devdahcoder.user.model.CreateUserModel;
import com.devdahcoder.user.model.UserMapperModel;
import com.devdahcoder.user.service.UserService;
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

    public UserController(UserService userService) { this.userService = userService; }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("")
    public ResponseEntity<Iterable<UserMapperModel>> findAllUsers(
            @RequestParam( defaultValue = "10", required = false, name = "limit", value = "limit" ) int limit,
            @RequestParam( defaultValue = "0", required = false, name = "offset", value = "offset" ) int offset,
            @RequestParam( defaultValue = "ASC", required = false, name = "order", value = "order" ) String order,
            @RequestParam( defaultValue = "1", required = false, name = "page", value = "page" ) int page
    ) {

        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);

    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody @Valid CreateUserModel user) {

        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);

    }

}
