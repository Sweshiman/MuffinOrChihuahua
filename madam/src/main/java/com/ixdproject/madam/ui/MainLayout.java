package com.ixdproject.madam.ui;

import com.ixdproject.madam.backend.ArduinoReader;
import com.ixdproject.madam.ui.views.GuessingGame;
import com.ixdproject.madam.ui.views.TuningGame;
import com.ixdproject.madam.ui.views.VideoView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.server.VaadinSession;

@HtmlImport("frontend://styles/shared-styles.html")
@Push
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainLayout extends Div
        implements RouterLayout, PageConfigurator, MainLayoutI {

    private ArduinoReader arduinoReader;

    public MainLayout() {
        addClassName("main-layout");

        /*TuningGame tuningGame = new TuningGame();
        arduinoReader = new ArduinoReader(tuningGame);
        tuningGame.showTuningGame(this);*/

        GuessingGame guessingGame = new GuessingGame();
        guessingGame.setVaadinSession(VaadinSession.getCurrent());
        arduinoReader = new ArduinoReader(guessingGame);
        guessingGame.showGuessingGame(this);

        //arduinoReader.start();
    }

    public void switchToVideoView() {
        this.removeAll();

        VideoView videoView = new VideoView();
        arduinoReader.changeView(videoView);
        videoView.showVideoView(this);
    }

    public void switchToGuessingGame() {
        this.removeAll();

        GuessingGame guessingGame = new GuessingGame();
        guessingGame.setVaadinSession(VaadinSession.getCurrent());
        arduinoReader.changeView(guessingGame);
        guessingGame.showGuessingGame(this);
    }

    public void switchToTuningGame() {
        this.removeAll();

        TuningGame tuningGame = new TuningGame();
        arduinoReader.changeView(tuningGame);
        tuningGame.showTuningGame(this);
    }

    @Override
    public void add(Component... components) {
        super.add(components);
    }

    @Override
    public void removeAll() {
        super.removeAll();
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
    }
}
