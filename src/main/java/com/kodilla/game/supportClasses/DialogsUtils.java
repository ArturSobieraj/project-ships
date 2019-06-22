package com.kodilla.game.supportClasses;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.Optional;



public class DialogsUtils {

    public static Optional<ButtonType> closeApplicationConfiramtionDialog() {
        Alert closeApplicationConfirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        closeApplicationConfirmationDialog.setTitle("Exit game confirmation");
        closeApplicationConfirmationDialog.setHeaderText("Are you sure you want to exit game?");
        closeApplicationConfirmationDialog.setGraphic(new ImageView(new Image(DialogsUtils.class.getResourceAsStream("/icons/questionIcon.png"))));
        Optional<ButtonType> result = closeApplicationConfirmationDialog.showAndWait();
        return result;
    }
    public static Optional<ButtonType> startGameConfirmationDialog() {
        Alert startGameConfirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        startGameConfirmationDialog.setTitle("Start game!");
        startGameConfirmationDialog.setHeaderText("Are you ready for battle?");
        startGameConfirmationDialog.setGraphic(new ImageView(new Image(DialogsUtils.class.getResourceAsStream("/icons/cannonIcon.png"))));
        Optional<ButtonType> result = startGameConfirmationDialog.showAndWait();
        return result;
    }
    public static void notAllShipsSelectedAllert() {
        Alert notAllShipsSlectedAllert = new Alert(Alert.AlertType.WARNING);
        notAllShipsSlectedAllert.setTitle("Warning!");
        notAllShipsSlectedAllert.setHeaderText("Not all ships have been sent to fight!");
        notAllShipsSlectedAllert.setGraphic(new ImageView(new Image(DialogsUtils.class.getResourceAsStream("/icons/pirateSkullIcon.png"))));
        notAllShipsSlectedAllert.showAndWait();
    }
    public static void whoWonInformationDialog(Boolean whoWon) {
        Alert whoWonInformationDialog = new Alert(Alert.AlertType.INFORMATION);
        whoWonInformationDialog.setTitle("Game Over");
        if (whoWon) {
            whoWonInformationDialog.setHeaderText("You have won!");
            whoWonInformationDialog.setGraphic(new ImageView(new Image(DialogsUtils.class.getResourceAsStream("/icons/fireworksIcon.png"))));
        } else if (!whoWon) {
            whoWonInformationDialog.setHeaderText("Computer have won!");
            whoWonInformationDialog.setGraphic(new ImageView(new Image(DialogsUtils.class.getResourceAsStream("/icons/defeatIcon.png"))));
        }
        whoWonInformationDialog.showAndWait();
    }
    public static Optional<ButtonType> newGameAfterBattleDialog() {
        Alert newGameAfterBattleDialog = new Alert(Alert.AlertType.CONFIRMATION);
        newGameAfterBattleDialog.setTitle("");
        newGameAfterBattleDialog.setHeaderText("Would you like to play another battle?");
        newGameAfterBattleDialog.setGraphic(new ImageView(new Image(DialogsUtils.class.getResourceAsStream("/icons/returningShipIcon.png"))));
        Optional<ButtonType> result = newGameAfterBattleDialog.showAndWait();
        return  result;
    }
}
