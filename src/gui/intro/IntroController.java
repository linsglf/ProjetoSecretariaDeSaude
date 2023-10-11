package gui.intro;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class IntroController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnEntrar;

    @FXML
    public void initialize() {
    }

    @FXML
    public void btnEntrarAction() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/menu/PrimaryScene.fxml"));
            Parent root = loader.load();

            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(root);
        } catch (IOException e) {
            Alerts.showAlert("Error", "Erro ao carregar a tela1", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
