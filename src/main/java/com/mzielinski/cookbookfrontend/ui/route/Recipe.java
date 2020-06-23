package com.mzielinski.cookbookfrontend.ui.route;

import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.mzielinski.cookbookfrontend.client.*;
import com.mzielinski.cookbookfrontend.domain.dto.*;
import com.mzielinski.cookbookfrontend.exception.ProductGroupNotFoundException;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Route(value = "recipes", layout = NavbarMenu.class)
public class Recipe extends VerticalLayout implements HasUrlParameter<Long> {


    private RecipeClient recipeClient = new RecipeClient();
    private IngredientClient ingredientClient = new IngredientClient();
    private RecipeCategoryClient recipeCategoryClient = new RecipeCategoryClient();
    private UserClient userClient = new UserClient();
    private ProductClient productClient = new ProductClient();
    private EdamamClient edamamClient = new EdamamClient();
    private SpoonacularClient spoonacularClient = new SpoonacularClient();


    @Override
    public void setParameter(BeforeEvent event, Long parameter) throws ProductGroupNotFoundException {
        Button allRecipesButton = new Button("Back to recipes list");
        allRecipesButton.addClickListener(click -> {
            allRecipesButton.getUI().ifPresent(ui -> ui.navigate("recipes/"));
        });

        H2 heading = new H2("RECIPE: " + recipeClient.getRecipe(parameter).getRecipeName());

        HorizontalLayout container = new HorizontalLayout(heading, allRecipesButton);
        container.setAlignItems(Alignment.BASELINE);
        add(container);

        RecipeDto recipe = recipeClient.getRecipe(parameter);
        EdamamDto edamam = edamamClient.getNutritionSum(parameter);

        List<IngredientDto> ingredients = ingredientClient.getIngredientsFromRecipe(parameter);
        RecipeCategoryDto recipeCategory = recipeCategoryClient.getRecipeCategory(recipeClient.getRecipe(recipe.getRecipeId()).getRecipeCategoryId());

        List<ProductDto> products = productClient.getProductsByRecipe(parameter);

        UserDto user = userClient.getUser(recipeClient.getRecipe(recipe.getRecipeId()).getUserId());

        H4 recipeCategoryField = new H4("Recipe Category:");
        Button recipeCategoryButton = new Button(recipeCategory.getRecipeCategoryName());
        recipeCategoryButton.addClickListener(click -> {
            allRecipesButton.getUI().ifPresent(ui -> ui.navigate("recipes/recipecategory/" + recipeCategory.getRecipeCategoryId()));
        });

        HorizontalLayout categoryContainer = new HorizontalLayout(recipeCategoryField, recipeCategoryButton);
        categoryContainer.setAlignItems(Alignment.BASELINE);

        //RECIPE DETAILS
        H4 recipeDetailsField = new H4("Recipe Details:");
        TextArea recipeDetails = new TextArea();
        recipeDetails.setReadOnly(true);
        recipeDetails.setWidth("700px");
        recipeDetails.setValue(recipe.getRecipeDetails());
        HorizontalLayout recipeDetailsFieldContainer = new HorizontalLayout(recipeDetailsField);
        HorizontalLayout recipeDetailsContainer = new HorizontalLayout(recipeDetails);
        recipeDetailsFieldContainer.setAlignItems(Alignment.BASELINE);
        recipeDetailsContainer.setAlignItems(Alignment.BASELINE);

        //PREPARATION TIME
        H4 preparationTimeField = new H4("Preparation time: ");
        Text preparationTime = new Text(" " + recipe.getPreparationTime().toString());
        Text preparationTimeMinutes = new Text(" minutes");

        HorizontalLayout preparatiomTimeContainer = new HorizontalLayout(preparationTimeField, preparationTime, preparationTimeMinutes);
        preparatiomTimeContainer.setAlignItems(Alignment.BASELINE);

        //INGREDIENTS

        H4 ingredientsField = new H4("Ingredients:");
        HorizontalLayout ingredientsContainer = new HorizontalLayout(ingredientsField);
        TextArea ingredientAarea = new TextArea();
        ingredientAarea.setReadOnly(true);
        ingredientAarea.setWidth("700px");

        if (ingredients.size() > 0) {
            List<String> ingredientList = new ArrayList<>();
            for (int i = 0; i < ingredients.size(); i++) {
                ingredientList.add(ingredients.get(i).getAmount() + " " + ingredients.get(i).getUnit() + " " + products.get(i).getProductName());
            }
            String delimiter = "\n";

            ingredientAarea.setValue(String.join(delimiter, ingredientList));
        } else {
            ingredientAarea.setValue("no ingredients found");
        }

        HorizontalLayout ingredientsListContainer = new HorizontalLayout(ingredientAarea);
        ingredientsContainer.setAlignItems(Alignment.BASELINE);

        //WINE SELECTOR

        H4 wineField = new H4("Wine matching:");
        HorizontalLayout wineFieldContainer = new HorizontalLayout(wineField);

        VerticalLayout wineBottomContainer = new VerticalLayout();

        List<Boolean> mainProd = ingredients.stream()
                .filter(x -> x.getRecipeId().equals(parameter))
                .map(IngredientDto::isMainProduct)
                .collect(Collectors.toList());
        if (mainProd.contains(true)) {
            SpoonacularDto spoonacular = spoonacularClient.getWineFromRecipe(parameter);


            Label winePairingText = new Label(spoonacular.getPairingText());
            Label wineDescription = new Label(spoonacular.getProductMatchesDto().get(0).getWineDescription());
            H5 wineTitle = new H5(spoonacular.getProductMatchesDto().get(0).getWineTitle());
            Label winePrice = new Label(spoonacular.getProductMatchesDto().get(0).getWinePrice());

            HorizontalLayout winePairingContainer = new HorizontalLayout(winePairingText);
            HorizontalLayout wineDetails1Container = new HorizontalLayout(wineTitle, winePrice);
            wineDetails1Container.setAlignItems(Alignment.BASELINE);
            HorizontalLayout wineDetails2Container = new HorizontalLayout(wineDescription);

            wineBottomContainer.add(wineFieldContainer, winePairingContainer, wineDetails1Container, wineDetails2Container);
        } else {
            wineBottomContainer.add(new Label("Main product was not set in the recipe."));
        }

        wineBottomContainer.setAlignItems(Alignment.BASELINE);

        //EDAMAM NUTRITION VALUES
        H4 nutritionValuesField = new H4("Nutrition values:");
        HorizontalLayout nutritionContainer = new HorizontalLayout(nutritionValuesField);

        //Kcal
        Label kcalField = new Label("Calories: ");
        Label kcalDetails = new Label(edamam.getNutrientsDto().getKcalDto().getQuantity() + " " + edamam.getNutrientsDto().getKcalDto().getUnit());
        HorizontalLayout kcalContainer = new HorizontalLayout(kcalField, kcalDetails);
        kcalContainer.setAlignItems(Alignment.BASELINE);

        //Protein
        Label proteinField = new Label("Protein:");
        Label proteinDetails = new Label(edamam.getNutrientsDto().getProteinDto().getQuantity() + " " + edamam.getNutrientsDto().getProteinDto().getUnit());
        HorizontalLayout proteinContainer = new HorizontalLayout(proteinField, proteinDetails);
        proteinContainer.setAlignItems(Alignment.BASELINE);

        //Carbo
        Label carboField = new Label("Carbohydrates:");
        Label carboDetails = new Label(edamam.getNutrientsDto().getCarbohydratesDto().getQuantity() + " " + edamam.getNutrientsDto().getCarbohydratesDto().getUnit());
        HorizontalLayout carboContainer = new HorizontalLayout(carboField, carboDetails);
        carboContainer.setAlignItems(Alignment.BASELINE);

        //Fat
        Label fatField = new Label("Fat: " + " ");
        Label fatDetails = new Label(edamam.getNutrientsDto().getFatDto().getQuantity() + " " + edamam.getNutrientsDto().getFatDto().getUnit());
        HorizontalLayout fatContainer = new HorizontalLayout(fatField, fatDetails);
        fatContainer.setAlignItems(Alignment.BASELINE);


        //AUTHOR

        H4 authorField = new H4("Author: ");
        Button authorButton = new Button(user.getUserName());
        authorButton.addClickListener(click -> {
            allRecipesButton.getUI().ifPresent(ui -> ui.navigate("recipes/user/" + user.getUserId()));
        });

        HorizontalLayout authorContainer = new HorizontalLayout(authorField, authorButton);
        authorContainer.setAlignItems(Alignment.BASELINE);


        VerticalLayout recipeInformation = new VerticalLayout(categoryContainer, preparatiomTimeContainer, ingredientsContainer, ingredientsListContainer, recipeDetailsFieldContainer, recipeDetailsContainer, nutritionContainer, kcalContainer, proteinContainer, carboContainer, fatContainer, wineFieldContainer, wineBottomContainer
                , authorContainer);

        add(recipeInformation);

        setSizeFull();


    }
}
