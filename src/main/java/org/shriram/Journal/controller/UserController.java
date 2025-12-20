package org.shriram.Journal.controller;

import org.shriram.Journal.api.response.QuoteResponse;
import org.shriram.Journal.api.response.WeatherResponse;
import org.shriram.Journal.entity.User;
import org.shriram.Journal.repository.UserRepository;
import org.shriram.Journal.service.QuoteService;
import org.shriram.Journal.service.UserService;
import org.shriram.Journal.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private QuoteService quoteService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDB = userService.findByUserName(userName);
        userInDB.setUserName(user.getUserName());
        userInDB.setPassword(user.getPassword());
        userService.saveNewUser(userInDB);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        userRepository.deleteByUserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping
    public ResponseEntity<?> greetings() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weather = weatherService.getWeather("Bangalore");
        QuoteResponse quoteResponse = quoteService.getQuote();

        String greeting = "Hi " + authentication.getName();
        if (weather != null) {
            greeting += "\nWeather feels like: " + weather.getCurrent().getFeelslike();
        }
        if (quoteResponse != null) {
            greeting += "\nQuote: " + quoteResponse.toString();
        }

        return ResponseEntity.ok(greeting);
    }





}
