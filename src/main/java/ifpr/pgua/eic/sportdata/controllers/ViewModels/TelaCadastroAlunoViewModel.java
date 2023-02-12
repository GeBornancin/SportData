package ifpr.pgua.eic.sportdata.controllers.ViewModels;

import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.repositories.AlunosRepository;
import ifpr.pgua.eic.sportdata.model.results.Result;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;



public class TelaCadastroAlunoViewModel {
    
    
    /*
     * Aqui são definidas a propriedades que serão ligadas com os
     * textfield da tela.
     */
    private StringProperty cpfProperty = new SimpleStringProperty();  
    private StringProperty nomeAlunoProperty = new SimpleStringProperty();
    private StringProperty turmaProperty = new SimpleStringProperty();
    private StringProperty senhaProperty = new SimpleStringProperty();

    Alert alert = new Alert(Alert.AlertType.NONE);
    
    
    private AlunosRepository repository;

    public TelaCadastroAlunoViewModel(AlunosRepository repository) {

        this.repository = repository;

    }

    public StringProperty cpfProperty(){
        return cpfProperty;
    }

    public StringProperty nomeAlunoProperty(){
        return nomeAlunoProperty;
    }   

    public StringProperty turmaProperty(){
        return turmaProperty;
    }
        
    public StringProperty senhaProperty(){
        return senhaProperty;
    }



    public void cadastrar() {

        

        

        String cpf = cpfProperty.getValue();
        String nomeAluno = nomeAlunoProperty.getValue();
        String turma = turmaProperty.getValue();
        String senha = senhaProperty.getValue();

        

        repository.adicionarAluno(cpf, nomeAluno, turma, senha);


        if(cpf == null){
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Campo CPF vazio");
            alert.showAndWait();

        }else{

            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Cadastro concluido!");
            alert.showAndWait();

        }
        
        limpar();
        
       }

    public void limpar() {
        cpfProperty.setValue("");
        nomeAlunoProperty.setValue("");
        turmaProperty.setValue("");
        senhaProperty.setValue("");

        

    }


}
