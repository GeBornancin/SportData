package ifpr.pgua.eic.sportdata.model.daos;

import ifpr.pgua.eic.sportdata.model.entities.Material;
import ifpr.pgua.eic.sportdata.model.results.Result;

import java.util.List;

public interface MaterialDAO {

    Result create(Material obj);
    Result update(Material obj);
    List<Material> listAll();
    Material getById(int idMaterial);
    Material getMaterialItem(int ItemId);
    Result deleteMaterial(int idMaterial);
    
}
