package ifpr.pgua.eic.sportdata.controllers.ViewModels;



import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;

import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.repositories.AlunosRepository;
import ifpr.pgua.eic.sportdata.model.entities.Sessao;

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
        Sessao sessao = Sessao.getInstance();

        alunoKey = alunosRepository.loginAluno(user, password);

    
        
        if(alunoKey != null){
            alert.setAlertType(Alert.AlertType.INFORMATION);
            sessao.setAluno(alunoKey);
            System.out.println(Sessao.getInstance().getAluno());
            alert.setHeaderText("Bem-vindo "+Sessao.getInstance().getAluno() );
            
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

        } 
         else {
            
            return false;
        }
        
    }

}