package com.example.spring.controllers;

import com.example.spring.models.Comment;
import com.example.spring.models.Favorite;
import com.example.spring.models.Likes;
import com.example.spring.models.User;
import com.example.spring.repository.CommentRepository;
import com.example.spring.repository.FavoriteRepository;
import com.example.spring.repository.LikeRepository;
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
  private CommentRepository cr;
  
  @Autowired
  private FavoriteRepository fr;
  
  @Autowired
  private UserRepository ur;
  
  @Autowired
  private LikeRepository likeRepository;
  
  @RequestMapping(value = "/categoria", method = RequestMethod.GET)
  public String categoria(@RequestParam String id, Model model) {
    model.addAttribute("id", id);
    return "categoria";
  }
  
  @RequestMapping(value = "/filme", method = RequestMethod.GET)
  public String movie(@RequestParam String id, Model model, HttpServletRequest request) {
    List<Comment> commentList = cr.findByMovieId(Long.parseLong(id));
    List<Likes> likesList = likeRepository.findByMovieId(Long.parseLong(id));
    
    try {
      long uid = (long) request.getSession().getAttribute("id");
      Optional<User> userOp = ur.findById(uid);
      if (userOp.isPresent()) {
        Favorite fav = fr.findByUserAndMovieId(userOp.get(), Long.parseLong(id));
        model.addAttribute("favorite", fav != null);
      }
    } catch (Exception e) {
      model.addAttribute("favorite", false);
    }
    
    model.addAttribute("id", id);
    model.addAttribute("comments", commentList);
    
    
    long userId = (long) request.getSession().getAttribute("id");
    User user = ur.findById(userId).get();
    int likes = 0, dislikes = 0;
    for (Likes value : likesList) {
      if (value.getValue() == 1) likes++;
      else if (value.getValue() == -1) dislikes++;
      
      if (value.getUser().getId() == user.getId())
        model.addAttribute("liked", value.getValue());
    }
    model.addAttribute("likes", likes);
    model.addAttribute("dislikes", dislikes);
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
    cr.save(usercomment);
    return "redirect:/filme?id=" + movieid;
  }
  
  @RequestMapping(value = "/filme/favToggle", method = RequestMethod.GET)
  public String movieFavToggle(@RequestParam String movieid, HttpServletRequest request) {
    try {
      long uid = (long) request.getSession().getAttribute("id");
      Optional<User> userOp = ur.findById(uid);
      if (userOp.isPresent()) {
        Favorite fav = fr.findByUserAndMovieId(userOp.get(), Long.parseLong(movieid));
        if (fav != null) {
          fr.delete(fav);
        } else {
          Favorite newFav = new Favorite();
          newFav.setMovieId(Long.parseLong(movieid));
          newFav.setUser(userOp.get());
          fr.save(newFav);
        }
      }
    } catch (Exception e) {
      return "redirect:/filme?id=" + movieid;
    }
    
    return "redirect:/filme?id=" + movieid;
  }
  
  
  @RequestMapping(value = "/trending", method = RequestMethod.GET)
  public String trending() {
    return "trending";
  }
  
  @RequestMapping(value = "/toprated", method = RequestMethod.GET)
  public String toprated() {
    return "toprated";
  }
  
  @RequestMapping(value = "/like", method = RequestMethod.GET)
  public String like(@RequestParam String value, @RequestParam String movieId, HttpServletRequest request) {
    long userId = (long) request.getSession().getAttribute("id");
    List<Likes> likesList = likeRepository.findByMovieId(Long.parseLong(movieId));
    User user = ur.findById(userId).get();
    Likes like = null;
    for (int i = 0; i < likesList.size(); i++) {
      if (likesList.get(i).getUser().getId() == user.getId()) {
        like = likesList.get(i);
        like.setValue(Integer.parseInt(value));
        break;
      }
    }
    if (like == null) {
      like = new Likes();
      like.setValue(Integer.parseInt(value));
      like.setUser(user);
      like.setMovieId(Long.parseLong(movieId));
    }
    likeRepository.save(like);
    return "redirect:/filme?id=" + movieId;
  }
  
  @RequestMapping(value = "/favorites", method = RequestMethod.GET)
  public String favorito(Model model, HttpServletRequest request) {
    long userId = (long) request.getSession().getAttribute("id");
    User user = ur.findById(userId).get();
    List<Favorite> favoriteList = fr.findAll();
    for (int i = 0; i < favoriteList.size(); i++) {
      System.out.println(favoriteList.get(i).getMovieId());
      if (favoriteList.get(i).getUser().getId() != user.getId())
        favoriteList.remove(i);
    }
    model.addAttribute("favorites", favoriteList);
    return "favorite";
  }
}
