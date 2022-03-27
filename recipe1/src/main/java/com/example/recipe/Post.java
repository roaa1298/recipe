package com.example.recipe;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Long userId;
    String TextContent;

    @ManyToOne
    RecipeUser recipeUser;

    public Post() {
    }

    public Post(String textContent) {
        this.TextContent = textContent;
    }

    public Long getId() {
        return id;
    }

    public String getTextContent() {
        return TextContent;
    }
}
