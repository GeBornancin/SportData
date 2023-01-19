package ifpr.pgua.eic.sportdata;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import ifpr.pgua.eic.sportdata.controllers.TelaAdmin;
import ifpr.pgua.eic.sportdata.controllers.TelaCadastroAluno;
import ifpr.pgua.eic.sportdata.controllers.TelaGeral;
import ifpr.pgua.eic.sportdata.controllers.TelaLogin;
import ifpr.pgua.eic.sportdata.controllers.TelaPrincipal;
import ifpr.pgua.eic.sportdata.model.FabricaConexoes;
import ifpr.pgua.eic.sportdata.utils.Navigator.BaseAppNavigator;
import ifpr.pgua.eic.sportdata.utils.Navigator.ScreenRegistryFXML;

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
        return "SportData";
    }

    @Override
    public void registrarTelas() {
        registraTela("PRINCIPAL",
                new ScreenRegistryFXML(getClass(), "fxml/principal.fxml", (o) -> new TelaPrincipal()));
        registraTela("LOGIN", new ScreenRegistryFXML(getClass(), "fxml/login.fxml", (o) -> new TelaLogin()));
        registraTela("CADASTROALUNO",
                new ScreenRegistryFXML(getClass(), "fxml/cadastroAluno.fxml", (o) -> new TelaCadastroAluno()));
        registraTela("GERAL", new ScreenRegistryFXML(getClass(), "fxml/geral.fxml", (o) -> new TelaGeral()));
        registraTela("ADMIN", new ScreenRegistryFXML(getClass(), "fxml/admin.fxml", (o) -> new TelaAdmin()));
        // REGISTRAR AS OUTRAS TELAS
    }

}