package gui.especialidade;

import DAO.EspecialidadeDAO;
import DTO.EspecialidadeDTO;
import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EspecialidadeController {

    @FXML
    private Button btnCreate;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField especialidadeTxtField;
    @FXML
    private TableView<EspecialidadeDTO> tabelaEspecialidade;
    @FXML
    private TableColumn<EspecialidadeDTO, String> especialidadeColumn;

    @FXML
    public void initialize() {
        listarValoresEspecialidades();
    }

    @FXML
    public void btnCreateAction(ActionEvent event) {
        criarEspecialidade();
        listarValoresEspecialidades();
    }

    private void criarEspecialidade() {
        String nomeEspecialidade = especialidadeTxtField.getText();

        EspecialidadeDTO objMunicipioDTO = new EspecialidadeDTO();
        objMunicipioDTO.setNomeEspecialidade(nomeEspecialidade);

        EspecialidadeDAO objMunicipioDAO = new EspecialidadeDAO();
        objMunicipioDAO.cadastrarEspecialidade(objMunicipioDTO);
    }

    @FXML
    public void btnEditAction(ActionEvent event) {
        editarCampos();
    }

    private void editarCampos() {
        EspecialidadeDTO selectedEspecialidade = tabelaEspecialidade.getSelectionModel().getSelectedItem();

        if (selectedEspecialidade != null) {
            especialidadeTxtField.setText(selectedEspecialidade.getNomeEspecialidade());
        } else {
            Alerts.showAlert("Error", null, "Selecione uma Especialidade para editar", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void btnUpdateAction(ActionEvent event) {
        updateEspecialidade();
        listarValoresEspecialidades();
    }

    private void updateEspecialidade() {
        EspecialidadeDTO selectedEspecialidade = tabelaEspecialidade.getSelectionModel().getSelectedItem();
        EspecialidadeDTO objEspecialidadeDTO = new EspecialidadeDTO();
        EspecialidadeDAO objEspecialidadeDAO = new EspecialidadeDAO();

        if (selectedEspecialidade != null && !especialidadeTxtField.getText().isEmpty()) {
            int idMunicipio = selectedEspecialidade.getIdEspecialidade();
            String nomeMunicipio = especialidadeTxtField.getText();

            objEspecialidadeDTO.setIdEspecialidade(idMunicipio);
            objEspecialidadeDTO.setNomeEspecialidade(nomeMunicipio);

            objEspecialidadeDAO.updateEspecialidade(objEspecialidadeDTO);
        } else {
            Alerts.showAlert("Error", null, "Selecione uma especialidade para editar ou insira um nome!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void btnDeleteAction(ActionEvent event) {
        deleteEspecialidade();
        listarValoresEspecialidades();
    }

    private void deleteEspecialidade() {
        EspecialidadeDTO selectedEspecialidade = tabelaEspecialidade.getSelectionModel().getSelectedItem();
        EspecialidadeDTO objEspecialidadeDTO = new EspecialidadeDTO();
        EspecialidadeDAO objEspecialidadeDAO = new EspecialidadeDAO();

        if (selectedEspecialidade != null) {
            int idEspecialidade = selectedEspecialidade.getIdEspecialidade();
            objEspecialidadeDTO.setIdEspecialidade(idEspecialidade);

            objEspecialidadeDAO.deleteEspecialidade(objEspecialidadeDTO);
        } else {
            Alerts.showAlert("Error", null, "Selecione uma especialidade para deletar", Alert.AlertType.ERROR);
        }
    }

    private void listarEspecialidades(List<EspecialidadeDTO> listaEspecialidades) {
        especialidadeColumn.setCellValueFactory(new PropertyValueFactory<>("nomeEspecialidade"));
        tabelaEspecialidade.setItems(FXCollections.observableArrayList(listaEspecialidades));
    }

    private void listarValoresEspecialidades() {
        try {
            List<EspecialidadeDTO> listaEspecialidade = new EspecialidadeDAO().listarEspecialidade();
            listarEspecialidades(listaEspecialidade);
        } catch (Exception e) {
            Alerts.showAlert("Error", null,"VIEW TABLE ESPECIALIDADE" + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
