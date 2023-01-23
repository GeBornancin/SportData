package ifpr.pgua.eic.sportdata.model.entities;

public class Aluno {

    private int idAluno;
    private String cpf;
    private String nomeAluno;
    private String turma;
    private String senha;
    
    public Aluno(int idAluno, String cpf, String nomeAluno, String turma, String senha) {
        this.idAluno = idAluno;
        this.cpf = cpf;
        this.nomeAluno = nomeAluno;
        this.turma = turma;
        this.senha = senha;
    }
    

    public Aluno(String cpf, String nomeAluno, String turma, String senha) {
        this.cpf = cpf;
        this.nomeAluno = nomeAluno;
        this.turma = turma;
        this.senha = senha;
    }


    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    

}
