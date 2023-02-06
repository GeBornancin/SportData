package ifpr.pgua.eic.sportdata.controllers.ViewModels;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.entities.ItemEmprestimo;
import ifpr.pgua.eic.sportdata.model.entities.Material;
import ifpr.pgua.eic.sportdata.model.entities.Sessao;
import ifpr.pgua.eic.sportdata.model.repositories.AlunosRepository;
import ifpr.pgua.eic.sportdata.model.repositories.EmprestimosRepository;
import ifpr.pgua.eic.sportdata.model.repositories.MateriaisRepository;
import ifpr.pgua.eic.sportdata.model.results.Result;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaGeralViewModel {

    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;
    private StringProperty spQuantidade = new SimpleStringProperty();

    private ObjectProperty<Material> materialProperty = new SimpleObjectProperty<>();
    private ObjectProperty<Aluno> alunoProperty = new SimpleObjectProperty<>();

    private ObservableList<Material> materiais = FXCollections.observableArrayList();
    private ObservableList<AlunoRow> obsAlunos = FXCollections.observableArrayList();
    public ObservableList<MaterialRow> obsMateriais = FXCollections.observableArrayList();

    private ObjectProperty<ItemEmprestimoRow> linhaSelecionadaProperty = new SimpleObjectProperty<>();

    private ObservableList<ItemEmprestimo> itensEmprestimo = FXCollections.observableArrayList();

    public ObjectProperty<ItemEmprestimoRow> getLinhaSelecionadaProperty(){
		return linhaSelecionadaProperty;

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
        dataDevolucao = LocalDateTime.now();
        updateListMaterial();
        updateListAluno();
    }
    
    
    
    
    
    public ObservableList<ItemEmprestimo> getItensEmprestimo(){
        return itensEmprestimo;
    }

    public ObservableList<MaterialRow> getMateriais() {

        return this.obsMateriais;
    }

    public ObservableList<AlunoRow> getAlunos(){
        return this.obsAlunos;
    }

    public ObservableList<Material> getMateriaisCb(){
        return materiais;
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


    public void carregaListas(){
       materiais.clear();
       materiais.addAll(materiaisRepository.listarMaterial());
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

    public Result emprestarItem(){

        if(materialProperty.get() == null) {
            return Result.fail("Nenhum produto adicionado");
        }

        int quantidade = 0;
        try{
            quantidade = Integer.parseInt(spQuantidade.getValue());
        }catch(NumberFormatException e) {
            return Result.fail("Quantidade invalida");
        }

        if(quantidade == 0){
            return Result.fail("");
        }

        ItemEmprestimo item = new ItemEmprestimo();
        Material material = materialProperty.get();
       
        item.setMaterial(material);
        item.setQuantidade(quantidade);

        itensEmprestimo.add(item);

        Aluno aluno = Sessao.getInstance().getAluno();

        return emprestimosRepository.cadastrar(dataEmprestimo, aluno, itensEmprestimo);
    
        
    }

    

}
