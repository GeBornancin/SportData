package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BorderPaneRegion;
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