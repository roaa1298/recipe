package com.example.recipe;

import javax.persistence.*;
import java.util.List;

@Entity
public class RecipeUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    String password;

    @OneToMany(mappedBy = "recipeUser")
    List<Post> posts;

    public RecipeUser() {
    }

    public RecipeUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
