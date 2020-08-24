package com.example.spring.controllers;

import com.example.spring.models.User;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
  
  private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
  private static String folder = "./src/main/resources/static/img/";
  private static String frontPath = "/img/";
  
  @Autowired
  private UserRepository ur;
  
  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String home(Model model, HttpServletRequest request) {
    try {
      long id = (long) request.getSession().getAttribute("id");
      
      Optional<User> userOp = ur.findById(id);

      
      if (!userOp.isPresent()) {
        request.getSession().invalidate();
        return "redirect:/login";
      }
      
      model.addAttribute("user", userOp.get());
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
  public String register(User user, String confirm, MultipartFile file, HttpServletRequest request) {
    if (!user.getPassword().equals(confirm))
      return "redirect:/register";

    System.out.println("aqui");
    user.setPassword(encoder.encode(user.getPassword()));
    String image;
    try {
      if (!file.isEmpty()) {
        byte[] bytes = file.getBytes();
        image = file.getOriginalFilename();
        Path path = Paths.get(folder + image);
        path.startsWith(path);
        Files.write(path, bytes);
      } else
        image = "default.jpg";
      
      List<User> users = ur.findByEmail(user.getEmail());
      if (users.size() > 0)
        return "redirect:/register";
      
      user.setImage(frontPath + image);
      ur.save(user);
      request.getSession().setAttribute("id", user.getId());
      return "redirect:/";
    } catch (IOException e) {
      e.printStackTrace();
      return "redirect:/register";
    }
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login() {
    return "login";
  }
  
  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(String email, String password, HttpServletRequest request) {
    String hash = encoder.encode(password);
    List<User> listUser = ur.findByEmail(email);
    
    if (listUser.size() != 1)
      return "redirect:/login";
    
    User user = listUser.get(0);
    
    if (!encoder.matches(password, hash))
      return "redirect:/login";
    
    request.getSession().setAttribute("id", user.getId());
    
    return "redirect:/";
  }
  
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(HttpServletRequest request) {
    request.getSession().invalidate();
    return "redirect:/login";
  }
}
