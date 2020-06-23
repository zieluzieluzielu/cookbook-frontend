package com.mzielinski.cookbookfrontend.ui.route;


import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.ProductClient;
import com.mzielinski.cookbookfrontend.domain.dto.ProductDto;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "products", layout = NavbarMenu.class)
public class Products extends VerticalLayout {

    private Grid<ProductDto> grid = new Grid<>(ProductDto.class);
    private ProductClient productClient = new ProductClient();


    public Products() {
        H2 heading = new H2("PRODUCTS");

        HorizontalLayout container = new HorizontalLayout(heading);
        container.setAlignItems(Alignment.BASELINE);
        add(container);
        grid.setColumns("productId", "productName", "groupId");
        add(grid);
        setSizeFull();
        refresh();
    }

    private void refresh() {
        grid.setItems(productClient.getProducts());

    }


}
