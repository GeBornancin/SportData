package ifpr.pgua.eic.sportdata.controllers.ViewModels;

import java.time.format.DateTimeFormatter;

import ifpr.pgua.eic.sportdata.model.entities.Emprestimo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EmprestimoRow {

    private Emprestimo emprestimo;

    public EmprestimoRow(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
       System.out.println(emprestimo.toString()); 
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public StringProperty idEmprestimoProperty() {
        return new SimpleStringProperty(String.valueOf(emprestimo.getIdEmprestimo()));
    }

    public StringProperty alunoProperty() {

        return new SimpleStringProperty(emprestimo.getAluno().getNomeAluno());
    }

    public StringProperty materialProperty() {

        return new SimpleStringProperty(emprestimo.getMaterial().getNomeMaterial());
    }

    public StringProperty quantidadeEmprestadaProperty() {

        return new SimpleStringProperty(String.valueOf(emprestimo.getQuantidadeEmprestada()));
    }

    public StringProperty dataEmprestimoProperty() {

        return new SimpleStringProperty(
                DateTimeFormatter.ofPattern("dd/MM/yyyy").format(emprestimo.getDataEmprestimo()));
    }

    public StringProperty dataDevolucaoProperty() {

        if (emprestimo.getDataDevolucao() == null) {
            return new SimpleStringProperty("");
        }
        return new SimpleStringProperty(
                DateTimeFormatter.ofPattern("dd/MM/yyyy").format(emprestimo.getDataDevolucao()));
    }

}