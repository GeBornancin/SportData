package ifpr.pgua.eic.sportdata.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.sportdata.App;
import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TelaGeral  extends BaseController implements Initializable{

    @FXML
    private void carregaTelaLogin(){
        App.changeScreenRegion("LOGIN", BorderPaneRegion.CENTER);
    } 
    @FXML
    private void carregaTelaAdmin(){
        App.changeScreenRegion("ADMIN", BorderPaneRegion.CENTER);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        
    } 
    

}