package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;

public class TelaLogin {

    @FXML
    private void carregaTelaCadastro(){
        App.changeScreenRegion("CADASTRO", BorderPaneRegion.CENTER);
    } 
    
}
