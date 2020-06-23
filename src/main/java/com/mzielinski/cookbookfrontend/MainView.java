package com.mzielinski.cookbookfrontend;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import javax.annotation.PostConstruct;

@UIScope
@Route(layout = NavbarMenu.class)
@SpringComponent
public class MainView extends VerticalLayout {

    public final static String ROUTE = "simple";
    Image img = new Image("https://i.imgur.com/Cd5H84o.png", "CookBook Logo");

    @PostConstruct
    public void init() {
        //FORM
        Label htmlLabel = new Label("- dsd\n-dsds");


        H2 welcomeText = new H2("CookBook application");
        TextArea text1 = new TextArea();
        text1.setReadOnly(true);
        text1.setWidthFull();
        text1.setValue("CookBook application was created to help in gathering recipes in one place (app), with matching specific wine (type and product with price) to the dish (based on products) and is counting nutrition values (kcal, carbohydrates, protein and fat) of each ingredient.\n" +
                "\n" +
                "Project is connected to two external API's: Spoonacular and Edamam.");

        H3 nextText = new H3("Main Features");

        TextArea text2 = new TextArea();

        text2.setWidthFull();
        text2.setReadOnly(true);
        text2.setValue("- gathering the recipes by products, recipe category, description, preparation time etc.\n" +
                "- filtering the recipes by i.a. product, recipe category, preparation time\n" +
                "- wine selector api -> founding the best fitting wine to recipe based on main ingredients\n" +
                "- nutrition values api -> summing all nutrition values (kcal, carbo, protein, fat) by taking quantity of each ingredient of recipe.\n" +
                "- adding recipes, products and ingredients");

        H3 addRecipe = new H3("Adding recipe");

        TextArea text3 = new TextArea();
        text3.setReadOnly(true);
        text3.setWidthFull();
        text3.setValue(   "1. First create a recipe\n"+"2. Then add an ingredients to recipe. If the products are not available in the list - add them.\n" +
                "3. After successfully added recipe with products, you can find the recipe in the list, see the suggested wines and calculated nutrition values.");

        VerticalLayout container = new VerticalLayout(
                welcomeText,text1, nextText,text2, addRecipe, text3, img);

        container.setWidth("800px");
        container.getStyle().set("margin", "0 auto");


        VerticalLayout vLayout = new VerticalLayout();
        vLayout.add(container);

        vLayout.setSizeFull();

        add(
                vLayout
        );

    }




}