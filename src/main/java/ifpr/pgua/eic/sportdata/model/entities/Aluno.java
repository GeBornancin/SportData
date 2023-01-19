package ifpr.pgua.eic.sportdata.model.entities;

public class Aluno {

    private int idAluno;
    private int cpf;
    private String nomeAluno;
    private String turma;
    private Integer senha;
    
    public Aluno(int idAluno,int cpf, String nomeAluno, String turma, int senha) {
        this.idAluno = idAluno;
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

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
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

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    
    

}
