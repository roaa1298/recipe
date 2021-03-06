package com.example.recipe;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthenticationController {
    @Autowired
    RecipeUserRepository recipeUserRepository;

    @Autowired
    PostRepository postRepository;

    @GetMapping("/login")
    public String getLoginPage()
    {
        return "/login.html";
    }

    @GetMapping("/loginWithSecret")
    public String getLoginPageWithSecret()
    {
        return "/loginWithSecret.html";
    }

    @GetMapping("/signup")
    public String getSignupPage()
    {
        return "/Signup.html";
    }

    @PostMapping("/login")
    public RedirectView logInUser(HttpServletRequest request,String username, String password)
    {
        RecipeUser userFromDb = recipeUserRepository.findByUsername(username);
        if ((userFromDb == null) || (!BCrypt.checkpw(password, userFromDb.password))) {
            return new RedirectView("/login");
        }
        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        return new RedirectView("/");
    }

    @PostMapping("/loginWithSecret")
    public RedirectView logInUserWithSecret(HttpServletRequest request, String username, String password)
    {
        RecipeUser userFromDb = recipeUserRepository.findByUsername(username);
        if ((userFromDb == null)
                || (!BCrypt.checkpw(password, userFromDb.password)))
        {
            return new RedirectView("/loginWithSecret");
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        return new RedirectView("/withSecret");
    }

    @PostMapping("/logoutWithSecret")
    public RedirectView logOutUserWithSecret(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.invalidate();

        return new RedirectView("/loginWithSecret");
    }

    @PostMapping("/signup")
    public RedirectView signUpUser(Model m, String username, String password)
    {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        RecipeUser newChatUser = new RecipeUser(username, hashedPassword);

        recipeUserRepository.save(newChatUser);
        return new RedirectView("/login");
    }
}
