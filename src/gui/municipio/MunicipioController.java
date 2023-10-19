package gui.municipio;

import DAO.MunicipioDAO;
import DTO.MunicipioDTO;
import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.Toolkit;
import java.util.List;

public class MunicipioController {

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField municipioTxtField;
    @FXML
    private TableView<MunicipioDTO> tabelaMunicipio;
    @FXML
    private TableColumn<MunicipioDTO, String> municipioColumn;

    @FXML
    public void initialize() {
        listarValoresMunicipios();
    }

    @FXML
    public void btnCreateAction(ActionEvent event) {
        criarMunicipio();
        listarValoresMunicipios();
    }

    private void criarMunicipio() {
        String nomeMunicipio = municipioTxtField.getText();

        if (!nomeMunicipio.isEmpty()) {
            MunicipioDTO objMunicipioDTO = new MunicipioDTO();
            objMunicipioDTO.setNomeMunicipio(nomeMunicipio);

            MunicipioDAO objMunicipioDAO = new MunicipioDAO();
            objMunicipioDAO.insert(objMunicipioDTO);
        } else {
            Toolkit.getDefaultToolkit().beep();
            Alerts.showAlert("Error", null, "Preencha o campo", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void btnEditAction(ActionEvent event) {
        editarCampos();
    }

    private void editarCampos() {
        MunicipioDTO selectedMunicipio = tabelaMunicipio.getSelectionModel().getSelectedItem();

        if (selectedMunicipio != null) {
            municipioTxtField.setText(selectedMunicipio.getNomeMunicipio());
        } else {
            Toolkit.getDefaultToolkit().beep();
            Alerts.showAlert("Error", null, "Selecione um municipio para editar", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void btnUpdateAction(ActionEvent event) {
        updateMunicipio();
        listarValoresMunicipios();
    }

    private void updateMunicipio() {
        MunicipioDTO selectedMunicipio = tabelaMunicipio.getSelectionModel().getSelectedItem();
        MunicipioDTO objMunicipioDTO = new MunicipioDTO();
        MunicipioDAO objMunicipioDAO = new MunicipioDAO();

        if (selectedMunicipio != null && !municipioTxtField.getText().isEmpty()) {
            int idMunicipio = selectedMunicipio.getId();
            String nomeMunicipio = municipioTxtField.getText();

            objMunicipioDTO.setId(idMunicipio);
            objMunicipioDTO.setNomeMunicipio(nomeMunicipio);

            objMunicipioDAO.update(objMunicipioDTO);
        } else {
            Toolkit.getDefaultToolkit().beep();
            Alerts.showAlert("Error", null, "Selecione um municipio para editar ou insira um nome!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void btnDeleteAction(ActionEvent event) {
        deleteMunicipio();
        listarValoresMunicipios();
    }

    private void deleteMunicipio() {
        MunicipioDTO selectedMunicipio = tabelaMunicipio.getSelectionModel().getSelectedItem();
        MunicipioDTO objMunicipioDTO = new MunicipioDTO();
        MunicipioDAO objMunicipioDAO = new MunicipioDAO();

        if (selectedMunicipio != null) {
            int idMunicipio = selectedMunicipio.getId();
            objMunicipioDTO.setId(idMunicipio);

            objMunicipioDAO.delete(objMunicipioDTO);
        } else {
            Toolkit.getDefaultToolkit().beep();
            Alerts.showAlert("Error", null, "Selecione um municipio para deletar", Alert.AlertType.ERROR);
        }
    }

    private void listarMunicipios(List<MunicipioDTO> listaMunicipios) {
        municipioColumn.setCellValueFactory(new PropertyValueFactory<>("nomeMunicipio"));
        tabelaMunicipio.setItems(FXCollections.observableArrayList(listaMunicipios));
    }

    private void listarValoresMunicipios() {
        try {
            List<MunicipioDTO> listaMunicipio = new MunicipioDAO().listarMunicipios();
            listarMunicipios(listaMunicipio);
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            Alerts.showAlert("Error", null,"VIEW TABLE MUNICIPIO" + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
