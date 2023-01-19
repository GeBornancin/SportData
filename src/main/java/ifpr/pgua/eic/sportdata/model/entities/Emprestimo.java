package ifpr.pgua.eic.sportdata.model.entities;

import java.time.LocalDate;

public class Emprestimo {
    
    private int id;
    private Aluno cpf;
    private Material nomeMaterial;
    private LocalDate dataEmprestimo;
    
    public Emprestimo(int id, Aluno cpf, Material nomeMaterial, LocalDate dataEmprestimo) {
        this.id = id;
        this.cpf = cpf;
        this.nomeMaterial = nomeMaterial;
        this.dataEmprestimo = dataEmprestimo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aluno getCpf() {
        return cpf;
    }

    public void setCpf(Aluno cpf) {
        this.cpf = cpf;
    }

    public Material getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(Material nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

}
