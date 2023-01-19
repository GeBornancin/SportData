package ifpr.pgua.eic.sportdata.model.entities;

public class Material {
    
    private String nomeMaterial;
    private int quantidade;
    


    public Material(String nomeMaterial, Integer quantidade) {
        this.nomeMaterial = nomeMaterial;
        this.quantidade = quantidade;
    }


    public String getNomeMaterial() {
        return nomeMaterial;
    }


    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }


    public Integer getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    
    
}
