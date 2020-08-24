package com.example.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MovieController {
  @RequestMapping(value = "/categoria", method = RequestMethod.GET)
  public String categoria(@RequestParam String id, Model model) {
    model.addAttribute("id", id);
    return "categoria";
  }
  
  @RequestMapping(value = "/filme", method = RequestMethod.GET)
  public String movie(@RequestParam String id, Model model) {
    model.addAttribute("id", id);
    return "filme";
  }

  @RequestMapping(value = "/trending", method = RequestMethod.GET)
  public String trending() {
    return "trending";
  }
}
