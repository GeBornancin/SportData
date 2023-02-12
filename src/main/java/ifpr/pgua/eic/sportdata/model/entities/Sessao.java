package ifpr.pgua.eic.sportdata.model.entities;

public class Sessao {
    private static Sessao instance;
    private Aluno aluno;
    private boolean isAdmin;

    private Sessao() {}

    public static Sessao getInstance() {
        if (instance == null) {
            instance = new Sessao();
        }
        return instance;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    @Override
    public String toString() {
        return "Sessao [aluno=" + aluno + "]";
    }
     
}
    

