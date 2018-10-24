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
@PageTitle("Muffin or chihuahua")
public class GuessingGame extends VerticalLayout implements MuffinView {

    private ImageManager imageManager = new ImageManager();
    private Image currentImage = new Image(imageManager.getRandomImage(), "");
    private MainLayoutI mainLayout;
    private VaadinSession vaadinSession;
    private boolean isGameOver = false;
    private Timer timer = new Timer();

    private Button muffinButton;
    private Button chihuahuaButton;
    private Button videoViewButton;
    private Button tuningGameButton;

    public void showGuessingGame(MainLayoutI layout) {
        currentImage.setClassName("current_image");
        mainLayout = layout;

        H1 title = new H1("Muffin eller chihuahua?");
        title.addClassNames("guessing-game__title", "text__center");

        mainLayout.add(title, currentImage, initButtons());
    }

    public void setVaadinSession(VaadinSession vaadinSession) {
        this.vaadinSession = vaadinSession;
    }

    private void fadeImage() {
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
            imageManager.isCorrectGuessingGameGuess(Tag.MUFFIN);
            if (imageManager.isLastGuessingGameImage()) {
                gameOver();
            } else {
                currentImage.setSrc(imageManager.getRandomImage());
                fadeImage();
            }
        });
        //muffinButton.addClassName("invisible");

        chihuahuaButton = new Button("Chihuahua", event -> {
            imageManager.isCorrectGuessingGameGuess(Tag.CHIHUAHUA);
            if (imageManager.isLastGuessingGameImage()) {
                gameOver();
            } else {
                currentImage.setSrc(imageManager.getRandomImage());
                fadeImage();
            }
        });
        //chihuahuaButton.addClassName("invisible");

        videoViewButton = new Button("Video view", event -> mainLayout.switchToVideoView());
        tuningGameButton = new Button("Tuning game", event -> mainLayout.switchToTuningGame());

        return new Div(muffinButton, chihuahuaButton, videoViewButton, tuningGameButton);
    }

    private void gameOver() {
        mainLayout.removeAll();
        mainLayout.add(endView());
        isGameOver = true;
    }

    private Div endView() {
        Div div = new Div();
        div.addClassName("congrats_div");

        int finalScore = imageManager.getFinalGuessingGameScore();

        H1 congratsText = new H1("Du fick " + finalScore + " poäng! \n");
        H4 toVideoViewText = new H4("Klicka på 'nästa' för att gå vidare");
        toVideoViewText.setClassName("text__center");

        div.add(congratsText, toVideoViewText);
        return div;
    }

    @Override
    public void handleInput(String command) {
        if (command.equals(Tag.MUFFIN)) {
            vaadinSession.access((Command) () -> muffinButton.click());
        } else if (command.equals(Tag.CHIHUAHUA)) {
            vaadinSession.access((Command) () -> chihuahuaButton.click());
        } else if (command.equals(Tag.NEXT_BUTTON) && isGameOver) {
            vaadinSession.access((Command) () -> videoViewButton.click());
        }
    }
}
