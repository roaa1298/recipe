package com.example.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeUserRepository extends JpaRepository<RecipeUser, Long> {
    RecipeUser findByUsername(String username);
}
