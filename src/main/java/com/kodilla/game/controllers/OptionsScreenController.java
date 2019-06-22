package com.kodilla.game.controllers;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import javafx.scene.control.Slider;

import static com.kodilla.game.main.Main.mp;
import static com.kodilla.game.main.Main.musicPlayer;

public class OptionsScreenController {

    public CheckBox enableMusicCheckBox;
    public Slider volumeSlider;
    public Button backButton;
    private MainScreenController mainScreenController;

    @FXML
    public void initialize() {
        volumeSlider.setValue(mp.getVolume() *100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mp.setVolume(volumeSlider.getValue() / 100);
            }
        });
        enableMusicCheckBox.setSelected(true);
        enableMusicCheckBox.addEventHandler(ActionEvent.ACTION, checkBoxHandler);
    }

    @FXML
    public void backButtonAction() {
        mainScreenController.loadMenuScreen();
    }

    EventHandler<ActionEvent> checkBoxHandler = event -> {
        CheckBox checkBox = (CheckBox) event.getSource();
        if (checkBox.isSelected()){
            musicPlayer(true);
        } else {
            musicPlayer(false);
        }
    };

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }
}
