package com.ixdproject.madam.ui.views;

import com.ixdproject.madam.ui.MainLayout;
import com.ixdproject.madam.ui.MainLayoutI;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;

@Route(value = "video", layout = MainLayout.class)
@PageTitle("Video")
public class VideoView extends VerticalLayout implements MuffinView {

    public void showVideoView(MainLayoutI mainLayout) {
        Html video = new Html(
                "<video " +
                        "width=\"100%\" " +
                        "height=\"auto\" autoplay><source " +
                        "src=\"/frontend/vid/vid.mp4\" " +
                        "type=\"video/mp4\"></video>");

        Button guessingGameButton = new Button("Guessing game", event -> mainLayout.switchToGuessingGame());
        Button tuningGameButton = new Button("Tuning game", event -> mainLayout.switchToTuningGame());

        mainLayout.add(video, new Div(guessingGameButton, tuningGameButton));
    }

    @Override
    public void handleInput(String command) {

    }
}