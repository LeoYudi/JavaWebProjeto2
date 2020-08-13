package com.example.spring.controllers;

import com.example.spring.models.User;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {
  
  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
  
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
    user.setPassword(encoder.encode(user.getPassword()));
    List<User> users = ur.findByEmail(user.getEmail());
    if (users.size() > 0)
      return "redirect:/";
    ur.save(user);
    return "redirect:/";
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    return "login";
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(String email, String password, boolean session) {
    String hash = encoder.encode(password);
    List<User> users = ur.findByEmail(email);
    if (users.size() == 0)
      return "redirect:/login";
    
    if (!encoder.matches(password, hash))
      return "redirect:/login";
    
    
    return "redirect:/";
  }
}
