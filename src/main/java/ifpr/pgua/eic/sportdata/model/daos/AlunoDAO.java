package ifpr.pgua.eic.sportdata.model.daos;

import java.util.List;
import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.results.Result;

public interface AlunoDAO {

    Result create(Aluno aluno);
    Result update(int cpf, Aluno aluno);
    List<Aluno> listAll();
    Aluno getByIdAluno(int idAluno);
    Result delete(int cpf);
    
}


