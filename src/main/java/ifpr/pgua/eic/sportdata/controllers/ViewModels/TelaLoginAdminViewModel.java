package ifpr.pgua.eic.sportdata.controllers.ViewModels;

import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.entities.Sessao;
import ifpr.pgua.eic.sportdata.model.repositories.AlunosRepository;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

public class TelaLoginAdminViewModel {
    
    private AlunosRepository alunosRepository;

    public TelaLoginAdminViewModel(AlunosRepository alunosRepository){

        this.alunosRepository = alunosRepository;

    }


    private StringProperty spUser = new SimpleStringProperty();
    private StringProperty spPassword = new SimpleStringProperty();
    Alert alert = new Alert(Alert.AlertType.NONE);

    public StringProperty userProperty() {
        return spUser;
    }

    public StringProperty passwordProperty() {
        return spPassword;
    }

    public boolean entrarComoAdministrador() {
        String user = spUser.getValue();
        String password = spPassword.getValue();
        Sessao sessao = Sessao.getInstance();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (user == null || user.trim().isEmpty()) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("Campo Usuario Vazio");
            alert.showAndWait();
            return false;

        } else if (password == null || password.trim().isEmpty()) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("Campo Senha Vazio");
            alert.showAndWait();
            return false;
        } else if (user.equals("admin") && password.equals("admin")) {
            alert.setHeaderText("Bem-vindo, administrador");
            sessao.setAluno(null);
            sessao.setAdmin(true);
            alert.showAndWait();

            return true;
        } else if (!user.equals("admin")) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("Usuario Incorreto");
            alert.showAndWait();
            return false;
        } else {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("Senha Incorreta");
            alert.showAndWait();
            return false;
        }
    }
}
