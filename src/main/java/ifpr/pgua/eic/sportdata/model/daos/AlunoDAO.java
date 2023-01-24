package ifpr.pgua.eic.sportdata.model.daos;

import java.util.List;
import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.results.Result;

public interface AlunoDAO {

    Result create(Aluno aluno);
    Result update(String cpf, Aluno aluno);
    List<Aluno> listAll();
    Aluno getByIdAluno(int idAluno);
    Aluno getAlunoFromEmprestimo(int idEmprestimo);
    Result deleteAluno(int idAluno);
    
}


