package ifpr.pgua.eic.sportdata.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.sportdata.App;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaCadastroAlunoViewModel;
import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TelaCadastroAluno  extends BaseController implements Initializable{

    @FXML
    private void carregaTelaLogin(){
        App.changeScreenRegion("LOGIN", BorderPaneRegion.CENTER);
    } 
    
    @FXML
    private TextField tfCpf;

    @FXML
    private TextField tfNomeAluno;

    @FXML
    private TextField tfTurma;

    @FXML
    private TextField tfSenha;

    @FXML
    private Button btCadastrar;

    private TelaCadastroAlunoViewModel viewModel;

    public TelaCadastroAluno(TelaCadastroAlunoViewModel viewModel){
        this.viewModel = viewModel;
    }
    
    @Override
        public void initialize(URL arg0, ResourceBundle arg1) {
            // TODO Auto-generated method stub
            
            tfCpf.textProperty().bindBidirectional(viewModel.cpfProperty());
            tfNomeAluno.textProperty().bindBidirectional(viewModel.nomeAlunoProperty());
            tfTurma.textProperty().bindBidirectional(viewModel.turmaProperty());
            tfSenha.textProperty().bindBidirectional(viewModel.senhaProperty());
        }

    @FXML
    private void cadastrar(){
        viewModel.cadastrar();
    }
    
    @FXML
    private void limpar(){
        viewModel.limpar();
    }
   
}