package ifpr.pgua.eic.sportdata.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



import ifpr.pgua.eic.sportdata.App;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.MaterialRow;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaGeralViewModel;
import ifpr.pgua.eic.sportdata.model.entities.Emprestimo;
import ifpr.pgua.eic.sportdata.model.entities.Material;
import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.beans.property.SimpleStringProperty;


public class TelaGeral extends BaseController implements Initializable {

    @FXML
    private TableView<MaterialRow> tbItensDisponiveis;

    @FXML
    private TableColumn<MaterialRow, String> tbcNomeMaterial;

    @FXML
    private TableColumn<MaterialRow, String> tbcQuantidade;

    ///////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private TableView<Emprestimo> tbEmprestimos;

    @FXML
    private TableColumn<Emprestimo, String> tbcAluno;

    @FXML
    private TableColumn<Emprestimo, String> tbcMaterial;

    @FXML
    private TableColumn<Emprestimo, String> tbcData;

    @FXML
    private TextField tfMaterial;

    @FXML
    private TextField tfQuantidade;

    @FXML
    private TextField tfAluno;

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

        
        

        this.tfQuantidade.textProperty().bindBidirectional(viewModel.getQuantidadeProperty());
        this.tfMaterial.textProperty().bindBidirectional(viewModel.getMaterial());
        this.tfAluno.textProperty().bindBidirectional(viewModel.getAlunoStringProperty());
        
        

    }

    @FXML
    private void emprestarItem(){
        viewModel.emprestarItem();
    }

    
    

}