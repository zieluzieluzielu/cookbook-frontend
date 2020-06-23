package com.mzielinski.cookbookfrontend.ui.route;

import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.ProductClient;
import com.mzielinski.cookbookfrontend.client.ProductGroupClient;
import com.mzielinski.cookbookfrontend.domain.dto.ProductDto;
import com.mzielinski.cookbookfrontend.exception.ProductGroupNotFoundException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import java.util.List;

@Route(value = "products/productgroups", layout = NavbarMenu.class)
public class ProductsByProductGroup extends VerticalLayout implements HasUrlParameter<Long> {

    private ProductGroupClient productGroupClient = new ProductGroupClient();
    private ProductClient productClient = new ProductClient();
    private Grid<ProductDto> grid = new Grid<>(ProductDto.class);

    @Override
    public void setParameter(BeforeEvent event, Long parameter) throws ProductGroupNotFoundException {

        Button allRecipesButton = new Button("Back to Product Groups");
        allRecipesButton.addClickListener(click -> {
            allRecipesButton.getUI().ifPresent(ui -> ui.navigate("productgroups/"));
        });

        H2 heading = new H2("PRODUCT GROUP: " + productGroupClient.getProductGroup(parameter).getGroupName());

        HorizontalLayout container = new HorizontalLayout(heading, allRecipesButton);
        container.setAlignItems(Alignment.BASELINE);
        add(container);

        add(grid);
        setSizeFull();

        List<ProductDto> products = productClient.getProductByProductGroup(parameter);

        grid.setColumns("productId", "productName", "groupId");

        grid.setItems(products);

    }
}
