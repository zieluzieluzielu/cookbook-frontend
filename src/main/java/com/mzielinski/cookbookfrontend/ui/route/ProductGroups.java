package com.mzielinski.cookbookfrontend.ui.route;

import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.ProductGroupClient;
import com.mzielinski.cookbookfrontend.domain.dto.ProductGroupDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value="productgroups", layout = NavbarMenu.class)
public class ProductGroups extends VerticalLayout {

    private Grid<ProductGroupDto> grid = new Grid<>(ProductGroupDto.class);
    private ProductGroupClient productGroupClient = new ProductGroupClient();

    public ProductGroups() {

        H2 heading = new H2("PRODUCT GROUPS");

        HorizontalLayout container = new HorizontalLayout(heading);
        container.setAlignItems(Alignment.BASELINE);
        add(container);

        grid.setColumns("productGroupId","groupName");
        add(grid);
        setSizeFull();
        refresh();
    }

    private void refresh() {
        grid.setItems(productGroupClient.getProductGroups());
        grid.addComponentColumn(productGroupDto -> {
            Button button = new Button("See the products");
            button.addClickListener(click -> {
                button.getUI().ifPresent(ui -> ui.navigate("products/productgroups/" + productGroupDto.getProductGroupId()));
            });
            return button;
        });

    }

}
