package com.example.spring.controllers;

import com.example.spring.models.User;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
  
  @Autowired
  private UserRepository ur;
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home() {
    return "home";
  }
  
  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String register() {
    return "register";
  }
  
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String register(User user) {
    ur.save(user);
    return "redirect:/";
  }
  
  
}
