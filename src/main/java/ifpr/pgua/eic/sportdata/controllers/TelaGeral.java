package ifpr.pgua.eic.sportdata.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Result;

import ifpr.pgua.eic.sportdata.App;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.ItemEmprestimoRow;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.MaterialRow;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaGeralViewModel;
import ifpr.pgua.eic.sportdata.model.entities.Emprestimo;
import ifpr.pgua.eic.sportdata.model.entities.ItemEmprestimo;
import ifpr.pgua.eic.sportdata.model.entities.Material;
import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class TelaGeral extends BaseController implements Initializable {

    @FXML
    private TableView<MaterialRow> tbItensDisponiveis;

    @FXML
    private TableColumn<ItemEmprestimo, String> tbcNomeMaterial;

    @FXML
    private TableColumn<ItemEmprestimo, String> tbcQuantidade;

    ///////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private TableView<ItemEmprestimo> tbEmprestimos;

    @FXML
    private TableColumn<Emprestimo, String> tbcAluno;

    @FXML
    private TableColumn<Emprestimo, String> tbcItensEmprestimo;

    @FXML
    private TableColumn<Emprestimo, String> tbcData;

    @FXML
    private ComboBox<Material> cbMaterial;


    @FXML
    private TextField tfQuantidade;

    private TelaGeralViewModel viewModel;

    public TelaGeral(TelaGeralViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @FXML
    private void carregaTelaLogin() {
        App.changeScreenRegion("LOGIN", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaAdmin() {
        App.changeScreenRegion("ADMIN", BorderPaneRegion.CENTER);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        
///////////////////////////////////////////////////////////////////////////////////////////////
        tbcNomeMaterial.setCellValueFactory(new PropertyValueFactory<>("nomeMaterial"));
        tbcQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

        tbItensDisponiveis.setItems(viewModel.getMateriais());
///////////////////////////////////////////////////////////////////////////////////////////////





        this.cbMaterial.setItems(viewModel.getMateriaisCb());

        this.tfQuantidade.textProperty().bindBidirectional(viewModel.getQuantidadeProperty());



        this.cbMaterial.selectionModelProperty().addListener((InvalidationListener) new ChangeListener<Material>() {
            @Override
            public void changed(ObservableValue<? extends Material> arg0, Material arg1, Material arg2) {
                viewModel.getMaterialProperty().set(arg2);

            }

        });

        tbcAluno.setCellValueFactory(emprestimo -> new SimpleStringProperty(emprestimo.getValue().getAluno().getNomeAluno()));
        tbcItensEmprestimo.setCellValueFactory(emprestimo -> new SimpleStringProperty(emprestimo.getValue().getItens()+""));
        tbcData.setCellValueFactory(emprestimo -> new SimpleStringProperty(emprestimo.getValue().getDataEmprestimo()+""));
        
        tbEmprestimos.setItems(viewModel.getItensEmprestimo());

        viewModel.carregaListas();

    }

    @FXML
    private void emprestarItem(){
        viewModel.emprestarItem();
    

    }

}