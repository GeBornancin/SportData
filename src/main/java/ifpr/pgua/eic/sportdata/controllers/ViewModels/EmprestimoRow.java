package ifpr.pgua.eic.sportdata.controllers.ViewModels;

import java.time.format.DateTimeFormatter;

import ifpr.pgua.eic.sportdata.model.entities.Emprestimo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmprestimoRow {

    private Emprestimo emprestimo;

    public EmprestimoRow(Emprestimo emprestimo){
        this.emprestimo = emprestimo;;
    }

    public Emprestimo getEmprestimo(){
        return emprestimo;
    }

    public StringProperty idEmprestimoProperty(){
        return new SimpleStringProperty(String.valueOf(emprestimo.getIdEmprestimo()));
    }

    public StringProperty alunoProperty(){
        return new SimpleStringProperty(String.valueOf(emprestimo.getAluno()));
    }

    public StringProperty materialProperty(){

        return new SimpleStringProperty(String.valueOf(emprestimo.getMaterial()));
    }

    public StringProperty dataEmprestimoProperty(){

        return new SimpleStringProperty(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(emprestimo.getDataEmprestimo()));
    }

}