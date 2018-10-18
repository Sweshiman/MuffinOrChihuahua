package com.ixdproject.madam.ui.views;

import com.ixdproject.madam.backend.ImageManager;
import com.ixdproject.madam.backend.Tag;
import com.ixdproject.madam.ui.MainLayoutI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.ixdproject.madam.ui.MainLayout;

import java.util.HashMap;
import java.util.List;

@Route(value = "tuning_game", layout = MainLayout.class)
@PageTitle("Tuning game")
public class TuningGame extends VerticalLayout implements MuffinView {

    private ImageManager imageManager = new ImageManager();
    private Image currentImage = imageManager.getFirstImage();
    private List<Image> overheadImages = imageManager.getAllImages();
    private int index = 0;
    private HashMap<Image, Div> overheadImageDivMap = new HashMap<>();

    public void showTuningGame(MainLayoutI mainLayout) {
        currentImage.setClassName("current_image");

        mainLayout.add(
                initOverheadImages(),
                currentImage

                // ----- Remove after debug
                ,new Button("Previous", event -> {
                    showPreviousImage();
                    updateOverviewImages();
                }),
                new Button("Next", event -> {
                    showNextImage();
                    updateOverviewImages();
                })
                // ---- Remove after debug

        );
    }

    private Div initOverheadImages() {
        Div imageOverview = new Div();
        overheadImages.forEach(image -> {
            image.addClassName("image");
            if (!image.getSrc().equals(imageManager.getFirstImage().getSrc())) {
                image.addClassName("image_unseen");
            }

            Div imageDiv = new Div(image);
            imageDiv.addClassName("overview_image");
            overheadImageDivMap.put(image, imageDiv);
            imageOverview.add(imageDiv);
        });
        imageOverview.addClassName("image_overview_container");

        return imageOverview;
    }

    private void updateOverviewImages() {
        overheadImages.forEach(image -> {
            if (!image.hasClassName("image_unseen")) {
                if (true) { //TODO: Replace with real check whether the image is tuned correctly or not
                    overheadImageDivMap.get(image).removeClassName("image_wrong");
                    overheadImageDivMap.get(image).addClassName("image_correct");
                } else {
                    overheadImageDivMap.get(image).removeClassName("image_correct");
                    overheadImageDivMap.get(image).addClassName("image_wrong");
                }
                image.addClassName("image_transparent");
            }
        });
    }

    private void showNextImage() {
        currentImage.setSrc(imageManager.getNextImageSrc());

        if (index == overheadImages.size() - 1) {
            index = 0;
        } else {
            index++;
        }

        overheadImages.get(index).removeClassNames("image_wrong", "image_correct", "image_unseen");
    }

    private void showPreviousImage() {
        currentImage.setSrc(imageManager.getPreviousImageSrc());
        if (index == 0) {
            index = overheadImages.size() - 1;
        } else {
            index--;
        }
        overheadImages.get(index).removeClassNames("image_wrong", "image_correct", "image_unseen");
    }

    @Override
    public void handleInput(String command) {

        if (command.equals(Tag.NEXT_BUTTON)) {
            showNextImage();
            updateOverviewImages();
        } else if (command.equals(Tag.PREVIOUS_BUTTON)) {
            showPreviousImage();
            updateOverviewImages();
        } else if (command.contains("P")) {
            //TODO: Handle new values

            updateOverviewImages();
        }
    }
}