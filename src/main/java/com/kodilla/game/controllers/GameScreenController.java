package com.kodilla.game.controllers;

import com.kodilla.game.supportClasses.DialogsUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.util.*;

public class GameScreenController {

    private static final String ICONS_SHIP_ICON_PNG = "/icons/shipIcon.png";
    private static final String ICONS_SUNKED_SHIP_ICON_PNG = "/icons/sunkedShipIcon.png";
    private static final String ICONS_MISHIT_ICON_PNG = "/icons/mishitIcon.png";
    private static final String FXML_NEW_GAME_SCREEN_FXML = "/fxml/NewGameScreen.fxml";
    private MainScreenController mainScreenController;
    private HashMap<Button, Boolean> playersButtonsList = new HashMap<>();
    private HashMap<Button, Boolean> computerButtonsList = new HashMap<>();
    private HashMap<Button, String> playersButtonsText = new HashMap<>();
    private HashMap<Button, String> computerButtonsText = new HashMap<>();
    private List<Button> computerButtonsObjectsList = new ArrayList<>();
    private List<Button> playerButtonsObjectsList = new ArrayList<>();
    private List<Integer> computerRandomInts = new ArrayList<>();
    private Image sunkedShip = new Image(this.getClass().getResourceAsStream(ICONS_SUNKED_SHIP_ICON_PNG));
    private Image misHit = new Image(this.getClass().getResourceAsStream(ICONS_MISHIT_ICON_PNG));
    private int playersShips;
    private int computersShips;
    private Random randomGen = new Random();
    private Integer randomed;

    @FXML
    public Label playerNameLabel;
    public Label playerStatsLabel;
    public Label computerStatsLabel;
    public Label gameStatusLabel;
    public Button startBattleButton;
    public Button endGameButton;
    public Button pa1, pa2, pa3, pa4, pa5, pb1, pb2, pb3, pb4, pb5, pc1, pc2, pc3, pc4, pc5,
            pd1, pd2, pd3, pd4, pd5, pe1, pe2, pe3, pe4, pe5;
    public Button ca1, ca2, ca3, ca4, ca5, cb1, cb2, cb3, cb4, cb5, cc1, cc2, cc3, cc4, cc5,
            cd1, cd2, cd3, cd4, cd5, ce1, ce2, ce3, ce4, ce5;

