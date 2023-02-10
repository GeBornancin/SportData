package ifpr.pgua.eic.sportdata.model.daos;

import ifpr.pgua.eic.sportdata.model.entities.Material;
import ifpr.pgua.eic.sportdata.model.results.Result;

import java.util.List;

public interface MaterialDAO {

    Result create(Material obj);
    Result update(int idMaterial, Material obj);
    List<Material> listAll();
    Material getById(int idMaterial);
    Result deleteMaterial(int idMaterial);
    Material getMaterialFromEmprestimo(int idEmprestimo);
}
