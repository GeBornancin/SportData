package ifpr.pgua.eic.sportdata.model.daos;

import java.util.List;

import ifpr.pgua.eic.sportdata.model.results.Result;
import ifpr.pgua.eic.sportdata.model.entities.Emprestimo;

public interface EmprestimoDAO {

    Result create(Emprestimo emprestimo);
    Result returnEmprestimo(Emprestimo emprestimo);
    List<Emprestimo> getAll();

}