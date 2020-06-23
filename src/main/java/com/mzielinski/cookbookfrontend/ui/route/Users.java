package com.mzielinski.cookbookfrontend.ui.route;

import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.RecipeClient;
import com.mzielinski.cookbookfrontend.client.UserClient;
import com.mzielinski.cookbookfrontend.domain.dto.UserDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "users", layout = NavbarMenu.class)
public class Users extends VerticalLayout {

    private Grid<UserDto> grid = new Grid<>(UserDto.class);
    private UserClient userClient = new UserClient();
    private RecipeClient recipeClient = new RecipeClient();


    public Users() {

        H2 heading = new H2("USERS");

        HorizontalLayout container = new HorizontalLayout(heading);
        container.setAlignItems(Alignment.BASELINE);
        add(container);

        grid.setColumns("userId", "userName", "emailAddress");
        add(grid);
        setSizeFull();
        refresh();
    }

    private void refresh() {
        grid.setItems(userClient.getUsers());

        grid.addComponentColumn(userDto -> {
            long size = recipeClient.getRecipesByUser(userDto.getUserId()).size();
            Button button = new Button(

                    size + (size == 1 ? " recipe" : " recipes"));
            button.addClickListener(click -> {
                button.getUI().ifPresent(ui -> ui.navigate("recipes/user/" + userDto.getUserId()));
            });
            return button;
        }).setHeader("Number of recipes");


    }
}
