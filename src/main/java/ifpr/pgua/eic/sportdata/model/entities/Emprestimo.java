package ifpr.pgua.eic.sportdata.model.entities;

import java.time.LocalDate;

public class Emprestimo {
    
    private int idEmprestimo;
    private Aluno idAluno;
    private Material idMaterial;
    private LocalDate dataEmprestimo;


    public Emprestimo(int idEmprestimo, Aluno idAluno, Material idMaterial, LocalDate dataEmprestimo) {
        this.idEmprestimo = idEmprestimo;
        this.idAluno = idAluno;
        this.idMaterial = idMaterial;
        this.dataEmprestimo = dataEmprestimo;
    }


    public int getIdEmprestimo() {
        return idEmprestimo;
    }


    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }


    public Aluno getIdAluno() {
        return idAluno;
    }


    public void setIdAluno(Aluno idAluno) {
        this.idAluno = idAluno;
    }


    public Material getIdMaterial() {
        return idMaterial;
    }


    public void setIdMaterial(Material idMaterial) {
        this.idMaterial = idMaterial;
    }


    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }


    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    

    
}
