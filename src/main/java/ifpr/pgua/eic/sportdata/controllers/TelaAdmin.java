package ifpr.pgua.eic.sportdata.controllers;

import ifpr.pgua.eic.sportdata.App;
import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;

public class TelaAdmin {

    @FXML
    private void carregaTelaGeral(){
        App.changeScreenRegion("GERAL", BorderPaneRegion.CENTER);
    }
    
    
}
