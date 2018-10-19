package com.ixdproject.madam.ui.views;

import com.ixdproject.madam.ui.MainLayout;
import com.ixdproject.madam.ui.MainLayoutI;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@HtmlImport("frontend://src/views/video.html")
@Route(value = "video", layout = MainLayout.class)
@PageTitle("Video")
public class VideoView extends VerticalLayout implements MuffinView {

    public void showVideoView(MainLayoutI mainLayout) {

    }

    @Override
    public void handleInput(String command) {
        System.out.println(command);
    }
}
