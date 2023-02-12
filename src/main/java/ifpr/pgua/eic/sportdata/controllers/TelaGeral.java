package ifpr.pgua.eic.sportdata.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



import ifpr.pgua.eic.sportdata.App;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.EmprestimoRow;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.MaterialRow;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaGeralViewModel;
import ifpr.pgua.eic.sportdata.model.entities.Emprestimo;
import ifpr.pgua.eic.sportdata.model.entities.Material;
import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.beans.property.SimpleStringProperty;


public class TelaGeral extends BaseController implements Initializable {

    /*Tabela Materiais disponiveis */
    @FXML
    private TableView<MaterialRow> tbItensDisponiveis;

    @FXML
    private TableColumn<MaterialRow, String> tbcNomeMaterial;

    @FXML
    private TableColumn<MaterialRow, String> tbcQuantidade;

    @FXML
    private TextField tfMaterial;

    @FXML
    private TextField tfQuantidade;

    @FXML
    private TextField tfAluno;

    @FXML
    private TableView<EmprestimoRow> tbEmprestimos;

    @FXML
    private TableColumn<EmprestimoRow, String> tbcAluno;

    @FXML
    private TableColumn<EmprestimoRow, String> tbcMaterial;

    @FXML
    private TableColumn<EmprestimoRow, String> tbcQuantidadeEmprestada;

    @FXML
    private TableColumn<EmprestimoRow, String> tbcDataEmprestimo;

    @FXML
    private TableColumn<EmprestimoRow, String> tbcDataDevolucao;

    @FXML 
    private Button btDevolver;

    private TelaGeralViewModel viewModel;

    public TelaGeral(TelaGeralViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void carregaTelaLogin() {
        App.changeScreenRegion("LOGIN", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaAdmin() {
        App.changeScreenRegion("ADMIN", BorderPaneRegion.CENTER);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        
        /*Tabela Materiais disponiveis */
        tbcNomeMaterial.setCellValueFactory(new PropertyValueFactory<>("nomeMaterial"));
        tbcQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        tbItensDisponiveis.setItems(viewModel.getMateriais());

        viewModel.getMaterialSelecionadoProperty().bind(tbItensDisponiveis.getSelectionModel().selectedItemProperty());

        /*TextField's */
        this.tfQuantidade.textProperty().bindBidirectional(viewModel.getQuantidadeProperty());
        this.tfMaterial.textProperty().bindBidirectional(viewModel.getMaterial());
        tfMaterial.editableProperty().bind(viewModel.podeEditarProperty());
        this.tfAluno.textProperty().bindBidirectional(viewModel.getAlunoStringProperty());

        /*Tabela Emprestimos */
        tbcAluno.setCellValueFactory(new PropertyValueFactory<>("aluno"));
        tbcMaterial.setCellValueFactory(new PropertyValueFactory<>("material"));
        tbcQuantidadeEmprestada.setCellValueFactory(new PropertyValueFactory<>("quantidadeEmprestada"));
        tbcDataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("dataEmprestimo"));
        tbcDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));

        viewModel.getEmprestimoSelecionadoProperty().bind(tbEmprestimos.getSelectionModel().selectedItemProperty());

        btDevolver.disableProperty().bind(viewModel.desativadoProperty());

        

        tbEmprestimos.setItems(viewModel.getEmprestimos());

    }

    @FXML
    private void emprestarItem(){
        viewModel.emprestarItem();
    }

    @FXML
    public void devolver(){
        viewModel.devolver();
    }

    @FXML
    public void atualizarEmprestimo(MouseEvent evt){
        if(evt.getClickCount()== 2) {
            viewModel.atualizarEmprestimo();
        }

    }

    @FXML
    public void encerrarSessao(){
        viewModel.encerrarSessao();
        carregaTelaLogin();
    }

    @FXML 
    private void atualizar(MouseEvent evt){
        if(evt.getClickCount() == 2) {
            viewModel.preencheTextFieldsParaAtualizar();

       }
    }

    
    

}