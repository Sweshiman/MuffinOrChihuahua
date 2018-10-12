package com.ixdproject.madam.ui.views;

import com.ixdproject.madam.backend.ImageManager;
import com.ixdproject.madam.backend.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.ixdproject.madam.ui.MainLayout;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Guessing Game")
public class GuessingGame extends VerticalLayout {

    private ImageManager imageManager = new ImageManager();

    public GuessingGame() {

    }

    public void showGuessingGame(Div mainLayout) {
        ImageManager imageManager = new ImageManager();
        Image currentImage = imageManager.getCurrentImage();
        currentImage.setClassName("current_image");

        mainLayout.add(initOverheadImages(), currentImage, initButtons(currentImage));
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

    private Div initButtons(Image currentImage) {
        Button muffin = new Button("Muffin");
        muffin.addClassName("button");
        muffin.addClickListener(buttonClickEvent -> {
            System.out.println(imageManager.isCorrectGuess(Tag.MUFFIN));
            currentImage.setSrc(imageManager.getNextImageSrc());
        });

        Button chihuahua = new Button("Chihuahua");
        chihuahua.addClassName("button");
        chihuahua.addClickListener(buttonClickEvent -> {
            System.out.println(imageManager.isCorrectGuess(Tag.CHIHUAHUA));
            currentImage.setSrc(imageManager.getNextImageSrc());
        });

        Div buttons = new Div(muffin, chihuahua);
        buttons.addClassName("button_container");
        return buttons;
    }
}
