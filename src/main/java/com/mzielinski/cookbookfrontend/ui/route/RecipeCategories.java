package com.mzielinski.cookbookfrontend.ui.route;

import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.RecipeCategoryClient;
import com.mzielinski.cookbookfrontend.domain.dto.RecipeCategoryDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "recipecategories", layout = NavbarMenu.class)
public class RecipeCategories extends VerticalLayout {

    private Grid<RecipeCategoryDto> grid = new Grid<>(RecipeCategoryDto.class);
    private RecipeCategoryClient recipeCategoryClient = new RecipeCategoryClient();

    public RecipeCategories() {

        H2 heading = new H2("RECIPE CATEGORIES");

        HorizontalLayout container = new HorizontalLayout(heading);
        container.setAlignItems(Alignment.BASELINE);
        add(container);

        grid.setColumns("recipeCategoryId", "recipeCategoryName");
        add(grid);
        setSizeFull();
        refresh();
    }

    private void refresh() {
        grid.setItems(recipeCategoryClient.getRecipeCategories());
        grid.addComponentColumn(recipeCategoryDto -> {
            Button button = new Button("See all recipes from this category");
            button.addClickListener(click -> {
                button.getUI().ifPresent(ui -> ui.navigate("recipes/recipecategory/" + recipeCategoryDto.getRecipeCategoryId()));
            });
            return button;
        });

    }

}
