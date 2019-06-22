package com.kodilla.game.controllers;

import com.kodilla.game.supportClasses.DialogsUtils;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.Optional;

public class MenuScreenController {

    private MainScreenController mainScreenController;

    @FXML
    public void openNewGame() {
        FXMLLoader loader = new FXMLLoader(MenuScreenController.class.getResource("/fxml/NewGameScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        NewGameScreenController newGameScreenController = loader.getController();
        newGameScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(pane);
    }

    @FXML
    public void openOptions() {
        FXMLLoader loader = new FXMLLoader(MenuScreenController.class.getResource("/fxml/OptionsScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.getMessage();
        }
        OptionsScreenController optionsScreenController = loader.getController();
        optionsScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(pane);
    }

    @FXML
    public void exit() {
        Optional<ButtonType> result = DialogsUtils.closeApplicationConfiramtionDialog();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }
}
