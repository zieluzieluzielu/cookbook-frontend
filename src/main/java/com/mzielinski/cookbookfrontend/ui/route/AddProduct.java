package com.mzielinski.cookbookfrontend.ui.route;

import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.ProductClient;
import com.mzielinski.cookbookfrontend.client.ProductGroupClient;
import com.mzielinski.cookbookfrontend.domain.dto.ProductDto;
import com.mzielinski.cookbookfrontend.domain.dto.ProductGroupDto;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route(value = "addproduct", layout = NavbarMenu.class)
public class AddProduct extends VerticalLayout {
    H2 heading = new H2("ADD A PRODUCT");

    private ProductClient productClient = new ProductClient();
    public ProductGroupClient productGroupClient = new ProductGroupClient();
    TextField productName = new TextField();

    ComboBox<ProductGroupDto> productGroup = new ComboBox<>();
    Button save = new Button("Save");
    Button clearButton = new Button("Clear");
    ProductDto product = new ProductDto();

    public AddProduct() {
        add(heading);
        productName.setValueChangeMode(ValueChangeMode.EAGER);

        FormLayout formLayout = new FormLayout();

        formLayout.addFormItem(productName, "Product Name");
        formLayout.addFormItem(productGroup, "Product Group");

        HorizontalLayout actions = new HorizontalLayout();
        actions.add(save, clearButton);

        VerticalLayout info = new VerticalLayout();

        formLayout.add(save, clearButton, info);

        productName.setRequiredIndicatorVisible(true);
        productGroup.setRequiredIndicatorVisible(true);

        formLayout.setMaxWidth("500px");
        formLayout.getStyle().set("margin", "0 auto");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        formLayout.setColspan(productName, 2);
        formLayout.setColspan(productGroup, 2);
        formLayout.setColspan(save, 2);
        formLayout.setColspan(clearButton, 2);

        add(formLayout);

        productGroup.setItems(productGroupClient.getProductGroups());
        productGroup.setRequired(true);
        productGroup.setValue(productGroupClient.getProductGroups().get(0));


        clearButton.addClickListener(click -> {
            productName.clear();
            productGroup.clear();

        });

        saveProduct();
    }


    private void saveProduct() {
        save.addClickListener(event -> {
            product.setProductName(productName.getValue());

            if (productGroup.getValue() != null) {
                product.setGroupId(productGroup.getValue().getProductGroupId());
            } else {
                product.setGroupId(null);
            }

            if (!product.getProductName().equals("") && product.getGroupId() != null) {
                productClient.createProduct(product);
                showSuccess();
            } else {
                showError();
            }

        });
    }

    private void showSuccess() {
        Notification notification = Notification.show("Product: " + productName.getValue() + " was successfully saved in group: " + productGroup.getValue().getGroupName());
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

    }

    private void showError() {
        Notification notification = Notification.show("Please specify the product name or product group");
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

    }


}
