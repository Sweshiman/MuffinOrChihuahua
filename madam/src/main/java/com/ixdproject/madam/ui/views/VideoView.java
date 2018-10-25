package com.ixdproject.madam.ui.views;

import com.ixdproject.madam.backend.Tag;
import com.ixdproject.madam.ui.MainLayout;
import com.ixdproject.madam.ui.MainLayoutI;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Command;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;

import java.util.Timer;
import java.util.TimerTask;

@Route(value = "video", layout = MainLayout.class)
@PageTitle("Video")
public class VideoView extends VerticalLayout implements MuffinView {

    private VaadinSession vaadinSession;
    private Timer timer = new Timer();
    private MainLayoutI mainLayout;
    private boolean isSkippable = true;

    public void showVideoView(MainLayoutI mainLayout) {
        vaadinSession = mainLayout.getVaadinSession();
        this.mainLayout = mainLayout;

        vaadinSession.access((Command) () -> timer.schedule(new TimerTask() {
            @Override
            public void run() {
                vaadinSession.lock();
                Html video = new Html(
                        "<video " +
                                "width=\"100%\" " +
                                "height=\"100%\" autoplay><source " +
                                "src=\"/frontend/vid/vid.mp4\" " +
                                "type=\"video/mp4\"></video>");

                mainLayout.add(video);
                vaadinSession.unlock();
            }
        }, 300));

        vaadinSession.access((Command) () -> timer.schedule(new TimerTask() {
            @Override
            public void run() {
                vaadinSession.lock();
                mainLayout.switchToTuningGame();
                vaadinSession.unlock();
            }
        }, 75 * 1000));

        vaadinSession.access((Command) () -> timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isSkippable = true;
            }
        }, 5 * 1000));
    }

    @Override
    public void handleInput(String command) {
        if (command.equals(Tag.RESET)) {
            vaadinSession.access((Command) () -> {
                timer.cancel();
                mainLayout.switchToGuessingGame();
            });
        } else if (isSkippable && command.equals(Tag.NEXT_BUTTON)) {
            vaadinSession.access((Command) () -> {
                timer.cancel();
                mainLayout.switchToTuningGame();
            });
        }
    }
}