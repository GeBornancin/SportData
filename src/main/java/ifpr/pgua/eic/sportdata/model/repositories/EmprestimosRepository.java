package ifpr.pgua.eic.sportdata.model.repositories;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ifpr.pgua.eic.sportdata.model.daos.AlunoDAO;
import ifpr.pgua.eic.sportdata.model.daos.EmprestimoDAO;
import ifpr.pgua.eic.sportdata.model.daos.MaterialDAO;
import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.entities.Material;
import ifpr.pgua.eic.sportdata.model.entities.Emprestimo;
import ifpr.pgua.eic.sportdata.model.results.Result;



public class EmprestimosRepository {

    private List<Emprestimo> emprestimos;

    private AlunoDAO alunoDao;
    private EmprestimoDAO emprestimoDao;
    private MaterialDAO materialDao;
    

    public EmprestimosRepository(EmprestimoDAO emprestimoDao, AlunoDAO alunoDao, MaterialDAO materialDao) {
        this.emprestimoDao = emprestimoDao;
        this.alunoDao = alunoDao;
        this.materialDao = materialDao;
    }

    public Result cadastrar(LocalDateTime dataEmprestimo, Aluno aluno, Material material, int quantidadeEmprestada, LocalDateTime dataDevolucao){
        
     

        Emprestimo emprestimo = new Emprestimo(aluno, material, quantidadeEmprestada, dataEmprestimo, dataDevolucao);
        
        material.setQuantidade(material.getQuantidade() - quantidadeEmprestada);
        materialDao.update(quantidadeEmprestada ,material);
        
        return emprestimoDao.create(emprestimo);

    }

    private Aluno carregaAlunoEmprestimo(int id){
        return alunoDao.getAlunoFromEmprestimo(id);
    }

    private Material carregaMaterialEmprestimo(int id){

        return materialDao.getMaterialFromEmprestimo(id);
    }
    

    public List<Emprestimo> listarEmprestimo(){

        emprestimos = emprestimoDao.getAll();

        for(Emprestimo emprestimo:emprestimos){
            
            emprestimo.setAluno(carregaAlunoEmprestimo(emprestimo.getIdEmprestimo()));
            
            emprestimo.setMaterial(carregaMaterialEmprestimo(emprestimo.getIdEmprestimo()));
        }
        
        return Collections.unmodifiableList(emprestimos);

    }

    public Result devolver(Emprestimo emprestimo) {
        emprestimo.setDataDevolucao(LocalDateTime.now());
    
        Material material = emprestimo.getMaterial();
        int quantidadeAtual = emprestimo.getMaterial().getQuantidade();
        int quantidadeDevolvida = emprestimo.getQuantidadeEmprestada();
    
        if (quantidadeAtual + quantidadeDevolvida >= 0) {
            emprestimo.getMaterial().setQuantidade(quantidadeAtual + quantidadeDevolvida);
        } else {
            emprestimo.getMaterial().setQuantidade(0);
        }
    
        materialDao.update(material.getIdMaterial(), material);
    
        return emprestimoDao.returnEmprestimo(emprestimo);
    }

}