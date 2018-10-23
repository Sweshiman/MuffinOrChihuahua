package com.ixdproject.madam.ui.views;

import com.ixdproject.madam.backend.ImageManager;
import com.ixdproject.madam.backend.ImageValues;
import com.ixdproject.madam.backend.Tag;
import com.ixdproject.madam.backend.MufChiValues;
import com.ixdproject.madam.ui.MainLayoutI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.ixdproject.madam.ui.MainLayout;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.server.VaadinSession;

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
    private HashMap<String, MufChiValues> imageTunings = new HashMap<>();
    private ImageValues imageValues = new ImageValues();
    private VaadinSession vaadinSession;

    public void showTuningGame(MainLayoutI mainLayout) {
        currentImage.setClassName("current_image");

        mainLayout.add(
                initOverheadImages(),
                currentImage

                // ----- Remove after debug
                , new Button("Previous", event -> {
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

    public void setVaadinSession(VaadinSession vaadinSession) {
        this.vaadinSession = vaadinSession;
    }

    private Div initOverheadImages() {
        Div imageOverview = new Div();
        overheadImages.forEach(image -> {
            image.addClassName("image");
            if (!image.getSrc().equals(imageManager.getFirstImage().getSrc())) {
                image.addClassName("image_unseen");
            }
            imageTunings.put(image.getSrc(), new MufChiValues(0, 0, 0));

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
                if (imageTunings.get(image.getSrc()).isCorrect(imageValues.getImageValues(imageManager.getImg(image.getSrc())))) {
                    vaadinSession.access((Command) () -> {
                        overheadImageDivMap.get(image).removeClassName("image_wrong");
                        overheadImageDivMap.get(image).addClassName("image_correct");
                    });
                } else {
                    vaadinSession.access((Command) () -> {
                        overheadImageDivMap.get(image).removeClassName("image_correct");
                        overheadImageDivMap.get(image).addClassName("image_wrong");
                    });
                }
                vaadinSession.access((Command) () -> image.addClassName("image_transparent"));
            }
        });
    }

    private void showNextImage() {
        vaadinSession.access((Command) () ->
                currentImage.setSrc(imageManager.getNextImageSrc()));

        if (index == overheadImages.size() - 1) {
            index = 0;
        } else {
            index++;
        }

        vaadinSession.access((Command) () ->
                overheadImages.get(index).removeClassNames("image_wrong", "image_correct", "image_unseen"));
    }

    private void showPreviousImage() {
        vaadinSession.access((Command) () ->
                currentImage.setSrc(imageManager.getPreviousImageSrc()));

        if (index == 0) {
            index = overheadImages.size() - 1;
        } else {
            index--;
        }
        vaadinSession.access((Command) () ->
                overheadImages.get(index).removeClassNames("image_wrong", "image_correct", "image_unseen"));
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
            String[] values = command.split(":");
            int value = Integer.parseInt(values[1]);

            switch (values[0]) {
                case "P0":
                    imageTunings.get(currentImage.getSrc()).setFluffinessValue(value);
                    break;
                case "P1":
                    imageTunings.get(currentImage.getSrc()).setRoundnessValue(value);
                    break;
                default:
                    imageTunings.get(currentImage.getSrc()).setColorValue(value);
                    break;
            }
            updateOverviewImages();
        }
    }
}