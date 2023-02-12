package ifpr.pgua.eic.sportdata.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.sportdata.App;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.AlunoRow;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.MaterialRow;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaAdminViewModel;
import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class TelaAdmin extends BaseController implements Initializable {

    @FXML
    private void carregaTelaGeral(){
        App.changeScreenRegion("GERAL", BorderPaneRegion.CENTER);
    }
    @FXML
    private void carregaTelaLoginAdmin(){
        App.changeScreenRegion("LOGINADMIN", BorderPaneRegion.CENTER);
    }

    

// MATERIAL

    @FXML
    private TableView<MaterialRow> tbMateriais;
    
    @FXML
    private TableColumn<MaterialRow, String> tbcNomeMaterial;

    @FXML
    private TableColumn<MaterialRow, Integer> tbcQuantidade;

    @FXML 
    private ComboBox cbMaterial;

    @FXML
    private TextField tfNomeMaterial;

    @FXML
    private TextField tfQuantidade;

    @FXML
    private Button btCadastrarMaterial;

    @FXML
    private Button btEditarMaterial;

    @FXML
    private Button btExcluirMaterial;

// ALUNO

    @FXML 
    private TableView<AlunoRow> tbAlunos;

    @FXML
    private TableColumn<AlunoRow, String> tbcNomeAluno;

    @FXML
    private TableColumn<AlunoRow, String> tbcCpf;

    @FXML
    private TableColumn<AlunoRow, String> tbcTurma;
    
    @FXML
    private TableColumn<AlunoRow, String> tbcSenha;

    @FXML
    private TextField tfNomeAluno;

    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfTurma;

    @FXML 
    private TextField tfSenha;
    
    @FXML 
    private Button btEditarAluno;

    @FXML
    private Button btExcluirAluno;
    
    @FXML 
    private TelaAdminViewModel viewModel;

    public TelaAdmin(TelaAdminViewModel viewModel){
        this.viewModel = viewModel;
    }


    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    
        //////MATERIAL

        tbcNomeMaterial.setCellValueFactory(new PropertyValueFactory<>("nomeMaterial"));
        tbcQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        viewModel.materialSelecionadoProperty().bind(tbMateriais.getSelectionModel().selectedItemProperty());
       
        tbMateriais.setItems(viewModel.getMateriais());

        tfNomeMaterial.textProperty().bindBidirectional(viewModel.nomeMaterialProperty());
        tfNomeMaterial.editableProperty().bind(viewModel.podeEditarProperty());
        tfQuantidade.textProperty().bindBidirectional(viewModel.quantidadeProperty());
        

        // btEditarMaterial.textProperty().bind(viewModel.operacaoProperty());
        /////ALUNO

        tbcNomeAluno.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
        tbcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tbcTurma.setCellValueFactory(new PropertyValueFactory<>("turma"));
        tbcSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));

        tbAlunos.setItems(viewModel.getAlunos());

        viewModel.selecionadoProperty().bind(tbAlunos.getSelectionModel().selectedItemProperty());

        tfNomeAluno.textProperty().bindBidirectional(viewModel.nomeAlunoProperty());
       
        tfCpf.textProperty().bindBidirectional(viewModel.cpfProperty());
        tfCpf.editableProperty().bind(viewModel.podeEditarProperty());

        tfTurma.textProperty().bindBidirectional(viewModel.turmaProperty());
        
        tfSenha.textProperty().bindBidirectional(viewModel.senhaProperty());
        
        // btEditarAluno.textProperty().bind(viewModel.operacaoProperty());
    }

    @FXML
    private void atualizarAluno(MouseEvent event){
        if(event.getClickCount() == 2)
        viewModel.atualizarAluno();
    }

    @FXML
    private  void editarAluno(){
        viewModel.editarAluno();
    }

    @FXML
    private void excluirAluno(){
        viewModel.excluirAluno();
    }


    public void limparAluno(){
        viewModel.limparAluno();
    }

// ----------------------------------------------------------------------------------------------------------

    @FXML
    public void cadastrarMaterial(){
        viewModel.cadastrarMaterial();
    }

    @FXML
    private void atualizarMaterial(MouseEvent event){
        if(event.getClickCount() == 2)
        viewModel.atualizarMaterial();
    }

    @FXML
    private void editarMaterial(){
        viewModel.editarMaterial();
    }

    @FXML
    private void excluirMaterial(){
        viewModel.excluirMaterial();
    }
    
    public void limparMaterial(){
        viewModel.limparMaterial();
    }

    @FXML
    public void encerrarSessao(){
        viewModel.encerrarSessaoAdministrador();
        carregaTelaLoginAdmin();
    }

}
