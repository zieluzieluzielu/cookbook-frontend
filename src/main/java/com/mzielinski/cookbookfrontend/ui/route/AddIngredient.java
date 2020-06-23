package com.mzielinski.cookbookfrontend.ui.route;

import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.IngredientClient;
import com.mzielinski.cookbookfrontend.client.ProductClient;
import com.mzielinski.cookbookfrontend.client.RecipeClient;
import com.mzielinski.cookbookfrontend.domain.dto.IngredientDto;
import com.mzielinski.cookbookfrontend.domain.dto.ProductDto;
import com.mzielinski.cookbookfrontend.domain.dto.RecipeDto;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.BigDecimalField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

import java.util.stream.Collectors;

@Route(value = "addingredient", layout = NavbarMenu.class)
public class AddIngredient extends VerticalLayout {
    H2 heading = new H2("ADD AN INGREDIENT TO RECIPE");

    private IngredientClient ingredientClient = new IngredientClient();
    private ProductClient productClient = new ProductClient();
    private RecipeClient recipeClient = new RecipeClient();
    BigDecimalField productQuantity = new BigDecimalField();

    Checkbox mainProductCheckbox = new Checkbox();

    ComboBox<ProductDto> productBox = new ComboBox<>();
    ComboBox<RecipeDto> recipeBox = new ComboBox<>();
    ComboBox<String> ingredientUnit = new ComboBox<>();
    Button save = new Button("Save");
    Button clearButton = new Button("Clear");
    IngredientDto ingredient = new IngredientDto();

    Text information = new Text("Please try to add only one main product to the recipe.");


    public AddIngredient() {
        add(heading);
        add(information);
        productQuantity.setValueChangeMode(ValueChangeMode.EAGER);

        FormLayout formLayout = new FormLayout();

        formLayout.addFormItem(recipeBox, "Recipe");

        mainProductCheckbox.setValue(false);

        formLayout.addFormItem(productBox, "Product");
        formLayout.addFormItem(mainProductCheckbox, "Main Product");
        formLayout.addFormItem(productQuantity,"Quantity (coma separated)");
        formLayout.addFormItem(ingredientUnit, "Unit");

        HorizontalLayout actions = new HorizontalLayout();
        actions.add(save, clearButton);



        formLayout.add(save, clearButton);

        productQuantity.setRequiredIndicatorVisible(true);
        productBox.setRequiredIndicatorVisible(true);
        recipeBox.setRequiredIndicatorVisible(true);
        ingredientUnit.setRequiredIndicatorVisible(true);

        formLayout.setWidth("800px");
        formLayout.getStyle().set("margin", "0 auto");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 3, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        formLayout.setColspan(productBox, 2);
        formLayout.setColspan(recipeBox,2);
        formLayout.setColspan(productQuantity,2);
        formLayout.setColspan(mainProductCheckbox,2);
        formLayout.setColspan(ingredientUnit,2);
        formLayout.setColspan(save, 2);
        formLayout.setColspan(clearButton, 2);

        add(formLayout);

        productBox.setItems(productClient.getProducts());
        productBox.setRequired(true);

        recipeBox.setItems(recipeClient.getRecipes());
        recipeBox.setRequired(true);

        ingredientUnit.setItems(ingredientClient.getIngredients().stream().map(t->t.getUnit()).collect(Collectors.toSet()));


        clearButton.addClickListener(click -> {
            productQuantity.clear();
            productBox.clear();
            mainProductCheckbox.clear();
            recipeBox.clear();
            ingredientUnit.clear();

        });

        saveIngredient();
    }


    private void saveIngredient() {
        save.addClickListener(event -> {
            ingredient.setAmount(productQuantity.getValue());
            ingredient.setUnit(ingredientUnit.getValue());
            ingredient.setMainProduct(mainProductCheckbox.getValue());

            if (productBox.getValue() != null) {
                ingredient.setProductId(productBox.getValue().getProductId());
            } else {
                ingredient.setProductId(null);
            }

            if (recipeBox.getValue() != null) {
                ingredient.setRecipeId(recipeBox.getValue().getRecipeId());
            } else {
                ingredient.setRecipeId(null);
            }
            if (ingredient.getAmount() !=null &&  ingredient.getUnit() !=null && ingredient.getProductId() != null && ingredient.getAmount() != null) {
                ingredientClient.createIngredient(ingredient);
                showSuccess();
            } else {
                showError();
            }
        });
    }

    private void showSuccess() {
        Notification notification = Notification.show(ingredient.getAmount() +" "+ingredient.getUnit()+ " " + productBox.getValue().getProductName()+ " was successfully addeed  to recipe:"+recipeBox.getValue().getRecipeName());
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

    }

    private void showError() {
        Notification notification = Notification.show("Please fill the form correctly (all fields are mandatory)");
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

    }


}
