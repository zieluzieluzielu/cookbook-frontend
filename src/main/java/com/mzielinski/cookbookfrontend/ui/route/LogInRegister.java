package com.mzielinski.cookbookfrontend.ui.route;

import com.mzielinski.cookbookfrontend.NavbarMenu;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "loginregister", layout = NavbarMenu.class)
public class LogInRegister extends VerticalLayout {
    H2 heading = new H2("LOG IN / REGISTER");

    Text information = new Text("Functionality to be developed soon");

    public LogInRegister() {
        add(heading);
        add(information);
    }


}
