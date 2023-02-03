package ifpr.pgua.eic.sportdata.controllers.ViewModels;



import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.repositories.AlunosRepository;

public class TelaLoginViewModel {

    private AlunosRepository alunosRepository;
    private Aluno alunoKey;

    public TelaLoginViewModel(AlunosRepository alunosRepository, Aluno alunoKey) {
    
        this.alunosRepository = alunosRepository;
        this.alunoKey = alunoKey;
    }

    private StringProperty spUser = new SimpleStringProperty();
    private StringProperty spPassword = new SimpleStringProperty();

    public StringProperty userProperty() {
        return spUser;
    }

    public StringProperty passwordProperty() {
        return spPassword;
    }

    public boolean entrar(){

        Alert alert = new Alert(Alert.AlertType.NONE);

        String user = spUser.getValue();
        String password = spPassword.getValue();
        

        alunoKey = alunosRepository.loginAluno(user, password);

        try {
            
            
        } catch (Exception e) {
            // TODO: handle exception

        }
        if(alunoKey != null){
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Login efetuado!");
            alert.showAndWait();
            
            return true;
        }else if(user == null){
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("Campo Usuario Vazio");
            alert.showAndWait();
            return false;
    
        }else if(password == null){

            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setHeaderText("Campo Senha Vazio");
            alert.showAndWait();
            return false;

        } else {
            
            return false;
        }
        
    }

}