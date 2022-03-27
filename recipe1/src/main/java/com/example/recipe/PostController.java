package com.example.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class PostController {
    @Autowired
    RecipeUserRepository recipeUserRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/")
    public String getHomepage(Model model)
    {
        model.addAttribute(postRepository.findAll());
        return "post.html";
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
