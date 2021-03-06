package com.example.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PostController {
    @Autowired
    RecipeUserRepository recipeUserRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/")
    public String getHomepage(HttpServletRequest request,Model model)
    {
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();

        model.addAttribute("postsList",postRepository.findAll());
        model.addAttribute("userN",username);
        return "post.html";
    }

    @PostMapping("/")
    public RedirectView addPost(String TextContent,String username){
        RecipeUser newUser=recipeUserRepository.findByUsername(username);
        Post post=new Post(TextContent,newUser);
        postRepository.save(post);
        return new RedirectView("/");
    }

    @GetMapping("/withSecret")
    public String getHomepageWithSecret(HttpServletRequest request, Model m)
    {
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();

        m.addAttribute("username", username);

        return "indexWithSecret.html";
    }


}
