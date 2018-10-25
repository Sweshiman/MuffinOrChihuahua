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
    private VaadinSession vaadinSession = VaadinSession.getCurrent();

    public MainLayout() {
        addClassNames("main-layout", "max-width");

        GuessingGame guessingGame = new GuessingGame();
        arduinoReader = new ArduinoReader(guessingGame);
        guessingGame.showGuessingGame(this);

        arduinoReader.start();
    }

    public void switchToGuessingGame() {
        this.removeAll();
        addClassName("max-width");

        GuessingGame guessingGame = new GuessingGame();
        arduinoReader.changeView(guessingGame);
        guessingGame.showGuessingGame(this);
    }

    public void switchToVideoView() {
        this.removeAll();
        removeClassName("max-width");
        arduinoReader.write("V".getBytes());

        VideoView videoView = new VideoView();
        arduinoReader.changeView(videoView);
        videoView.showVideoView(this);
    }

    public void switchToTuningGame() {
        this.removeAll();
        addClassName("max-width");
        arduinoReader.write("T".getBytes());

        TuningGame tuningGame = new TuningGame();
        arduinoReader.changeView(tuningGame);
        tuningGame.showTuningGame(this);
    }

    @Override
    public VaadinSession getVaadinSession() {
        return vaadinSession;
    }

    public ArduinoReader getArduinoReader() {
        return arduinoReader;
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
