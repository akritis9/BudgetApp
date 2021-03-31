package ui.buttons;

import ui.Gui;

import java.io.IOException;

// represents a button on the Gui
public abstract class Button {
    private Gui controller;

    //REQUIRES: Gui controller that holds this tab
    // EFFECTS: constructs a button for gui
    public Button(Gui controller) {
        this.controller = controller;
    }

    public Button() throws IOException {
    }

    //EFFECTS: returns the Gui controller for this tab
    public Gui getController() {
        return controller;
    }
}
