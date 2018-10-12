package com.ixdproject.madam.ui;

import com.ixdproject.madam.ui.views.GuessingGame;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;

@HtmlImport("frontend://styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainLayout extends Div
        implements RouterLayout, PageConfigurator {

    public MainLayout() {
        addClassName("main-layout");

        new GuessingGame().showGuessingGame(this);
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
    }
}
