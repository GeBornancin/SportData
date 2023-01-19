package ifpr.pgua.eic.sportdata.controllers;

import ifpr.pgua.eic.sportdata.App;
import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;

public class TelaGeral{

    @FXML
    private void carregaTelaLogin(){
        App.changeScreenRegion("LOGIN", BorderPaneRegion.CENTER);
    } 
    @FXML
    private void carregaTelaAdmin(){
        App.changeScreenRegion("ADMIN", BorderPaneRegion.CENTER);
    } 
    
}