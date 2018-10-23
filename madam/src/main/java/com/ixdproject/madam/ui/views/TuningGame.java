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
    private List<Image> images = imageManager.getTuningGameImages();
    private int index = 0;
    private Image currentImage = imageManager.getFirstTuningGameImage();
    private HashMap<Image, Div> overheadImageDivMap = new HashMap<>();
    private MufChiValues tuningValues = new MufChiValues(0, 0, 0);
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
        images.forEach(image -> {
            image.addClassNames("image");
            if (!image.getSrc().equals(currentImage.getSrc())) {
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
        images.forEach(image -> {
            if (!image.hasClassName("image_unseen")) {
                Div div = overheadImageDivMap.get(image);

                boolean isCorrect = tuningValues.isCorrect(imageValues.getImageValues(imageManager.getImg(image.getSrc())));
                if (isCorrect) {
                    vaadinSession.access((Command) () -> {
                        div.removeClassName("image_wrong");
                        div.addClassName("image_correct");
                    });
                } else {
                    vaadinSession.access((Command) () -> {
                        div.removeClassName("image_correct");
                        div.addClassName("image_wrong");
                    });
                }

                //vaadinSession.access((Command) () -> image.addClassName("image_transparent"));
            }
        });
    }

    private void showNextImage() {
        if (index == images.size() - 1) {
            index = 0;
        } else {
            index++;
        }

        Image imageToShow = images.get(index);

        vaadinSession.access((Command) () -> {
            currentImage.setSrc(imageToShow.getSrc());
            imageToShow.removeClassNames("image_wrong", "image_correct", "image_unseen");
        });
    }

    private void showPreviousImage() {
        if (index == 0) {
            index = images.size() - 1;
        } else {
            index--;
        }

        Image imageToShow = images.get(index);

        vaadinSession.access((Command) () -> {
            currentImage.setSrc(imageToShow.getSrc());
            imageToShow.removeClassNames("image_wrong", "image_correct", "image_unseen");
        });
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
                    tuningValues.setFluffinessValue(value);
                    break;
                case "P1":
                    tuningValues.setRoundnessValue(value);
                    break;
                default:
                    tuningValues.setColorValue(value);
                    break;
            }
            updateOverviewImages();
        }
    }
}