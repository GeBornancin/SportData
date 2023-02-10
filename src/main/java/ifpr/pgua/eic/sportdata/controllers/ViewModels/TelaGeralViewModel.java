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

    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;
    private StringProperty spMaterial = new SimpleStringProperty();
    private StringProperty spQuantidade = new SimpleStringProperty();

    private BooleanProperty podeEditar = new SimpleBooleanProperty(true);
    private StringProperty operacao = new SimpleStringProperty("Editar");
    private boolean atualizar = false;

    public BooleanProperty podeEditarProperty() {

        return podeEditar;
    }

    public StringProperty operacaoProperty() {

        return operacao;
    }
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

        dataEmprestimo  = LocalDateTime.now();
        
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

    private void updateListAluno() {
        obsAlunos.clear();
        for (Aluno a : alunosRepository.listarAluno()) {
            obsAlunos.add(new AlunoRow(a));
        }

    }

    private void updateListEmprestimo() {
        obsEmprestimos.clear();
        for (Emprestimo e : emprestimosRepository.listarEmprestimo()) {
            obsEmprestimos.add(new EmprestimoRow(e));
        }
    }


    public void carregaListas(){
       materiais.clear();
       materiais.addAll(materiaisRepository.listarMaterial());
    }
    
    public StringProperty getMaterial(){

        return  spMaterial;
    }

    public StringProperty getQuantidadeProperty(){
        return spQuantidade;
    }

    public ObjectProperty<Aluno> getAlunoProperty(){
        return alunoProperty;
    }

    public ObjectProperty<Material> getMaterialProperty(){
        return materialProperty;
    }

    public void emprestarItem(){


        Aluno aluno = Sessao.getInstance().getAluno();
        Material material = materialSelecionadoProperty.get().getMaterial();
        int quantidadeEmprestada = Integer.parseInt(spQuantidade.getValue());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yy H:mm");
        String text = dataEmprestimo.format(formatter);
        LocalDateTime dataEmprestimoFormat = LocalDateTime.parse(text, formatter);
        LocalDateTime dataDevolucao = LocalDateTime.parse(text, formatter);

        

        emprestimosRepository.cadastrar(dataEmprestimoFormat, aluno, material, quantidadeEmprestada, dataDevolucao);

        updateListMaterial();
        updateListEmprestimo();
        
        System.out.println(""+ aluno + material + dataEmprestimoFormat + quantidadeEmprestada);

    }

    
    
    public void preencheTextFieldsParaAtualizar(){

        operacao.setValue("Editar");
        podeEditar.setValue(false);
        atualizar = true;
    
        if(materialSelecionadoProperty.get() != null){
            Material material = materialSelecionadoProperty.get().getMaterial();

            spMaterial.setValue(material.getNomeMaterial());
            
         
            
            
        }

    }
    

}
