package com.example.recipe;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String TextContent;

    @ManyToOne
    RecipeUser recipeUser;

    public Post() {
    }

    public Post(String textContent,RecipeUser recipeUser) {
        this.TextContent = textContent;
        this.recipeUser=recipeUser;
    }

    public Long getId() {
        return id;
    }

    public String getTextContent() {
        return TextContent;
    }
}
