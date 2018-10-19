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

import java.util.Timer;
import java.util.TimerTask;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Guessing Game")
public class GuessingGame extends VerticalLayout implements MuffinView {

    private ImageManager imageManager = new ImageManager();
    private Image currentImage = imageManager.getCurrentImage();
    private MainLayoutI mainLayout;
    private VaadinSession vaadinSession;
    private boolean gameOver = false;
    private Timer timer = new Timer();

    private Button muffinButton;
    private Button chihuahuaButton;
    private Button videoViewButton;

    public void showGuessingGame(MainLayoutI layout) {
        currentImage.setClassName("current_image");
        mainLayout = layout;
        H1 title = new H1("Muffin eller chihuahua?");
        title.addClassName("guessing-game__title");
        mainLayout.add(title, currentImage, initButtons());
    }

    public void setVaadinSession(VaadinSession vaadinSession) {
        this.vaadinSession = vaadinSession;
    }

    private void hideImage() {
        vaadinSession.access((Command) () -> timer.schedule(new TimerTask() {
            @Override
            public void run() {
                vaadinSession.lock();
                currentImage.removeClassName("fade");
                currentImage.addClassName("visible");
                vaadinSession.unlock();
            }
        }, 100));
        vaadinSession.access((Command) () -> timer.schedule(new TimerTask() {
            @Override
            public void run() {
                vaadinSession.lock();
                currentImage.addClassName("fade");
                currentImage.removeClassName("visible");
                vaadinSession.unlock();
            }
        }, 500));
    }

    private Div initButtons() {
        muffinButton = new Button("Muffin", event -> {
            isCorrectGuess(imageManager.isCorrectGuess(Tag.MUFFIN));
            currentImage.setSrc(imageManager.getNextImageSrc());
            hideImage();
        });
        //muffinButton.addClassName("invisible");

        chihuahuaButton = new Button("Chihuahua", event -> {
            isCorrectGuess(imageManager.isCorrectGuess(Tag.CHIHUAHUA));
            currentImage.setSrc(imageManager.getNextImageSrc());
            hideImage();
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
            gameOver();
        }
    }

    private void gameOver() {
        mainLayout.removeAll();
        mainLayout.add(endView());
        gameOver = true;
    }

    //TODO: Might want to make it look prettier
    private Div endView() {
        Div div = new Div();
        div.addClassName("congrats_div");

        int finalScore = imageManager.getFinalScore();

        H1 congratsText = new H1("Du fick " + finalScore + " poäng! \n");
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
