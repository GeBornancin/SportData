package ifpr.pgua.eic.sportdata.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.sportdata.App;
import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaLoginViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLogin extends BaseController implements Initializable{

    @FXML
    private TextField tfUser;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Button btLogin;

    @FXML
    private void carregaTelaAdmin(){
        App.changeScreenRegion("ADMIN", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaCadastroAluno(){
        App.changeScreenRegion("CADASTROALUNO", BorderPaneRegion.CENTER);
    } 

    @FXML
    private void carregaTelaGeral(){
        App.changeScreenRegion("GERAL", BorderPaneRegion.CENTER);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        tfUser.textProperty().bindBidirectional(viewModel.userProperty());
        tfPassword.textProperty().bindBidirectional(viewModel.passwordProperty());


    }

 
    private TelaLoginViewModel viewModel;
    
    public TelaLogin(TelaLoginViewModel viewModel){
        this.viewModel = viewModel;
    }

    @FXML
    private void entrar() {
        
        if(viewModel.entrar()){
            carregaTelaGeral();
        }

    }
    
}
