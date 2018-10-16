package com.ixdproject.madam.ui;

import com.vaadin.flow.component.Component;

public interface MainLayoutI {
    void switchToVideoView();
    void switchToGuessingGame();
    void switchToTuningGame();
    void add(Component... components);
    void removeAll();
}
