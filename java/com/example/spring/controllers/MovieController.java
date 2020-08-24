package com.example.spring.controllers;

import com.example.spring.models.Comment;
import com.example.spring.models.User;
import com.example.spring.repository.CommentRepository;
import com.example.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {
  @Autowired
  private CommentRepository commentRepository;

  @Autowired
  private UserRepository ur;


  @RequestMapping(value = "/categoria", method = RequestMethod.GET)
  public String categoria(@RequestParam String id, Model model) {
    model.addAttribute("id", id);
    return "categoria";
  }
  
  @RequestMapping(value = "/filme", method = RequestMethod.GET)
  public String movie(@RequestParam String id, Model model) {
    List<Comment> commentList = commentRepository.findByMovieId(Long.parseLong(id));
    model.addAttribute("id", id);
    model.addAttribute("comments", commentList);
    return "filme";
  }

  @RequestMapping(value = "/filme", method = RequestMethod.POST)
  public String movie(String comment, String movieid, Model model, HttpServletRequest request) {
    Comment usercomment = new Comment();

    long id = (long) request.getSession().getAttribute("id");
    Optional<User> userOp = ur.findById(id);

    if (userOp.isPresent()) {
      usercomment.setComment(comment);
      usercomment.setMovieId(Long.parseLong(movieid));
      usercomment.setUser(userOp.get());
    }
    commentRepository.save(usercomment);
    return "filme";
  }

  @RequestMapping(value = "/trending", method = RequestMethod.GET)
  public String trending() {
    return "trending";
  }

  @RequestMapping(value = "/toprated", method = RequestMethod.GET)
  public String toprated() {
    return "toprated";
  }
}
