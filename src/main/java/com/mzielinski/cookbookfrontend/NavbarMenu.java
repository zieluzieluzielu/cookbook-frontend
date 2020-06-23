package com.mzielinski.cookbookfrontend;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.spring.annotation.UIScope;

import javax.annotation.PostConstruct;

@UIScope
public class NavbarMenu extends AppLayout {
    public NavbarMenu() {

        Image img = new Image("https://i.imgur.com/Cd5H84o.png", "CookBook Logo");
        img.setHeight("44px");

        addToNavbar(true, img);

        MenuBar menuBar = new MenuBar();

        MenuItem findRecipe = menuBar.addItem("Find a Recipe");

        MenuItem addRecipe = menuBar.addItem("Add a Recipe", e -> getUI().ifPresent(ui -> ui.navigate("addrecipe")));

        MenuItem addIngredient = menuBar.addItem("Add an ingredient",
                e -> getUI().ifPresent(ui -> ui.navigate("addingredient")));

        MenuItem addProduct = menuBar.addItem("Add a Product",
                e -> getUI().ifPresent(ui -> ui.navigate("addproduct")));



        MenuItem explore = menuBar.addItem("Explore");

        findRecipe.getSubMenu().addItem("By Product",
                e -> findRecipe.getUI().ifPresent(ui -> ui.navigate("recipesbyproduct")));
        findRecipe.getSubMenu().addItem("By Recipe Category",
                e -> findRecipe.getUI().ifPresent(ui -> ui.navigate("recipesbycategory")));

        explore.getSubMenu().addItem("Recipes",
                e -> explore.getUI().ifPresent(ui -> ui.navigate("recipes")));

        explore.getSubMenu().addItem("Recipe Categories",
                e -> explore.getUI().ifPresent(ui -> ui.navigate("recipecategories")));

        explore.getSubMenu().addItem("Products",
                e -> explore.getUI().ifPresent(ui -> ui.navigate("products")));

        explore.getSubMenu().addItem("Product Groups",
                e -> explore.getUI().ifPresent(ui -> ui.navigate("productgroups")));

        explore.getSubMenu().addItem("Ingredients",
                e -> explore.getUI().ifPresent(ui -> ui.navigate("ingredients")));

        explore.getSubMenu().addItem("Users",
                e -> explore.getUI().ifPresent(ui -> ui.navigate("users")));


        MenuItem addItem = menuBar.addItem("Log In / Register",
                e -> getUI().ifPresent(ui -> ui.navigate("loginregister")));


        addToNavbar(true, menuBar);
        this.setPrimarySection(Section.NAVBAR);

    }

}