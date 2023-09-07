package com.devdahcoder.user.controller;

import com.devdahcoder.user.model.UserMapperModel;
import com.devdahcoder.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping("")
    public ResponseEntity<Iterable<UserMapperModel>> findAllUsers() {

        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);

    }

//    @PostMapping("/add")
//    public ResponseEntity<UserMapperModel> addUser() {
//
//        return new ResponseEntity<>(userService.addUser(), )
//
//    }

}
