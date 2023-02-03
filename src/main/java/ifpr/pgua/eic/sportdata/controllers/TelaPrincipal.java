package ifpr.pgua.eic.sportdata.controllers;

import ifpr.pgua.eic.sportdata.App;

import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class TelaPrincipal extends BaseController {
    

    @FXML
    private void carregaTelaLogin(){
        App.changeScreenRegion("LOGIN", BorderPaneRegion.CENTER);

    } 

    public void fecharApp(){
        Platform.exit();
        
    }



}
