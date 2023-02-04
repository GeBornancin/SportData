package ifpr.pgua.eic.sportdata.controllers;

import ifpr.pgua.eic.sportdata.App;

import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaPrincipal extends BaseController {
    

    @FXML
    private void carregaTelaLogin(){
        App.changeScreenRegion("LOGIN", BorderPaneRegion.CENTER);

    } 

    @FXML
    private Button iconid;

    public void fecharApp(){
        Platform.exit();
        
    }

    public void minimizarApp(){

        Stage obj = (Stage) iconid.getScene().getWindow();
        obj.setIconified(true);
        
        
    }



}
