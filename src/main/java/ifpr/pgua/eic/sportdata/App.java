package ifpr.pgua.eic.sportdata;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import ifpr.pgua.eic.sportdata.model.daos.AlunoDAO;
import ifpr.pgua.eic.sportdata.model.daos.JDBCAlunoDAO;
import ifpr.pgua.eic.sportdata.controllers.TelaAdmin;
import ifpr.pgua.eic.sportdata.controllers.TelaCadastroAluno;
import ifpr.pgua.eic.sportdata.controllers.TelaGeral;
import ifpr.pgua.eic.sportdata.controllers.TelaLogin;
import ifpr.pgua.eic.sportdata.controllers.TelaPrincipal;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaCadastroAlunoViewModel;
import ifpr.pgua.eic.sportdata.model.FabricaConexoes;
import ifpr.pgua.eic.sportdata.model.repositories.AlunosRepository;
import ifpr.pgua.eic.sportdata.utils.Navigator.BaseAppNavigator;
import ifpr.pgua.eic.sportdata.utils.Navigator.ScreenRegistryFXML;

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

    // DEFINIR A FABRICA DE CONEXÕES, DAOS e REPOSITÓRIOS

    private AlunoDAO alunoDao;
    private AlunosRepository alunosRepository;

    @Override
    public void init() throws Exception {
        // TODO Auto-generated method stub
        super.init();

        // INSTANCIAR FABRICA, DAOS E REPOSITÓRIOS

        alunoDao = new JDBCAlunoDAO(FabricaConexoes.getInstance());
        alunosRepository = new AlunosRepository(alunoDao);

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
                new ScreenRegistryFXML(getClass(), "fxml/cadastroAluno.fxml", (o) -> new TelaCadastroAluno(new TelaCadastroAlunoViewModel(alunosRepository))));
        registraTela("GERAL", new ScreenRegistryFXML(getClass(), "fxml/geral.fxml", (o) -> new TelaGeral()));
        registraTela("ADMIN", new ScreenRegistryFXML(getClass(), "fxml/admin.fxml", (o) -> new TelaAdmin()));
        // REGISTRAR AS OUTRAS TELAS
    }

}