package ifpr.pgua.eic.sportdata.model.entities;

public class Material {
    
    private int idMaterial;
    private String nomeMaterial;
    private int quantidade;

    
    public Material(int idMaterial, String nomeMaterial, int quantidade) {
        this.idMaterial = idMaterial;
        this.nomeMaterial = nomeMaterial;
        this.quantidade = quantidade;
    }

    

    public Material(String nomeMaterial, int quantidade) {
        this.nomeMaterial = nomeMaterial;
        this.quantidade = quantidade;
    }



    public int getIdMaterial() {
        return idMaterial;
    }


    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }


    public String getNomeMaterial() {
        return nomeMaterial;
    }


    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }


    public int getQuantidade() {
        return quantidade;
    }


    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    

    @Override
    public String toString() {
        return nomeMaterial;
    }
   
    
}
