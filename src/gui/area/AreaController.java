package gui.area;

import DAO.AreaDAO;
import DTO.AreaDTO;
import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.util.List;

public class AreaController {

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField areaTxtField;
    @FXML
    private TableView<AreaDTO> tabelaArea;
    @FXML
    private TableColumn<AreaDTO, String> areaColumn;

    @FXML
    public void initialize() {
        listarValoresAreas();
    }

    @FXML
    public void btnCreateAction(ActionEvent event) {
        criarArea();
        listarValoresAreas();
    }

    private void criarArea() {
        String nomeArea = areaTxtField.getText();

        AreaDTO objAreaDTO = new AreaDTO();
        objAreaDTO.setNomeArea(nomeArea);

        AreaDAO objAreaDAO = new AreaDAO();
        objAreaDAO.insert(objAreaDTO);
    }

    @FXML
    public void btnEditAction(ActionEvent event) {
        editarCampos();
    }

    public void editarCampos() {
        AreaDTO selectedArea = tabelaArea.getSelectionModel().getSelectedItem();

        if (selectedArea != null) {
            areaTxtField.setText(selectedArea.getNomeArea());
        } else {
            Toolkit.getDefaultToolkit().beep();
            Alerts.showAlert("Error", null, "Selecione uma Area para editar", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void btnUpdateAction(ActionEvent event) {
        updateArea();
        listarValoresAreas();
    }

    private void updateArea() {
        AreaDTO selectedArea = tabelaArea.getSelectionModel().getSelectedItem();
        AreaDTO objAreaDTO = new AreaDTO();
        AreaDAO objAreaDAO = new AreaDAO();

        if (selectedArea != null && !areaTxtField.getText().isEmpty()) {
            int idArea = selectedArea.getId();
            String nomeArea = areaTxtField.getText();

            objAreaDTO.setId(idArea);
            objAreaDTO.setNomeArea(nomeArea);

            objAreaDAO.update(objAreaDTO);
        } else {
            Toolkit.getDefaultToolkit().beep();
            Alerts.showAlert("Error", null, "Selecione uma Area para editar", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void btnDeleteAction(ActionEvent event) {
        deleteArea();
        listarValoresAreas();
    }

    private void deleteArea() {
        AreaDTO selectedArea = tabelaArea.getSelectionModel().getSelectedItem();
        AreaDTO objAreaDTO = new AreaDTO();
        AreaDAO objAreaDAO = new AreaDAO();

        if (selectedArea != null) {
            int idArea = selectedArea.getId();
            objAreaDTO.setId(idArea);

            objAreaDAO.delete(objAreaDTO);
        } else {
            Toolkit.getDefaultToolkit().beep();
            Alerts.showAlert("Error", null, "Selecione uma Area para deletar", Alert.AlertType.ERROR);
        }
    }

    private void listarAreas(List<AreaDTO> listaAreas) {
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("nomeArea"));
        tabelaArea.setItems(FXCollections.observableArrayList(listaAreas));
    }

    private void listarValoresAreas() {
        try {
            List<AreaDTO> listaAreas = new AreaDAO().listarAreas();
            listarAreas(listaAreas);
        } catch (Exception e) {
            Toolkit.getDefaultToolkit().beep();
            Alerts.showAlert("Error", null, "VIEW TABLE AREA" + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