    @FXML
    protected void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    private void gameEndAction() {
        Optional<ButtonType> result = DialogsUtils.closeApplicationConfiramtionDialog();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    @FXML
    protected void setComputerStatsLabel() {
        computerStatsLabel.setText("Computers ships: " + computersShips);
    }

    @FXML
    protected void setPlayerStatsLabel() {
        playerStatsLabel.setText("Players ships: " + playersShips);
    }

    private EventHandler<ActionEvent> playersButtonsHandler = event -> {
        Button button = (Button) event.getSource();
        if (!playersButtonsList.get(button) && playersShips < 7) {
            Image image = new Image(this.getClass().getResourceAsStream(ICONS_SHIP_ICON_PNG));
            button.setGraphic(new ImageView(image));
            button.setText(null);
            playersButtonsList.put(button, true);
            playersShips++;
            setPlayerStatsLabel();
        } else if (playersButtonsList.get(button)) {
            playersShips--;
            button.setGraphic(null);
            button.setText(playersButtonsText.get(button));
            setPlayerStatsLabel();
            playersButtonsList.put(button, false);
        }
    };

    private EventHandler<ActionEvent> attackHandler = event ->  {
        Button button = (Button) event.getSource();
        computerAttack();
        if (computerButtonsList.get(button)) {
            button.setGraphic(new ImageView(sunkedShip));
            button.setText(null);
            computersShips--;
            setComputerStatsLabel();
            gameStatusLabel.setText("It's a hit!");
        } else {
            button.setGraphic(new ImageView(misHit));
            button.setText(null);
            gameStatusLabel.setText("You missed!");
        }
        winChecker();
    };

    protected EventHandler<ActionEvent> startBattleHandler = event ->  {

        if (playersShips != 7) {
            DialogsUtils.notAllShipsSelectedAllert();
        }
        if (playersShips == 7) {
            Optional<ButtonType> result = DialogsUtils.startGameConfirmationDialog();
            if (result.get() == ButtonType.OK) {
                for (Map.Entry<Button, Boolean> playerEntry : playersButtonsList.entrySet()) {
                    playerEntry.getKey().removeEventHandler(ActionEvent.ACTION, playersButtonsHandler);
                }
                for (Map.Entry<Button, Boolean> computerEntry : computerButtonsList.entrySet()) {
                    computerEntry.getKey().addEventHandler(ActionEvent.ACTION, attackHandler);
                }
                computerStarting();
                gameStatusLabel.setText("Battle begins!");
            }
        }
    };

    private void computerStarting() {
        startBattleButton.removeEventHandler(ActionEvent.ACTION, startBattleHandler);
        for (int i = 0; i < computersShips; i++) {
            Button button = computerButtonsObjectsList.get(randomGen.nextInt(25));
            if (!computerButtonsList.get(button)) {
                computerButtonsList.put(button, true);
            } else
                i--;
        }
    }

    private void computerAttack() {
        randomed = randomGen.nextInt(25);
        while (computerRandomInts.contains(randomed)) {
            randomed = randomGen.nextInt(25);
        }
        computerRandomInts.add(randomed);
        Button button = playerButtonsObjectsList.get(randomed);
        if (playersButtonsList.get(button)) {
            button.setGraphic(new ImageView(sunkedShip));
            button.setText(null);
            playersShips--;
            setPlayerStatsLabel();
        } else {
            button.setGraphic(new ImageView(misHit));
            button.setText(null);
        }
    }
    private void winChecker() {
        if (computersShips == 0) {
            DialogsUtils.whoWonInformationDialog(true);
            afterBattleDialog();
        }
        if (playersShips == 0) {
            DialogsUtils.whoWonInformationDialog(false);
            afterBattleDialog();
        }
    }

    private void afterBattleDialog() {
        Optional<ButtonType> result = DialogsUtils.newGameAfterBattleDialog();
        if (result.get() == ButtonType.OK) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(FXML_NEW_GAME_SCREEN_FXML));
            Pane pane = null;
            try {
                pane = loader.load();
            } catch (IOException e) {
                e.getMessage();
            }
            NewGameScreenController newGameScreenController = loader.getController();
            newGameScreenController.setMainScreenController(mainScreenController);
            mainScreenController.setScreen(pane);
        } else  {
            Platform.exit();
            System.exit(0);
        }
    }

    public void setPlayersShips(int playersShips) {
        this.playersShips = playersShips;
    }

    public void setComputersShips(int computersShips) {
        this.computersShips = computersShips;
    }

    public void setPlayersButtonList() {
        playersButtonsList.put(pa1, false);
        playersButtonsList.put(pa2, false);
        playersButtonsList.put(pa3, false);
        playersButtonsList.put(pa4, false);
        playersButtonsList.put(pa5, false);
        playersButtonsList.put(pb1, false);
        playersButtonsList.put(pb2, false);
        playersButtonsList.put(pb3, false);
        playersButtonsList.put(pb4, false);
        playersButtonsList.put(pb5, false);
        playersButtonsList.put(pc1, false);
        playersButtonsList.put(pc2, false);
        playersButtonsList.put(pc3, false);
        playersButtonsList.put(pc4, false);
        playersButtonsList.put(pc5, false);
        playersButtonsList.put(pd1, false);
        playersButtonsList.put(pd2, false);
        playersButtonsList.put(pd3, false);
        playersButtonsList.put(pd4, false);
        playersButtonsList.put(pd5, false);
        playersButtonsList.put(pe1, false);
        playersButtonsList.put(pe2, false);
        playersButtonsList.put(pe3, false);
        playersButtonsList.put(pe4, false);
        playersButtonsList.put(pe5, false);

        for (Map.Entry<Button, Boolean> entry : playersButtonsList.entrySet()) {
            playersButtonsText.put(entry.getKey(), entry.getKey().getText());
            playerButtonsObjectsList.add(entry.getKey());
            entry.getKey().addEventHandler(ActionEvent.ACTION, playersButtonsHandler);
        }
    }
    public void setComputerButtonList() {
        computerButtonsList.put(ca1, false);
        computerButtonsList.put(ca2, false);
        computerButtonsList.put(ca3, false);
        computerButtonsList.put(ca4, false);
        computerButtonsList.put(ca5, false);
        computerButtonsList.put(cb1, false);
        computerButtonsList.put(cb2, false);
        computerButtonsList.put(cb3, false);
        computerButtonsList.put(cb4, false);
        computerButtonsList.put(cb5, false);
        computerButtonsList.put(cc1, false);
        computerButtonsList.put(cc2, false);
        computerButtonsList.put(cc3, false);
        computerButtonsList.put(cc4, false);
        computerButtonsList.put(cc5, false);
        computerButtonsList.put(cd1, false);
        computerButtonsList.put(cd2, false);
        computerButtonsList.put(cd3, false);
        computerButtonsList.put(cd4, false);
        computerButtonsList.put(cd5, false);
        computerButtonsList.put(ce1, false);
        computerButtonsList.put(ce2, false);
        computerButtonsList.put(ce3, false);
        computerButtonsList.put(ce4, false);
        computerButtonsList.put(ce5, false);

        for (Map.Entry<Button, Boolean> entry : computerButtonsList.entrySet()) {
            computerButtonsText.put(entry.getKey(), entry.getKey().getText());
            computerButtonsObjectsList.add(entry.getKey());
        }
    }
}
