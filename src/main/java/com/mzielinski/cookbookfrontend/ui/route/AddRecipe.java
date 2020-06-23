package com.mzielinski.cookbookfrontend.ui.route;

import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.*;
import com.mzielinski.cookbookfrontend.domain.dto.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.*;
import com.vaadin.flow.router.Route;


@Route(value = "addrecipe", layout = NavbarMenu.class)
public class AddRecipe extends VerticalLayout {
    H2 heading = new H2("ADD NEW RECIPE");

    private IngredientClient ingredientClient = new IngredientClient();
    private ProductClient productClient = new ProductClient();
    private RecipeClient recipeClient = new RecipeClient();
    private RecipeCategoryClient recipeCategoryClient = new RecipeCategoryClient();
    public UserClient userClient = new UserClient();

    RecipeDto recipe = new RecipeDto();
    Button save = new Button("Save");
    Button clearButton = new Button("Clear");
    Button addIngredients = new Button("Add ingredients to this recipe",e -> getUI().ifPresent(ui -> ui.navigate("addingredient")));




    TextField recipeName = new TextField();

    TextArea recipeDetails = new TextArea();


    NumberField preparationTime = new NumberField();

    ComboBox<RecipeCategoryDto> recipeCategoryBox = new ComboBox<>();
    ComboBox<UserDto> userBox = new ComboBox<>();

    public AddRecipe() {
        add(heading);

        preparationTime.setHasControls(true);
        preparationTime.setMin(0);
        recipeDetails.getStyle().set("minWidth", "600px");
        recipeDetails.getStyle().set("maxWidth", "700px");
        recipeDetails.getStyle().set("minHeight", "400px");
        recipeDetails.setPlaceholder("Recipe description ...");
        recipeName.setPlaceholder("Recipe name ...");


        FormLayout formLayout = new FormLayout();

        FormLayout formLayout2 = new FormLayout();
;




        formLayout.addFormItem(recipeCategoryBox, "Recipe Category");
        formLayout.addFormItem(recipeName, "Recipe Name");
        formLayout.addFormItem(preparationTime, "Preparation time (in minutes)");
        formLayout.addFormItem(userBox, "Author");
        formLayout2.addFormItem(recipeDetails, "Recipe details");



        HorizontalLayout actions = new HorizontalLayout();
        actions.add(save, clearButton);

        VerticalLayout info = new VerticalLayout();

        formLayout2.add(save, clearButton, info);

        recipeCategoryBox.setRequiredIndicatorVisible(true);
        recipeName.setRequiredIndicatorVisible(true);
        preparationTime.setRequiredIndicatorVisible(true);
        recipeDetails.setRequiredIndicatorVisible(true);
        userBox.setRequiredIndicatorVisible(true);

        formLayout.setWidth("800px");
        formLayout.getStyle().set("margin", "0 auto");
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        formLayout2.setWidth("800px");
        formLayout.getStyle().set("margin", "0 auto");
        formLayout2.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP));


        formLayout.setColspan(recipeCategoryBox, 2);
        formLayout.setColspan(recipeName, 2);
        formLayout.setColspan(preparationTime, 2);
        formLayout2.setColspan(recipeDetails, 2);
        formLayout.setColspan(userBox, 2);

        formLayout2.setColspan(save, 2);
        formLayout2.setColspan(clearButton, 2);

        //Layout
        VerticalLayout mainLayout = new VerticalLayout();

        mainLayout.setWidth("800px");
        mainLayout.getStyle().set("margin", "0 auto");
        mainLayout.setAlignItems(Alignment.CENTER);
        add(mainLayout);
        mainLayout.add(formLayout);

        mainLayout.add(formLayout2);
        mainLayout.add(addIngredients);


        userBox.setItems(userClient.getUsers());
        userBox.setRequired(true);

        recipeCategoryBox.setItems(recipeCategoryClient.getRecipeCategories());
        recipeCategoryBox.setRequired(true);


        clearButton.addClickListener(click -> {
            recipeCategoryBox.clear();
            recipeName.clear();
            preparationTime.clear();
            recipeDetails.clear();
            userBox.clear();

        });

        saveRecipe();
    }

    private void saveRecipe() {
        save.addClickListener(event -> {

            if (preparationTime.getValue() != null) {
                long longPreparationTime = (preparationTime.getValue()).longValue();
                recipe.setPreparationTime(longPreparationTime);
            } else {
                recipe.setPreparationTime(null);
            }

            recipe.setRecipeName(recipeName.getValue());
            recipe.setRecipeDetails(recipeDetails.getValue());

            if (userBox.getValue() != null) {
                recipe.setUserId(userBox.getValue().getUserId());
            } else {
                recipe.setUserId(null);
            }

            if (recipeCategoryBox.getValue() != null) {
                recipe.setRecipeCategoryId(recipeCategoryBox.getValue().getRecipeCategoryId());
            } else {
                recipe.setRecipeCategoryId(null);
            }

            if (recipe.getRecipeName() != null && recipe.getRecipeCategoryId() != null && recipe.getRecipeDetails() != null && recipe.getPreparationTime() != null && recipe.getUserId() != null) {
                recipeClient.createRecipe(recipe);
                showSuccess();
            } else {
                showError();
            }
        });
    }

    private void showSuccess() {
        Notification notification = Notification.show("Recipe " + recipe.getRecipeName() + " was successfully added to the app");
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);

    }

    private void showError() {
        Notification notification = Notification.show("Please fill the form correctly (all fields are mandatory)");
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);

    }


}
