package ifpr.pgua.eic.sportdata.controllers.ViewModels;

import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.entities.Material;
import ifpr.pgua.eic.sportdata.model.repositories.AlunosRepository;
import ifpr.pgua.eic.sportdata.model.repositories.MateriaisRepository;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaAdminViewModel {
    
    private AlunosRepository alunosRepository;
    private MateriaisRepository materiaisRepository;

    public TelaAdminViewModel(AlunosRepository alunosRepository,
                            MateriaisRepository materiaisRepository) {

        this.alunosRepository = alunosRepository;
        this.materiaisRepository = materiaisRepository;

        updateList();
    }
    

    private void updateList() {
        obsAlunos.clear();
        for(Aluno a : alunosRepository.Listar()) {
            obsAlunos.add(new AlunoRow(a));
        }
            // obsMateriais.clear();
            // for(Material material:materiaisRepository.getMaterials()){
            //     obsMateriais.add(new MaterialRow(material));
            // }
    }
   

    // ALUNO

    private StringProperty spNomeAluno = new SimpleStringProperty();
    private StringProperty spCpf = new SimpleStringProperty();
    private StringProperty spTurma = new SimpleStringProperty();
    private StringProperty spSenha = new SimpleStringProperty();

    
    public ObservableList<AlunoRow> obsAlunos = FXCollections.observableArrayList();

    public ObjectProperty<AlunoRow> selecionado = new SimpleObjectProperty<>();

    private StringProperty operacao = new SimpleStringProperty("Editar");
    private boolean atualizar = false;

    public ObjectProperty<AlunoRow> selecionadoProperty() {
            return selecionado;
    }  

    public StringProperty operacaoProperty() {
        return operacao;
    } 

    public ObservableList<AlunoRow> getAlunos() {
        return this.obsAlunos;
    }

    public void atualizar() {
        operacao.setValue("Editar");
        atualizar = true;
        Aluno aluno = selecionado.get().getAluno();
        spNomeAluno.setValue(aluno.getNomeAluno());
        spCpf.setValue(aluno.getCpf());
        spTurma.setValue(aluno.getTurma());
        spSenha.setValue(aluno.getSenha());
    }


    



    // MATERIAL

    public ObservableList<MaterialRow> obsMateriais = FXCollections.observableArrayList();

    private StringProperty nomeMaterialProperty = new SimpleStringProperty();
    private StringProperty quantidadeProperty = new SimpleStringProperty();

    public StringProperty  nomeMaterialProperty(){
        return nomeMaterialProperty;
    }

    public StringProperty quantidadeProperty(){

        return quantidadeProperty;
    }


}
