package ifpr.pgua.eic.projetointegrador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


import ifpr.pgua.eic.projetointegrador.controllers.TelaCadastroAluno;
import ifpr.pgua.eic.projetointegrador.controllers.TelaGeral;
import ifpr.pgua.eic.projetointegrador.controllers.TelaLogin;
import ifpr.pgua.eic.projetointegrador.controllers.TelaPrincipal;
import ifpr.pgua.eic.projetointegrador.model.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.BaseAppNavigator;
import ifpr.pgua.eic.projetointegrador.utils.Navigator.ScreenRegistryFXML;

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

    // DEFINIR A FABRICA DE CONEXÕES, DAOS e REPOSITÓRIOS

    @Override
    public void init() throws Exception {
        // TODO Auto-generated method stub
        super.init();

        // INSTANCIAR FABRICA, DAOS E REPOSITÓRIOS

    }

    @Override
    public void stop() throws Exception {
        super.stop();

    }

    @Override
    public String getHome() {
        // TODO Auto-generated method stub
        return "PRINCIPAL";
    }

    @Override
    public String getAppTitle() {
        // TODO Auto-generated method stub
        return "Projeto Integrador";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL",new ScreenRegistryFXML(getClass(), "fxml/principal.fxml", (o) -> new TelaPrincipal()));
        registraTela("LOGIN", new ScreenRegistryFXML(getClass(), "fxml/login.fxml", (o) -> new TelaLogin()));
        registraTela("CADASTROALUNO", new ScreenRegistryFXML(getClass(), "fxml/cadastroAluno.fxml", (o) -> new TelaCadastroAluno()));
        registraTela("GERAL", new ScreenRegistryFXML(getClass(), "fxml/geral.fxml", (o) -> new TelaGeral()));
        // REGISTRAR AS OUTRAS TELAS
    }

}