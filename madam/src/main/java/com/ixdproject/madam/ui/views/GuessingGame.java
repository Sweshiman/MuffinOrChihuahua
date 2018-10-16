package com.ixdproject.madam.ui.views;

import com.ixdproject.madam.backend.ImageManager;
import com.ixdproject.madam.backend.Tag;
import com.ixdproject.madam.ui.MainLayoutI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.ixdproject.madam.ui.MainLayout;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Guessing Game")
public class GuessingGame extends VerticalLayout implements MuffinView {

    private ImageManager imageManager = new ImageManager();
    private Image currentImage = imageManager.getCurrentImage();
    private Button muffinButton;
    private Button chihuahuaButton;
    private Button videoViewButton;
    private MainLayoutI mainLayout;
    private VaadinSession vaadinSession;
    private boolean gameOver = false;

    public void setVaadinSession(VaadinSession vaadinSession) {
        this.vaadinSession = vaadinSession;
    }

    public void showGuessingGame(MainLayoutI layout) {
        currentImage.setClassName("current_image");
        mainLayout = layout;
        mainLayout.add(currentImage, initButtons());
    }

    private Div initButtons() {
        muffinButton = new Button("Muffin", event -> {
            isCorrectGuess(imageManager.isCorrectGuess(Tag.MUFFIN));
            currentImage.setSrc(imageManager.getNextImageSrc());
        });
        //muffinButton.addClassName("invisible");

        chihuahuaButton = new Button("Chihuahua", event -> {
            isCorrectGuess(imageManager.isCorrectGuess(Tag.CHIHUAHUA));
            currentImage.setSrc(imageManager.getNextImageSrc());
        });
        //chihuahuaButton.addClassName("invisible");

        videoViewButton = new Button("Video view", event -> {
            mainLayout.switchToVideoView();
        });
        //videoViewButton.addClassName("invisible");

        return new Div(muffinButton, chihuahuaButton, videoViewButton);
    }

    private void isCorrectGuess(boolean isCorrect) {
        imageManager.setIsCorrect(imageManager.getCurrentImg(), isCorrect);
        if (isCorrect) {
            //TODO: Add animation
            System.out.println("You are correct!");
        } else {
            //TODO: Add animation
            System.out.println("You are wrong.");
        }

        if (imageManager.isLastImage()) {
            mainLayout.removeAll();
            mainLayout.add(endView());
            gameOver = true;
        }
    }

    //TODO: Might want to make it look prettier
    private Div endView() {
        Div div = new Div();
        div.addClassName("congrats_div");

        H1 congratsText = new H1("Du fick " + imageManager.getFinalScore() + " poäng! \n");
        H4 toVideoViewText = new H4("Klicka på 'nästa' för att gå vidare");

        div.add(congratsText, toVideoViewText);
        return div;
    }

    @Override
    public void handleInput(String command) {
        if (command.equals(Tag.MUFFIN)) {
            vaadinSession.access((Command) () -> muffinButton.click());
        } else if (command.equals(Tag.CHIHUAHUA)) {
            vaadinSession.access((Command) () -> chihuahuaButton.click());
        } else if (command.equals(Tag.NEXT_BUTTON) && gameOver) {
            vaadinSession.access((Command) () -> videoViewButton.click());
        }
    }
}
