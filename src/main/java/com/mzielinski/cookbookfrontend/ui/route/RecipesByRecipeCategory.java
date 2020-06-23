package com.mzielinski.cookbookfrontend.ui.route;

import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.RecipeCategoryClient;
import com.mzielinski.cookbookfrontend.client.RecipeClient;
import com.mzielinski.cookbookfrontend.domain.dto.RecipeDto;
import com.mzielinski.cookbookfrontend.exception.ProductGroupNotFoundException;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "recipes/recipecategory", layout = NavbarMenu.class)
public class RecipesByRecipeCategory extends VerticalLayout implements HasUrlParameter<Long> {

    private RecipeCategoryClient recipeCategoryClient = new RecipeCategoryClient();
    private RecipeClient recipeClient = new RecipeClient();
    private Grid<RecipeDto> grid = new Grid<>(RecipeDto.class);

    @Override
    public void setParameter(BeforeEvent event, Long parameter) throws ProductGroupNotFoundException {

        Button allRecipeCategoriesButton = new Button("Back to recipe categories");
        allRecipeCategoriesButton.addClickListener(click -> {
            allRecipeCategoriesButton.getUI().ifPresent(ui -> ui.navigate("recipecategories/"));
        });

        H2 heading = new H2("RECIPE CATEGORY: " + recipeCategoryClient.getRecipeCategory(parameter).getRecipeCategoryName());

        HorizontalLayout container = new HorizontalLayout(heading, allRecipeCategoriesButton);
        container.setAlignItems(Alignment.BASELINE);
        add(container);


        List<RecipeDto> recipes = recipeClient.getRecipesByCategory(parameter);

        if (recipes.size() > 0) {
            add(grid);
            setSizeFull();

            grid.setColumns("recipeId", "recipeName", "recipeDetails", "preparationTime", "recipeCategoryId", "userId");

            grid.setItems(recipes);

            grid.addComponentColumn(recipeDto -> {
                Button button = new Button("See the recipe");
                button.addClickListener(click -> {
                    button.getUI().ifPresent(ui -> ui.navigate("recipes/" + recipeDto.getRecipeId()));
                });
                return button;
            });
        } else {
            Text empty = new Text("No recipes found in this category");
            add(empty);
        }


    }
}
