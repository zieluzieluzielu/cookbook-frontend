package com.mzielinski.cookbookfrontend.ui.route;

import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.ProductClient;
import com.mzielinski.cookbookfrontend.client.RecipeCategoryClient;
import com.mzielinski.cookbookfrontend.client.RecipeClient;
import com.mzielinski.cookbookfrontend.domain.dto.RecipeCategoryDto;
import com.mzielinski.cookbookfrontend.domain.dto.RecipeDto;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;


@Route(value = "recipesbycategory", layout = NavbarMenu.class)
public class FindRecipesByCategory extends VerticalLayout {

    ComboBox<RecipeCategoryDto> findRecipesByCategoryComboBox = new ComboBox<>();

    private Grid<RecipeDto> grid = new Grid<>(RecipeDto.class);

    private RecipeClient recipeClient = new RecipeClient();
    private RecipeCategoryClient recipeCategoryClient = new RecipeCategoryClient();

    private ProductClient productClient = new ProductClient();

    public void refresh() {
        grid.setItems(recipeClient.getRecipes());

    }

    private void update() {

        grid.setItems(recipeClient.getRecipesByCategory(findRecipesByCategoryComboBox.getValue().getRecipeCategoryId()));
    }

    public FindRecipesByCategory() {


        findRecipesByCategoryComboBox.setItems(recipeCategoryClient.getRecipeCategories());
        findRecipesByCategoryComboBox.setLabel("Find recipe by category");
        findRecipesByCategoryComboBox.setPlaceholder("category name");

        grid.setColumns("recipeName", "recipeDetails", "preparationTime");
        add(grid);

        findRecipesByCategoryComboBox.addValueChangeListener(e -> update());
        HorizontalLayout toolbar = new HorizontalLayout(findRecipesByCategoryComboBox);
        toolbar.setAlignItems(Alignment.BASELINE);

        grid.addComponentColumn(recipeDto -> {
            Text text = new Text(recipeCategoryClient.getRecipeCategory(recipeDto.getRecipeCategoryId()).getRecipeCategoryName());
            return text;
        }).setHeader("Recipe Category").setSortable(true).isSortable();

        grid.addComponentColumn(recipeDto -> {
            Button button = new Button("Recipe details");
            button.addClickListener(click -> {
                button.getUI().ifPresent(ui -> ui.navigate("recipes/" + recipeDto.getRecipeId()));
            });
            return button;
        });

        HorizontalLayout mainContent = new HorizontalLayout(grid);
        mainContent.setSizeFull();
        grid.setSizeFull();

        add(toolbar, mainContent);
        setSizeFull();
        refresh();

    }


}
