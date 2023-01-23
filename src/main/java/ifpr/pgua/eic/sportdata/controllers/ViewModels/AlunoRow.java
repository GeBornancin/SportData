package ifpr.pgua.eic.sportdata.controllers.ViewModels;

import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class AlunoRow {

    private Aluno aluno;

    public AlunoRow(Aluno aluno){
        this.aluno = aluno;
    }

    public Aluno getAluno(){
        return aluno;
    }

    public StringProperty idAlunoProperty(){
        return new SimpleStringProperty(String.valueOf(aluno.getIdAluno()));
    }

    public StringProperty cpfProperty(){
        return new SimpleStringProperty(String.valueOf(aluno.getCpf()));
    }

    public StringProperty nomeAlunoProperty(){
        return new SimpleStringProperty(String.valueOf(aluno.getNomeAluno()));
    }

    public StringProperty turmaProperty(){
        return new SimpleStringProperty(String.valueOf(aluno.getTurma()));
    }

    public StringProperty senhaProperty(){
        return new SimpleStringProperty(String.valueOf(aluno.getSenha()));
    }








}