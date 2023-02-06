package ifpr.pgua.eic.sportdata.controllers.ViewModels;

import ifpr.pgua.eic.sportdata.model.entities.ItemEmprestimo;
import ifpr.pgua.eic.sportdata.model.entities.Material;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;

public class ItemEmprestimoRow{

    private ItemEmprestimo itemEmprestimo;

    public ItemEmprestimoRow(ItemEmprestimo itemEmprestimo){
        this.itemEmprestimo = itemEmprestimo;
    }

    public ItemEmprestimo getItemEmprestimo(){
        return itemEmprestimo;
    }

    public StringProperty idItemEmprestimoProperty() {
        return new SimpleStringProperty(String.valueOf(itemEmprestimo.getIdItemEmprestimo()));
    }

    public StringProperty MaterialProperty() {
        return new SimpleStringProperty(String.valueOf(itemEmprestimo.getMaterial()));
    }

    public StringProperty quantidadeProperty() {
        return new SimpleStringProperty(String.valueOf(itemEmprestimo.getQuantidade()));
    }

    // private int idItemEmprestimo;
    // private Material material;
    // private int quantidade;

}