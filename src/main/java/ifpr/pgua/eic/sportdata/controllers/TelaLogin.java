package ifpr.pgua.eic.sportdata.controllers;

import ifpr.pgua.eic.sportdata.App;
import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;

public class TelaLogin {

    @FXML
    private void carregaTelaCadastroAluno(){
        App.changeScreenRegion("CADASTROALUNO", BorderPaneRegion.CENTER);
    } 

    @FXML
    private void carregaTelaGeral(){
        App.changeScreenRegion("GERAL", BorderPaneRegion.CENTER);
    }
    
}
