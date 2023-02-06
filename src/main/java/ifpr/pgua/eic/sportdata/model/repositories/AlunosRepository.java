package ifpr.pgua.eic.sportdata.model.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ifpr.pgua.eic.sportdata.controllers.ViewModels.AlunoRow;
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

    public Result atualizarAlunos(String cpf,String novoNomeAluno, String novaTurma, String novaSenha){


        Optional<Aluno> busca = alunos.stream().filter((alu)->alu.getCpf().equals(cpf)).findFirst();
    
        if(busca.isPresent()){
            Aluno aluno = busca.get();
            aluno.setNomeAluno(novoNomeAluno);
            aluno.setTurma(novaTurma);
            aluno.setSenha(novaSenha);

            return dao.update(aluno);
        }
        return Result.fail("Aluno não encontrado!");
    }

    public List<Aluno> listarAluno(){
        alunos = dao.listAll();
        return Collections.unmodifiableList(alunos);
    }

    public Result deleteAluno(int idAluno){

        return dao.deleteAluno(idAluno);

    }

    public Aluno loginAluno(String cpf, String senha){
        return dao.login(cpf, senha);
    }




}
