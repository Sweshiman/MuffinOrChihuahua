package com.ixdproject.madam.ui;

import com.ixdproject.madam.backend.ArduinoReader;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.server.VaadinSession;

public interface MainLayoutI {
    void switchToVideoView();
    void switchToTuningGame();
    VaadinSession getVaadinSession();
    ArduinoReader getArduinoReader();
    void add(Component... components);
    void removeAll();
}
