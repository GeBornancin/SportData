package ifpr.pgua.eic.sportdata;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


import ifpr.pgua.eic.sportdata.model.daos.AlunoDAO;
import ifpr.pgua.eic.sportdata.model.daos.EmprestimoDAO;
import ifpr.pgua.eic.sportdata.model.daos.JDBCAlunoDAO;
import ifpr.pgua.eic.sportdata.model.daos.JDBCEmprestimoDAO;
import ifpr.pgua.eic.sportdata.model.daos.JDBCMaterialDAO;
import ifpr.pgua.eic.sportdata.model.daos.MaterialDAO;
import ifpr.pgua.eic.sportdata.model.entities.Aluno;

import ifpr.pgua.eic.sportdata.controllers.TelaAdmin;
import ifpr.pgua.eic.sportdata.controllers.TelaCadastroAluno;
import ifpr.pgua.eic.sportdata.controllers.TelaGeral;
import ifpr.pgua.eic.sportdata.controllers.TelaLogin;
import ifpr.pgua.eic.sportdata.controllers.TelaPrincipal;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaAdminViewModel;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaCadastroAlunoViewModel;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaGeralViewModel;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaLoginViewModel;
import ifpr.pgua.eic.sportdata.model.FabricaConexoes;
import ifpr.pgua.eic.sportdata.model.repositories.AlunosRepository;
import ifpr.pgua.eic.sportdata.model.repositories.EmprestimosRepository;
import ifpr.pgua.eic.sportdata.model.repositories.MateriaisRepository;
import ifpr.pgua.eic.sportdata.utils.Navigator.BaseAppNavigator;
import ifpr.pgua.eic.sportdata.utils.Navigator.ScreenRegistryFXML;

/**
 * JavaFX App
 */
public class App extends BaseAppNavigator {

    // DEFINIR A FABRICA DE CONEXÕES, DAOS e REPOSITÓRIOS
    
    private Aluno alunoKey;
    private AlunoDAO alunoDao;
    private AlunosRepository alunosRepository;

    private MaterialDAO materialDao;
    private MateriaisRepository materiaisRepository;

    private EmprestimoDAO emprestimoDao;
    private EmprestimosRepository emprestimosRepository;
    

    @Override
    public void init() throws Exception {
        // TODO Auto-generated method stub
        super.init();

        // INSTANCIAR FABRICA, DAOS E REPOSITÓRIOS

        alunoDao = new JDBCAlunoDAO(FabricaConexoes.getInstance());
        alunosRepository = new AlunosRepository(alunoDao);

        materialDao = new JDBCMaterialDAO(FabricaConexoes.getInstance());
        materiaisRepository = new MateriaisRepository(materialDao);

        emprestimoDao = new JDBCEmprestimoDAO(FabricaConexoes.getInstance());
        emprestimosRepository = new EmprestimosRepository(emprestimoDao, alunoDao, materialDao);

        
        materiaisRepository.listarMaterial();
        alunosRepository.listarAluno();
        emprestimosRepository.listarEmprestimo();
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
        registraTela("PRINCIPAL", new ScreenRegistryFXML(getClass(), "fxml/principal.fxml", (o) -> new TelaPrincipal()));
        registraTela("LOGIN", new ScreenRegistryFXML(getClass(),
         "fxml/login.fxml", (o) -> new TelaLogin(new TelaLoginViewModel(alunosRepository, alunoKey))));
        registraTela("CADASTROALUNO", new ScreenRegistryFXML(getClass(), "fxml/cadastroAluno.fxml", (o) -> new TelaCadastroAluno(new TelaCadastroAlunoViewModel(alunosRepository))));
        registraTela("GERAL", new ScreenRegistryFXML(getClass(), "fxml/geral.fxml", (o) -> new TelaGeral(new TelaGeralViewModel(alunosRepository, materiaisRepository, emprestimosRepository))));
        registraTela("ADMIN", new ScreenRegistryFXML(getClass(), "fxml/admin.fxml", (o) -> new TelaAdmin(new TelaAdminViewModel(alunosRepository, materiaisRepository))));
        // REGISTRAR AS OUTRAS TELAS
    }

}