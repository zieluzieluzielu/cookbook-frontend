package com.mzielinski.cookbookfrontend.ui.route;


import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.IngredientClient;
import com.mzielinski.cookbookfrontend.client.ProductClient;
import com.mzielinski.cookbookfrontend.domain.dto.IngredientDto;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "ingredients", layout = NavbarMenu.class)
public class Ingredients extends VerticalLayout {

    private Grid<IngredientDto> grid = new Grid<>(IngredientDto.class);
    private IngredientClient ingredientClient = new IngredientClient();
    private ProductClient productClient = new ProductClient();


    public Ingredients() {
        H2 heading = new H2("INGREDIENTS");

        HorizontalLayout container = new HorizontalLayout(heading);
        container.setAlignItems(Alignment.BASELINE);
        add(container);

        grid.setColumns("ingredientId", "amount", "unit", "productId", "recipeId", "mainProduct");
        add(grid);
        setSizeFull();
        refresh();
    }

    private void refresh() {
        grid.setItems(ingredientClient.getIngredients());

        grid.addComponentColumn(ingredientDto -> {
            Text text = new Text(productClient.getProduct(ingredientDto.getProductId()).getProductName());
            return text;
        }).setHeader("Product");
    }


}
