package com.mzielinski.cookbookfrontend.ui.route;

import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.RecipeClient;
import com.mzielinski.cookbookfrontend.client.UserClient;
import com.mzielinski.cookbookfrontend.domain.dto.RecipeDto;
import com.mzielinski.cookbookfrontend.exception.UserNotFoundException;
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

@Route(value = "recipes/user", layout = NavbarMenu.class)
public class RecipesByUser extends VerticalLayout implements HasUrlParameter<Long> {

    private RecipeClient recipeClient = new RecipeClient();
    private Grid<RecipeDto> grid = new Grid<>(RecipeDto.class);
    private UserClient userClient = new UserClient();

    @Override
    public void setParameter(BeforeEvent event, Long parameter) throws UserNotFoundException {

        Button allRecipesButton = new Button("Back to recipes list");
        allRecipesButton.addClickListener(click -> {
            allRecipesButton.getUI().ifPresent(ui -> ui.navigate("recipes/"));
        });

        Button allUsersButton = new Button("Back to users list");
        allUsersButton.addClickListener(click -> {
            allUsersButton.getUI().ifPresent(ui -> ui.navigate("users/"));
        });

        H2 heading = new H2("RECIPES BY: " + userClient.getUser(parameter).getUserName() + " (" + recipeClient.getRecipesByUser(parameter).size() + ")");

        HorizontalLayout container = new HorizontalLayout(heading, allRecipesButton, allUsersButton);
        container.setAlignItems(Alignment.BASELINE);
        add(container);


        List<RecipeDto> recipes = recipeClient.getRecipesByUser(parameter);

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
            Text empty = new Text("No recipes were created by this user");
            add(empty);
        }


    }
}
