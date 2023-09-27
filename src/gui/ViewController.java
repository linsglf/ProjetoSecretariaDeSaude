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
	private Button btnPesquisar;
	@FXML
	private TableView<Medico> tabelaMedico;
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
	private ChoiceBox<String> especialidadeChoiceBox;
	@FXML
	private ChoiceBox<String> atuacaoChoiceBox;
	@FXML
	private TextField searchBar;
	@FXML
	private CheckBox checkMunicipio;
	@FXML
	private ChoiceBox<String> filtroMunicipio;
	@FXML
	private CheckBox checkCRM;
	@FXML
	private ChoiceBox<String> filtroCRM;
	@FXML
	private CheckBox checkEspecialidade;
	@FXML
	private ChoiceBox<String> filtroEspecialidade;
	@FXML
	private CheckBox checkArea;
	@FXML
	private ChoiceBox<String> filtroArea;


	@FXML
	public void initialize() {
		gerarChoiceBoxes();
		listarValores();
	}

	@FXML
	public void btnCadastrarActionPerformed(ActionEvent event) {
		cadastrarMedico();
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
		updateMedico();
		listarValores();
		limparCampos();
	}

	@FXML
	void btnDeleteAction(ActionEvent event) {
		deleteMedico();
		listarValores();
	}

	@FXML
	void btnSearchAction(ActionEvent event) {
		pesquisaCheckBox();
	}

	private void cadastrarMedico() {
		String nome, crm, municipio, statusCRM, especialidade, areaAtuacao;

		nome = txtNome.getText();
		crm = txtCRM.getText();
		municipio = municipioChoiceBox.getValue();
		statusCRM = CRMChoiceBox.getValue();
		especialidade = especialidadeChoiceBox.getValue();
		areaAtuacao = atuacaoChoiceBox.getValue();

		Medico objMedicoDTO = new MedicoDTO();
		objMedicoDTO.setNomeMedico(nome);
		objMedicoDTO.setCRM(crm);
		objMedicoDTO.setMunicipio(municipio);
		objMedicoDTO.setStatusCRM(statusCRM);
		objMedicoDTO.setEspecialidade(especialidade);
		objMedicoDTO.setAreaAtuacao(areaAtuacao);

		MedicoDAO objMedicoDAO = new MedicoDAO();
		objMedicoDAO.cadastrarMedico(objMedicoDTO);
	}

	private void listarValores() {
		try	{
			MedicoDAO objMedicoDAO = new MedicoDAO();
			List<Medico> listaMedicos = objMedicoDAO.listarMedico();

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
			especialidadeChoiceBox.setValue(selectedMedico.getEspecialidade());
			atuacaoChoiceBox.setValue(selectedMedico.getAreaAtuacao());
			txtNome.requestFocus();
		}
	}

	private void gerarChoiceBoxes() {
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


		ObservableList<String> status_crm = FXCollections.observableArrayList(
				"Ativo",
				"Inativo"
		);

		CRMChoiceBox.setItems(status_crm);
		CRMChoiceBox.setValue("Selecione a situação do CRM");



		ObservableList<String> especialidade = FXCollections.observableArrayList(
				"Clínica Geral",
				"Pediatria",
				"Ginecologia",
				"Cardiologia",
				"Dermatologia",
				"Ortopedia",
				"Oftalmologia",
				"Otorrinolaringologia",
				"Psiquiatria",
				"Neurologia"
		);

		especialidadeChoiceBox.setItems(especialidade);
		especialidadeChoiceBox.setValue("Selecione a especialidade");


		ObservableList<String> areaAtuacao = FXCollections.observableArrayList(
				"Administração em saúde",
				"Alergia e Imunologia Pediátrica",
				"Cardiologia Pediátrica",
				"Ecocardiografia",
				"Emergência Pediátrica",
				"Infectologia Hospitalar",
				"Neonatologia",
				"Nutrição Parenteral e Enteral",
				"Psicoterapia",
				"Radiologia Intervencionista"
		);

		atuacaoChoiceBox.setItems(areaAtuacao);
		atuacaoChoiceBox.setValue("Selecione a área de atuação");

		filtroMunicipio.setItems(municipio);
		filtroMunicipio.setValue("Selecione o município");

		filtroCRM.setItems(status_crm);
		filtroCRM.setValue("Selecione a situação do CRM");

		filtroEspecialidade.setItems(especialidade);
		filtroEspecialidade.setValue("Selecione a especialidade");

		filtroArea.setItems(areaAtuacao);
		filtroArea.setValue("Selecione a área de atuação");
	}

	private void limparCampos() {
		txtNome.setText("");
		txtCRM.setText("");
		txtNome.requestFocus();
		CRMChoiceBox.setValue("Selecione a situação do CRM");
		municipioChoiceBox.setValue("Selecione um município");
		especialidadeChoiceBox.setValue("Selecione a especialidade");
		atuacaoChoiceBox.setValue("Selecione a área de atuação");
		listarValores();
	}

	private void updateMedico() {
		Medico selectedMedico = tabelaMedico.getSelectionModel().getSelectedItem();
		Medico objMedicoDTO = new MedicoDTO();
		MedicoDAO objMedicoDAO = new MedicoDAO();

		if (selectedMedico != null && (!txtNome.getText().isEmpty() && !txtCRM.getText().isEmpty())) {
			int idFuncionario = selectedMedico.getIdMedico();
			String nomeFuncionario = txtNome.getText();
			String CRM = txtCRM.getText();
			String situacaoCRM = CRMChoiceBox.getValue();
			String municipio = municipioChoiceBox.getValue();
			String especialidade = especialidadeChoiceBox.getValue();
			String areaAtuacao = atuacaoChoiceBox.getValue();


			objMedicoDTO.setIdMedico(idFuncionario);
			objMedicoDTO.setNomeMedico(nomeFuncionario);
			objMedicoDTO.setCRM(CRM);
			objMedicoDTO.setStatusCRM(situacaoCRM);
			objMedicoDTO.setMunicipio(municipio);
			objMedicoDTO.setEspecialidade(especialidade);
			objMedicoDTO.setAreaAtuacao(areaAtuacao);

			objMedicoDAO.updateFuncionario(objMedicoDTO);
		}else {
			Alerts.showAlert("Informações inválidas!", null,"Preencha os campos corretamente!", Alert.AlertType.WARNING);
		}
	}

	private void deleteMedico() {
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

	public void pesquisaCheckBox() {
		String pesquisa = searchBar.getText();
		String municipio, crm, especialidade, area;

		if (checkMunicipio.isSelected()) {
			municipio = filtroMunicipio.getValue();
			System.out.println(municipio);
		} else {
			municipio = null;
			System.out.println(municipio);
		}

		if (checkCRM.isSelected()) {
			crm = filtroCRM.getValue();
			System.out.println(crm);
		} else {
			crm = null;
			System.out.println(crm);
		}

		if (checkEspecialidade.isSelected()) {
			especialidade = filtroEspecialidade.getValue();
			System.out.println(especialidade);
		} else {
			especialidade = null;
			System.out.println(especialidade);
		}

		if (checkArea.isSelected()) {
			area = filtroArea.getValue();
			System.out.println(area);
		} else {
			area = null;
			System.out.println(area);
		}

		System.out.println(pesquisa);

		try	{
			MedicoDAO objMedicoDAO = new MedicoDAO();
			List<Medico> listaMedicos = objMedicoDAO.pesquisarMedico(pesquisa, municipio, crm, especialidade, area);

			nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nomeMedico"));
			CRMColumn.setCellValueFactory(new PropertyValueFactory<>("CRM"));
			municipioColumn.setCellValueFactory(new PropertyValueFactory<>("municipio"));
			situacaoCRMColumn.setCellValueFactory(new PropertyValueFactory<>("statusCRM"));
			especialidadeColumn.setCellValueFactory(new PropertyValueFactory<>("especialidade"));
			areaColumn.setCellValueFactory(new PropertyValueFactory<>("areaAtuacao"));

			tabelaMedico.setItems(FXCollections.observableArrayList(listaMedicos));
		} catch (Exception e) {
			Alerts.showAlert("Error", null,"PESQUISA" + e.getMessage(), Alert.AlertType.ERROR);
		}
	}
}

