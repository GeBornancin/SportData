package ifpr.pgua.eic.sportdata.model.repositories;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import ifpr.pgua.eic.sportdata.model.daos.MaterialDAO;
import ifpr.pgua.eic.sportdata.model.entities.Material;
import ifpr.pgua.eic.sportdata.model.results.Result;

public class MateriaisRepository {

    private List<Material> materiais;
    private MaterialDAO dao;

    public MateriaisRepository(MaterialDAO dao){
        this.dao = dao;
    }

    public Result adicionarMaterial(int idMaterial, String nomeMaterial, int quantidade){
        if(quantidade < 0){
            return Result.fail("Quantidade invalida");
        }

        Material material = new Material(idMaterial, nomeMaterial, quantidade);
        return dao.create(material);

    }

    public List<Material> getMaterials(){
        materiais = dao.getAll();
        return Collections.unmodifiableList(materiais);
    }

    public Result atualizarMaterial(int idMaterial, String nomeMaterial, int quantidade){

        if(quantidade < 0) {
            return Result.fail("Quantidade invalida");
        }

        Material material = new Material(idMaterial, nomeMaterial, quantidade);
        return dao.update(idMaterial, material);

    }

    
}