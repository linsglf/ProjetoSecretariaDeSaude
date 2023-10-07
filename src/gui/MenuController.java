package gui;

import gui.util.Alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class MenuController {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private MenuItem menuCadastroMedico;
    @FXML
    private MenuItem menuEditarMunicipio;

    @FXML
    public void initialize() {
    }

    @FXML
    private void menuCadastrarMedicoAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CadastroMedicoView.fxml"));
            Parent root = loader.load();

            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(root);
        } catch (IOException e) {
            Alerts.showAlert("Error", "Erro ao carregar a tela", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void menuEditarMunicipioAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MunicipioView.fxml"));
            Parent root = loader.load();

            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(root);
        } catch (IOException e) {
            Alerts.showAlert("Error", "Erro ao carregar a tela", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
