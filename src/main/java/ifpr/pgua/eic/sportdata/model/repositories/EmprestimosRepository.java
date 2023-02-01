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
import ifpr.pgua.eic.sportdata.model.entities.ItemEmprestimo;
import ifpr.pgua.eic.sportdata.model.results.Result;
import ifpr.pgua.eic.sportdata.model.results.SuccessResult;

public class EmprestimosRepository {

    public AlunoDAO alunoDao;
    public EmprestimoDAO emprestimoDao;
    public MaterialDAO materialDao;

    public EmprestimosRepository(EmprestimoDAO dao, AlunoDAO alunoDao, MaterialDAO materialDao) {
        this.emprestimoDao = dao;
        this.alunoDao = alunoDao;
        this.materialDao = materialDao;
    }

    public Result cadastrar(LocalDateTime dataHora, Aluno aluno, List<ItemEmprestimo> itens){
        
        if(aluno == null) {
            return Result.fail("Aluno invalido");
        }

        if(dataHora.isAfter(LocalDateTime.now())) {
            return Result.fail("Data e hora invalida");
        }

        if(itens.size() == 0){
            return Result.fail("Nenhum item selecionado");
        }

        for(ItemEmprestimo item:itens){
            if(item.getQuantidade() > item.getMaterial().getQuantidade()){
                return Result.fail("Não há materiais o suficiente");
            }
        }

        Emprestimo emprestimo = new Emprestimo(aluno, itens, dataHora);

        Result resultado = emprestimoDao.create(emprestimo);

        if(resultado instanceof SuccessResult) {

            for(ItemEmprestimo item:emprestimo.getItens()){
            
            Material materialItem = item.getMaterial();

            int quantidade = materialItem.getQuantidade() - item.getQuantidade();

            Material novoMaterial = new Material(materialItem.getIdMaterial(), materialItem.getNomeMaterial(), quantidade);

            }
        }
        
        return resultado;

    }

    private Aluno carregaAlunoEmprestimo(int id){
        return alunoDao.getAlunoFromEmprestimo(id);
    }

    private void carregarMaterialItemEmprestimo(Emprestimo emprestimo){

        for(ItemEmprestimo item:emprestimo.getItens()){
            item.setMaterial(materialDao.getMaterialItem(item.getIdItemEmprestimo()));
        }
    }

    public List<Emprestimo> listarEmprestimo(){

        List<Emprestimo> emprestimos = emprestimoDao.getAll();

        for(Emprestimo emprestimo:emprestimos){
            emprestimo.setAluno(carregaAlunoEmprestimo(emprestimo.getIdEmprestimo()));
        }
        
        return emprestimos;

    }


}