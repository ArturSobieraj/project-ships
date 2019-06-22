package com.kodilla.game.controllers;

import com.kodilla.game.supportClasses.DialogsUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class NewGameScreenController {

    private MainScreenController mainScreenController;
    private GameScreenController gameScreenController;

    @FXML
    private TextField textField;

    @FXML
    public void startNewGame() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/GameScreen.fxml"));
        Pane pane = null;
        try{
            pane = loader.load();
        } catch (IOException e) {
            e.getMessage();
        }
        gameScreenController = loader.getController();
        gameScreenController.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(pane);
        gameScreenController.playerNameLabel.setText(textField.getText());
        gameScreenController.setComputerButtonList();
        gameScreenController.setPlayersButtonList();
        gameScreenController.setComputersShips(7);
        gameScreenController.setPlayersShips(0);
        gameScreenController.setComputerStatsLabel();
        gameScreenController.setPlayerStatsLabel();
        gameScreenController.startBattleButton.addEventHandler(ActionEvent.ACTION, gameScreenController.startBattleHandler);
        gameScreenController.gameStatusLabel.setText("Select the position of ships");

    }

    @FXML
    public void backToMenuScreenAction() {
        mainScreenController.loadMenuScreen();
    }

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }
}
