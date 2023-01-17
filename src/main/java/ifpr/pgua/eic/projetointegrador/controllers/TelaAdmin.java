package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;

public class TelaAdmin {

    @FXML
    private void carregaTelaGeral(){
        App.changeScreenRegion("GERAL", BorderPaneRegion.CENTER);
    }
    
    
}
