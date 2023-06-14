package com.sisvime.app.repository;

import com.sisvime.app.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRecipeRepository extends JpaRepository<Recipe, Long> {
}
