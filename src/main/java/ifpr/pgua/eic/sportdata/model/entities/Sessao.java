package ifpr.pgua.eic.sportdata.model.entities;

public class Sessao{

    private static Sessao instance = null;
    private Aluno aluno;

    private Sessao(){
    }

    public void setAluno(Aluno aluno){
        this.aluno = aluno;
     }
  
     public Aluno getAluno(){
         return aluno;
     }
     public static Sessao getInstance(){
           if(instance == null){
                 instance = new Sessao();
           }
          return instance;
     }

}