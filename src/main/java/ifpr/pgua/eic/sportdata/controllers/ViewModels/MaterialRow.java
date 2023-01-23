package ifpr.pgua.eic.sportdata.controllers.ViewModels;

import ifpr.pgua.eic.sportdata.model.entities.Material;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// MATERIAL nomeMaterial QUANTIDADE

public class MaterialRow {

    private Material material;

    public MaterialRow(Material material){
        this.material = material;
    }

    public Material getMaterial(){
        return material;
    }

    public StringProperty idMaterialProperty(){
        return new SimpleStringProperty(String.valueOf(material.getIdMaterial()));
    }

    public StringProperty nomeMaterialProperty(){
        return new SimpleStringProperty(String.valueOf(material.getNomeMaterial()));
    }

    public StringProperty quantidadeProperty(){
        return new SimpleStringProperty(String.valueOf(material.getQuantidade()));
    }

}