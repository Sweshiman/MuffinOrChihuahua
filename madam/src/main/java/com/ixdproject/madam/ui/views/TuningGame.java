package com.ixdproject.madam.ui.views;

import com.ixdproject.madam.backend.ImageManager;
import com.ixdproject.madam.backend.Tag;
import com.vaadin.flow.component.KeyPressEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.ixdproject.madam.ui.MainLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PageConfigurator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Route(value = "tuning_game", layout = MainLayout.class)
@PageTitle("Tuning game")
public class TuningGame extends VerticalLayout {

    private ImageManager imageManager = new ImageManager();

    public TuningGame() {

    }

    public void showTuningGame(Div mainLayout) {
        ImageManager imageManager = new ImageManager();
        Image currentImage = imageManager.getCurrentImage();
        currentImage.setClassName("current_image");

        mainLayout.add(initOverheadImages(), currentImage);
        // TODO: Handle events coming from RasPi
    }

    private Div initOverheadImages() {
        Div imageOverview = new Div();
        imageManager.getAllImages().forEach(image -> {
            image.addClassName("overview_image");
            imageOverview.add(image);

        });
        imageOverview.addClassName("image_overview_container");

        return imageOverview;
    }
}