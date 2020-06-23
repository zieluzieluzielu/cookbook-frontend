package com.mzielinski.cookbookfrontend.ui.route;


import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.RecipeClient;
import com.mzielinski.cookbookfrontend.domain.dto.RecipeDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "recipes", layout = NavbarMenu.class)
public class Recipes extends VerticalLayout {

    private Grid<RecipeDto> grid = new Grid<>(RecipeDto.class);
    private RecipeClient recipeClient = new RecipeClient();

    public Recipes() {
        Button allRecipeCategoriesButton = new Button("Recipe categories");
        allRecipeCategoriesButton.addClickListener(click -> {
            allRecipeCategoriesButton.getUI().ifPresent(ui -> ui.navigate("recipecategories/"));
        });

        H2 heading = new H2("RECIPES");

        HorizontalLayout container = new HorizontalLayout(heading, allRecipeCategoriesButton);
        container.setAlignItems(Alignment.BASELINE);
        add(container);
        grid.setColumns("recipeId", "recipeName", "recipeDetails", "preparationTime", "recipeCategoryId", "userId");
        add(grid);
        setSizeFull();
        refresh();
    }

    private void refresh() {
        grid.setItems(recipeClient.getRecipes());
        grid.addComponentColumn(recipeDto -> {
            Button button = new Button("See the recipe");
            button.addClickListener(click -> {
                button.getUI().ifPresent(ui -> ui.navigate("recipes/" + recipeDto.getRecipeId()));
            });
            return button;
        });
    }


}
