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

    public Result adicionarMaterial(String nomeMaterial, int quantidade){
        if(quantidade < 0){
            return Result.fail("Quantidade invalida");
        }else{
            Material material = new Material(nomeMaterial, quantidade);
            return dao.create(material);
        }

    }

    public List<Material> listarMaterial(){
        materiais = dao.listAll();
        return Collections.unmodifiableList(materiais);
    }

    public Result atualizarMaterial(int idMaterial,String nomeMaterial, int novaQuantidade){

        Optional<Material> busca = materiais.stream().filter(mat->mat.getNomeMaterial().equals(nomeMaterial)).findFirst();
        if(busca.isPresent()){
            Material material = busca.get();
            material.setQuantidade(novaQuantidade);
            return dao.update(idMaterial, material);
        }
        
        if(novaQuantidade < 0) {
            return Result.fail("Quantidade invalida");
        }

        return Result.fail("Material não encontrado");
            

    }

    public Material getMaterialByNome(String nomeMaterial){
        Material material = null;

        for(Material m: materiais){
            if(m.getNomeMaterial().equals(nomeMaterial)){
                material = m;
            }
        }
        return material;
    }

    public Result deleteMaterial(int idMaterial){

        return dao.deleteMaterial(idMaterial);
    }

    
}