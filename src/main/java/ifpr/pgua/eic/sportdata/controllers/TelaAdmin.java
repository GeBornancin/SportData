package ifpr.pgua.eic.sportdata.controllers;

import java.net.URL;
import java.util.ResourceBundle;


import javax.swing.table.TableColumnModel;

import ifpr.pgua.eic.sportdata.App;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.AlunoRow;
import ifpr.pgua.eic.sportdata.controllers.ViewModels.TelaAdminViewModel;
import ifpr.pgua.eic.sportdata.utils.Navigator.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class TelaAdmin extends BaseController implements Initializable {

    @FXML
    private void carregaTelaGeral(){
        App.changeScreenRegion("GERAL", BorderPaneRegion.CENTER);
    }

    @FXML
    private TableColumn<AlunoRow, String> tbcNomeAluno;

    @FXML
    private TableColumn<AlunoRow, String> tbcCpf;

    @FXML
    private TableColumn<AlunoRow, String> tbcTurma;
    
    @FXML
    private TableColumn<AlunoRow, String> tbcSenha;


    @FXML 
    private Button btEditarAluno;

    @FXML
    private Button btExcluirAluno;
    

    @FXML 
    private TableView<AlunoRow> tbAlunos;

    @FXML 
    private TelaAdminViewModel viewModel;

    public TelaAdmin(TelaAdminViewModel viewModel){
        this.viewModel = viewModel;
    }


    

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    
        tbcNomeAluno.setCellValueFactory(new PropertyValueFactory<>("nomeAluno"));
        tbcNomeAluno.setCellFactory(TextFieldTableCell.forTableColumn());
        // tbcNomeAluno.setOnEditCommit();

        tbcCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tbcCpf.setCellFactory(TextFieldTableCell.forTableColumn());

        
        tbcTurma.setCellValueFactory(new PropertyValueFactory<>("turma"));
        tbcTurma.setCellFactory(TextFieldTableCell.forTableColumn());

        tbcSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        tbcSenha.setCellFactory(TextFieldTableCell.forTableColumn());


        tbAlunos.setItems(viewModel.getAlunos());

        viewModel.selecionadoProperty().bind(tbAlunos.getSelectionModel().selectedItemProperty());

    }

    @FXML
    private void atualizar(){
        viewModel.atualizar();
    }

}
