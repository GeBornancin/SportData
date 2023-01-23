package ifpr.pgua.eic.sportdata.model.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;



import ifpr.pgua.eic.sportdata.model.daos.AlunoDAO;
import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.results.Result;

public class AlunosRepository {
    

    private List<Aluno> alunos;
    private AlunoDAO dao;


    public  AlunosRepository(AlunoDAO dao){
        this.dao = dao;
    }

   
    public Result adicionarAluno(String cpf,String nomeAluno,String turma, String senha ){

        // Optional<Aluno> busca =  alunos.stream().filter((alu)->alu.getCpf().equals(cpf)).findFirst();

        // if(busca.isPresent()){
        //     return Result.fail("Aluno já cadastrado!");
        // }

        Aluno aluno = new Aluno(cpf,nomeAluno,turma,senha);

        return dao.create(aluno);

    }


    public List<Aluno> getAlunos(){
        alunos = dao.listAll();
        return Collections.unmodifiableList(alunos);
    }
}
