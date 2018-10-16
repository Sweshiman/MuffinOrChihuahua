package com.ixdproject.madam.ui.views;

import com.ixdproject.madam.backend.ImageManager;
import com.ixdproject.madam.ui.MainLayoutI;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.ixdproject.madam.ui.MainLayout;

@Route(value = "tuning_game", layout = MainLayout.class)
@PageTitle("Tuning game")
public class TuningGame extends VerticalLayout implements MuffinView {

    private ImageManager imageManager = new ImageManager();

    public void showTuningGame(MainLayoutI mainLayout) {
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

    @Override
    public void handleInput(String command) {

    }
}