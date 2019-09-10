package com.slh.opensourcesharing.controller;

import com.slh.opensourcesharing.model.Post;
import com.slh.opensourcesharing.service.PostList;
import com.slh.opensourcesharing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.slh.opensourcesharing.model.User;
import javax.validation.Valid;

@Controller
public class AuthenticationController
{
    @Autowired
    UserService userService;

    @Autowired
    PostList postList;

    @RequestMapping(value = { "/page/login" } , method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); //resources/templates/login.html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register"); //resources/templates/register.html
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home"); //resources/templates/home.html
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView();
        Post post = new Post();
        modelAndView.addObject("post", post);
        modelAndView.addObject("listOfPosts", postList.getAllPosts());
        modelAndView.setViewName("admin"); // resources/template/admin.html
        return modelAndView;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        // Check for the validation
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        //save the user if no binding errors
        else if(userService.isUserAlreadyPresent(user)){
            modelAndView.addObject("successMessage", "User already exists!");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User is registered successfully");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value="/admin/addPost", method = RequestMethod.GET)
    public ModelAndView showAddPost() {
        ModelAndView modelAndView = new ModelAndView();
        Post post = new Post();
        modelAndView.addObject("post", post);
        modelAndView.setViewName("addPost"); //resources/templates/admin.html
        return modelAndView;
    }

    @RequestMapping(value="/admin/addPost", method = RequestMethod.POST)
    public ModelAndView addPost(Post post) {
        ModelAndView modelAndView = new ModelAndView();
        postList.addPost(post);
        modelAndView.addObject("listOfPosts", postList.getAllPosts());
        modelAndView.setViewName("admin"); //resources/templates/admin.html
        return modelAndView;
    }

    @RequestMapping(value = "/admin/editPost/{id}", method = RequestMethod.GET)
    public ModelAndView editPost(@PathVariable("id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        Post value = postList.getPost(id);
        modelAndView.addObject("post", value);
        modelAndView.setViewName("editPost");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/editPost", method = RequestMethod.POST)
    public ModelAndView editPost(Post post) {
        ModelAndView modelAndView = new ModelAndView();
        postList.updatePost(post.getId(), post);
        modelAndView.addObject("listOfPosts", postList.getAllPosts());
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/delete-post", method = RequestMethod.GET)
    public ModelAndView deletePost(@RequestParam(name="id", required = true) long id)
    {
        ModelAndView modelAndView = new ModelAndView();
        postList.removePost(id);
        modelAndView.addObject("listOfPosts", postList.getAllPosts());
        modelAndView.setViewName("admin");
        return modelAndView;
    }
}
