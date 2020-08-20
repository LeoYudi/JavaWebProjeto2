package com.example.spring.controllers;

import com.example.spring.models.User;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
  
  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
  
  @Autowired
  private UserRepository ur;
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Model model, HttpServletRequest request) {
    try {
      long id = (long) request.getSession().getAttribute("id");
      boolean session = (boolean) request.getSession().getAttribute("session");
      
      Optional<User> userOp = ur.findById(id);
      
      if (userOp.isEmpty()) {
        request.getSession().invalidate();
        return "redirect:/login";
      }
      System.out.println(session);
      if (!session)
        request.getSession().invalidate();
      
      model.addAttribute("user", userOp.get().getName());
      return "home";
      
    } catch (Exception e) {
      return "redirect:/login";
    }
  }
  
  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String register() {
    return "register";
  }
  
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String register(User user, HttpServletRequest request) {
    user.setPassword(encoder.encode(user.getPassword()));
    List<User> users = ur.findByEmail(user.getEmail());
    
    if (users.size() > 0)
      return "redirect:/register";
    
    ur.save(user);
    request.getSession().setAttribute("id", user.getId());
    return "redirect:/";
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    return "login";
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(String email, String password, boolean session, HttpServletRequest request) {
    String hash = encoder.encode(password);
    User user = ur.findByEmail(email).get(0);
    if (user == null)
      return "redirect:/login";
    
    if (!encoder.matches(password, hash))
      return "redirect:/login";
    
    request.getSession().setAttribute("id", user.getId());
    request.getSession().setAttribute("session", session);
    
    return "redirect:/";
  }
}
