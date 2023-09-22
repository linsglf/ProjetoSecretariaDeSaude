package gui;

import DTO.Medico;
import gui.util.Alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import DAO.MedicoDAO;
import DTO.MedicoDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ViewController {
	@FXML
	private TextField txtNome;
	@FXML
	private TextField txtCRM;
	@FXML
	private Button btnInsert;
	@FXML
	private Button btnClear;
	@FXML
	private Button btncCarregarCampos;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnDelete;
	@FXML
	private TableView<Medico> tabelaMedico;
	@FXML
	private TableColumn<Medico, Integer> idColumn;
	@FXML
	private TableColumn<Medico, String> nomeColumn;
	@FXML
	private TableColumn<Medico, String> CRMColumn;
	@FXML
	private TableColumn<Medico, String> municipioColumn;
	@FXML
	private TableColumn<Medico, String> situacaoCRMColumn;
	@FXML
	private TableColumn<Medico, String> especialidadeColumn;
	@FXML
	private TableColumn<Medico, String> areaColumn;
	@FXML
	private ChoiceBox<String> CRMChoiceBox;
	@FXML
	private ChoiceBox<String> municipioChoiceBox;




	@FXML
	public void initialize() {
		gerarChoiceBoxCRM();
		gerarChoiceBoxMunicipio();
		listarValores();
	}

	// Event Listener on Button[#btnCadastrar].onAction
	@FXML
	public void btnCadastrarActionPerformed(ActionEvent event) {
		cadastrarFuncionario();
		listarValores();
	}

	@FXML
	void btnClearActionPerformed(ActionEvent event) {
		limparCampos();
	}

	@FXML
	public void btnCarregarCampos(ActionEvent event) {
		carregarCampos();
	}
	@FXML
	public void btnUpdateTableValue(ActionEvent event) {
		updateFuncionario();
		listarValores();
		limparCampos();
	}

	@FXML
	void btnDeleteAction(ActionEvent event) {
		deleteFuncionario();
		listarValores();
	}

	private void cadastrarFuncionario() {
		String nome, crm, municipio, statusCRM, especialidade, areaAtuacao;

		nome = txtNome.getText();
		crm = txtCRM.getText();
		municipio = municipioChoiceBox.getValue();
		statusCRM = CRMChoiceBox.getValue();
		//especialidade = cargoChoiceBox.getValue();
		//areaAtuacao = cargoChoiceBox.getValue();

		Medico objMedicoDTO = new MedicoDTO();
		objMedicoDTO.setNomeMedico(nome);
		objMedicoDTO.setCRM(crm);
		objMedicoDTO.setMunicipio(municipio);
		objMedicoDTO.setStatusCRM(statusCRM);
		objMedicoDTO.setEspecialidade("Especialidade Aleatória");
		objMedicoDTO.setAreaAtuacao("Área de Atuação Aleatória");

		MedicoDAO objMedicoDAO = new MedicoDAO();
		objMedicoDAO.cadastrarMedico(objMedicoDTO);
	}

	private void listarValores() {
		try	{
			MedicoDAO objMedicoDAO = new MedicoDAO();
			List<Medico> listaMedicos = objMedicoDAO.listarMedico();

			idColumn.setCellValueFactory(new PropertyValueFactory<>("idMedico"));
			nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nomeMedico"));
			CRMColumn.setCellValueFactory(new PropertyValueFactory<>("CRM"));
			municipioColumn.setCellValueFactory(new PropertyValueFactory<>("municipio"));
			situacaoCRMColumn.setCellValueFactory(new PropertyValueFactory<>("statusCRM"));
			especialidadeColumn.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
			areaColumn.setCellValueFactory(new PropertyValueFactory<>("areaAtuacao"));

			tabelaMedico.setItems(FXCollections.observableArrayList(listaMedicos));
		} catch (Exception e) {
			Alerts.showAlert("Error", null,"VIEW TABLE" + e.getMessage(), Alert.AlertType.ERROR);
		}
	}

	private void carregarCampos() {
		Medico selectedMedico = tabelaMedico.getSelectionModel().getSelectedItem();

		if (selectedMedico != null) {
			txtNome.setText(selectedMedico.getNomeMedico());
			txtCRM.setText(selectedMedico.getCRM());
			CRMChoiceBox.setValue(selectedMedico.getStatusCRM());
			municipioChoiceBox.setValue(selectedMedico.getMunicipio());
			txtNome.requestFocus();
		}
	}

	private void gerarChoiceBoxCRM() {
		ObservableList<String> status_crm = FXCollections.observableArrayList(
				"Ativo",
				"Inativo"
		);

		CRMChoiceBox.setItems(status_crm);
		CRMChoiceBox.setValue("Selecione a situação do CRM");
	}

	private void gerarChoiceBoxMunicipio() {
		ObservableList<String> municipio = FXCollections.observableArrayList(
				"Recife (capital do estado)",
				"Jaboatão dos Guararapes",
				"Olinda",
				"Caruaru",
				"Floresta",
				"Petrolina",
				"Paulista",
				"Cabo de Santo Agostinho",
				"Camaragibe",
				"Garanhuns",
				"Vitória de Santo Antão"
		);

		municipioChoiceBox.setItems(municipio);
		municipioChoiceBox.setValue("Selecione o município");
	}

	private void limparCampos() {
		txtNome.setText("");
		txtCRM.setText("");
		txtNome.requestFocus();
		CRMChoiceBox.setValue("Selecione a situação do CRM");
		municipioChoiceBox.setValue("Selecione um município");
	}

	private void updateFuncionario() {
		Medico selectedMedico = tabelaMedico.getSelectionModel().getSelectedItem();
		Medico objMedicoDTO = new MedicoDTO();
		MedicoDAO objMedicoDAO = new MedicoDAO();

		if (selectedMedico != null && (!txtNome.getText().isEmpty() && !txtCRM.getText().isEmpty())) {
			int idFuncionario = selectedMedico.getIdMedico();
			String nomeFuncionario = txtNome.getText();
			String CRM = txtCRM.getText();
			String situacaoCRM = CRMChoiceBox.getValue();
			String municipio = municipioChoiceBox.getValue();


			objMedicoDTO.setIdMedico(idFuncionario);
			objMedicoDTO.setNomeMedico(nomeFuncionario);
			objMedicoDTO.setCRM(CRM);
			objMedicoDTO.setStatusCRM(situacaoCRM);
			objMedicoDTO.setMunicipio(municipio);
			//objMedicoDTO.setEspecialidade("Especialidade Aleatória");
			//objMedicoDTO.setAreaAtuacao("Área de Atuação Aleatória");

			objMedicoDAO.updateFuncionario(objMedicoDTO);
		}else {
			Alerts.showAlert("Informações inválidas!", null,"Preencha os campos corretamente!", Alert.AlertType.WARNING);
		}
	}

	private void deleteFuncionario() {
		Medico selectedMedico = tabelaMedico.getSelectionModel().getSelectedItem();
		Medico objMedicoDTO = new MedicoDTO();
		MedicoDAO objMedicoDAO = new MedicoDAO();

		if (selectedMedico != null) {
			int idFuncionario = selectedMedico.getIdMedico();
			objMedicoDTO.setIdMedico(idFuncionario);

			objMedicoDAO.deletFuncionario(objMedicoDTO);
		}else {
			Alerts.showAlert("Delete ERROR", null,"Unable to delete!", Alert.AlertType.ERROR);
		}
	}
}

