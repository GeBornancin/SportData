package ifpr.pgua.eic.sportdata.controllers;

import java.net.URL;
import java.util.ResourceBundle;




import ifpr.pgua.eic.sportdata.App;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.AlunoRow;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaAdminViewModel;
import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
        
    
        tbcNomeAluno.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
        tbcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tbcTurma.setCellValueFactory(new PropertyValueFactory<>("turma"));
        tbcSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));

        tbAlunos.setItems(viewModel.getAlunos());

        viewModel.selecionadoProperty().bind(tbAlunos.getSelectionModel().selectedItemProperty());

        tfNomeAluno.textProperty().bindBidirectional(viewModel.nomeAlunoProperty());
        tfNomeAluno.editableProperty().bind(viewModel.podeEditarProperty());

        tfCpf.textProperty().bindBidirectional(viewModel.cpfProperty());
        tfCpf.editableProperty().bind(viewModel.podeEditarProperty());

        tfTurma.textProperty().bindBidirectional(viewModel.turmaProperty());
        tfTurma.editableProperty().bind(viewModel.podeEditarProperty());
        
        tfSenha.textProperty().bindBidirectional(viewModel.senhaProperty());
        tfSenha.editableProperty().bind(viewModel.podeEditarProperty());

        // btEditarAluno.textProperty().bind(viewModel.operacaoProperty());
    }

    @FXML
    private void atualizar(MouseEvent event){
        if(event.getClickCount() == 2)
        viewModel.atualizar();
    }

    public void limpar(){
        viewModel.limpar();
    }

}
