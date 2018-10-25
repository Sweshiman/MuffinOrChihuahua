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

import java.util.*;

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
    private MainLayoutI mainLayout;
    private boolean dogFlag;
    private boolean isNewImage = true;
    private HashMap<Image, Boolean> correctImages = new HashMap<>();
    private Timer timer = new Timer();
    private boolean isGameOver = false;
    private boolean isPreGame = true;
    private Image preGameImage;

    public void showTuningGame(MainLayoutI mainLayout) {
        currentImage.setClassName("current_image");
        vaadinSession = mainLayout.getVaadinSession();
        this.mainLayout = mainLayout;

        images.forEach(image -> correctImages.put(image, false));

        preGameImage = new Image("frontend/img/before_game.png", "");
        preGameImage.setClassName("pregame__image");

        mainLayout.add(preGameImage, initOverheadImages(), currentImage);
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
        updateOverviewImages();

        return imageOverview;
    }

    private void updateOverviewImages() {
        images.forEach(image -> {
            if (!image.hasClassName("image_unseen")) {
                Div div = overheadImageDivMap.get(image);
                boolean isDog = tuningValues.isDog(imageValues.getImageValues(imageManager.getImg(image.getSrc())));
                boolean isCorrect = imageManager.isDogImage(image.getSrc()) == isDog;
                correctImages.put(image, isCorrect);

                if (image.getSrc().equals(currentImage.getSrc())) {
                    vaadinSession.access((Command) () -> overheadImageDivMap.get(image).addClassName("image_focus"));
                    if (isNewImage || dogFlag != isDog) {
                        isNewImage = false;
                        dogFlag = isDog;
                        if (isDog) {
                            mainLayout.getArduinoReader().write("D".getBytes());
                        } else {
                            mainLayout.getArduinoReader().write("M".getBytes());
                        }
                    }
                } else {
                    vaadinSession.access((Command) () -> overheadImageDivMap.get(image).removeClassName("image_focus"));
                }

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
            }
        });
        boolean isDone = true;
        for (Map.Entry<Image, Boolean> entry : correctImages.entrySet()) {
            if (!entry.getValue() || entry.getKey().hasClassName("image_unseen")) {
                isDone = false;
            }
        }
        if (isDone) {
            vaadinSession.access((Command) () -> timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    vaadinSession.lock();
                    mainLayout.removeAll();
                    mainLayout.removeCssClass("max-width");
                    mainLayout.add(new Image("frontend/img/after_game.png", ""));
                    vaadinSession.unlock();
                }
            }, 3 * 1000));
            isGameOver = true;
        }
    }

    private void showNextImage() {
        if (index == images.size() - 1) {
            index = 0;
        } else {
            index++;
        }

        mainLayout.getArduinoReader().write("C".getBytes());
        isNewImage = true;

        updateImage();
    }

    private void updateImage() {
        Image imageToShow = images.get(index);
        vaadinSession.access((Command) () -> {
            vaadinSession.lock();
            currentImage.setSrc(imageToShow.getSrc());
            imageToShow.removeClassNames("image_wrong", "image_correct", "image_unseen");
            vaadinSession.unlock();
        });
        updateOverviewImages();
    }

    private void showPreviousImage() {
        if (index == 0) {
            index = images.size() - 1;
        } else {
            index--;
        }

        mainLayout.getArduinoReader().write("C".getBytes());
        isNewImage = true;

        updateImage();
    }

    @Override
    public void handleInput(String command) {
        if (isPreGame) {
            if (command.equals(Tag.NEXT_BUTTON)) {
                vaadinSession.access((Command) () -> {
                    mainLayout.addCssClass("max-width");
                    mainLayout.removeComponent(preGameImage);
                    isPreGame = false;
                });
            }
        } else {
            if (command.equals(Tag.NEXT_BUTTON) && !isGameOver) {
                showNextImage();
            } else if (command.equals(Tag.PREVIOUS_BUTTON) && !isGameOver) {
                showPreviousImage();
            } else if (command.contains("P") && !isGameOver) {
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

        if (command.equals(Tag.RESET)) {
            vaadinSession.access((Command) () -> mainLayout.switchToGuessingGame());
        }
    }
}