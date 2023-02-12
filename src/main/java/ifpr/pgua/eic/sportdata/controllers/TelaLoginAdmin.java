package ifpr.pgua.eic.sportdata.controllers;

import java.net.URL;
import java.security.Principal;
import java.util.ResourceBundle;

import ifpr.pgua.eic.sportdata.App;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaLoginAdminViewModel;
import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaLoginAdmin extends BaseController implements Initializable {

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
   private void carregaTelaPrincipal(){
        App.changeScreenRegion("PRINCIPAL", BorderPaneRegion.CENTER);
   }
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        tfUser.textProperty().bindBidirectional(viewModel.userProperty());
        tfPassword.textProperty().bindBidirectional(viewModel.passwordProperty());


    }

 
    private TelaLoginAdminViewModel viewModel;
    
    
    public TelaLoginAdmin(TelaLoginAdminViewModel viewModel){
        this.viewModel = viewModel;
    }

    @FXML
    private void entrar() {
        
        if(viewModel.entrarComoAdministrador()){
            carregaTelaAdmin();
        }

    }
}
