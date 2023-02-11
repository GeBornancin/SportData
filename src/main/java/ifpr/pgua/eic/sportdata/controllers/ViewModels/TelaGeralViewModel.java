package ifpr.pgua.eic.sportdata.controllers.ViewModels;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.entities.Material;
import ifpr.pgua.eic.sportdata.model.entities.Emprestimo;
import ifpr.pgua.eic.sportdata.model.entities.Sessao;
import ifpr.pgua.eic.sportdata.model.repositories.AlunosRepository;
import ifpr.pgua.eic.sportdata.model.repositories.EmprestimosRepository;
import ifpr.pgua.eic.sportdata.model.repositories.MateriaisRepository;
import ifpr.pgua.eic.sportdata.model.results.Result;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaGeralViewModel {

    private StringProperty spMaterial = new SimpleStringProperty();
    private StringProperty spQuantidade = new SimpleStringProperty();
    private StringProperty spAluno = new SimpleStringProperty();


    
    private ObjectProperty<Material> materialProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Aluno> alunoProperty = new SimpleObjectProperty<>();

    private ObservableList<Emprestimo> emprestimos = FXCollections.observableArrayList();
    private ObservableList<EmprestimoRow> obsEmprestimos = FXCollections.observableArrayList();
    private ObservableList<Material> materiais = FXCollections.observableArrayList();
    private ObservableList<MaterialRow> obsMateriais = FXCollections.observableArrayList();
    private ObservableList<AlunoRow> obsAlunos = FXCollections.observableArrayList();
    
    private ObjectProperty<EmprestimoRow> dataEmprestimoProperty = new SimpleObjectProperty<>();
    private ObjectProperty<MaterialRow> materialSelecionadoProperty = new SimpleObjectProperty<>();

    public ObjectProperty<MaterialRow> getMaterialSelecionadoProperty(){
		return materialSelecionadoProperty;
    }

    public ObjectProperty<EmprestimoRow> getDataEmprestimopProperty(){
        return dataEmprestimoProperty;
    }
    

    private MateriaisRepository materiaisRepository;
    private AlunosRepository alunosRepository;
    private EmprestimosRepository emprestimosRepository;
    

    public TelaGeralViewModel(AlunosRepository alunosRepository,
            MateriaisRepository materiaisRepository,
            EmprestimosRepository emprestimosRepository){

        this.alunosRepository = alunosRepository;
        this.materiaisRepository = materiaisRepository;
        this.emprestimosRepository = emprestimosRepository;

        
        
        // dataDevolucao = LocalDateTime.now();
        updateListMaterial();
        updateListEmprestimo();
    }


    
    public ObservableList<Emprestimo> getEmprestimos() {

        return this.emprestimos;
    }


    public ObservableList<MaterialRow> getMateriais() {

        return this.obsMateriais;
    }

    public ObservableList<AlunoRow> getAlunos(){
        return this.obsAlunos;
    }

   
    private void updateListMaterial() {
        obsMateriais.clear();
        for (Material m : materiaisRepository.listarMaterial()) {
            obsMateriais.add(new MaterialRow(m));
        }
    }

    private void updateListEmprestimo() {
        obsEmprestimos.clear();
        for (Emprestimo e : emprestimosRepository.listarEmprestimo()) {
            obsEmprestimos.add(new EmprestimoRow(e));
        }
    }



    public StringProperty getMaterial(){

        return  spMaterial;
    }

    public StringProperty getQuantidadeProperty(){
        return spQuantidade;
    }

    public StringProperty getAlunoStringProperty(){
        return spAluno;
    }

    public ObjectProperty<Aluno> getAlunoProperty(){
        return alunoProperty;
    }

    public ObjectProperty<Material> getMaterialProperty(){
        return materialProperty;
    }

    public void emprestarItem(){

        Aluno aluno = alunosRepository.getAlunoByCpf(spAluno.getValue());
        Material material = materiaisRepository.getMaterialByNome(spMaterial.getValue());
        int quantidadeEmprestada = Integer.valueOf(spQuantidade.getValue());
        LocalDateTime dataEmprestimo = LocalDateTime.now();
        LocalDateTime dataDevolucao = null;

        emprestimosRepository.cadastrar(dataEmprestimo, aluno, material, quantidadeEmprestada, dataDevolucao);

        updateListEmprestimo();
        limpar();


    }

    public void limpar(){

        spAluno.setValue("");
        spMaterial.setValue("");
        spQuantidade.setValue("");
    }

    
   
    

}
