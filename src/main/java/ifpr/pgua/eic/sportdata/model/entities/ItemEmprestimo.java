package ifpr.pgua.eic.sportdata.model.entities;

public class ItemEmprestimo {

    private int idItemEmprestimo;
    private Material material;
    private int quantidade;
    
    
    public int getIdItemEmprestimo() {
        return idItemEmprestimo;
    }
    public void setIdItemEmprestimo(int idItemEmprestimo) {
        this.idItemEmprestimo = idItemEmprestimo;
    }
    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    
}
