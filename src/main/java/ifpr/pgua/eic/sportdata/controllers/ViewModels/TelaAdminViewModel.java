package ifpr.pgua.eic.sportdata.controllers.ViewModels;

import com.mysql.cj.conf.IntegerProperty;

import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.entities.Material;
import ifpr.pgua.eic.sportdata.model.repositories.AlunosRepository;
import ifpr.pgua.eic.sportdata.model.repositories.MateriaisRepository;
import ifpr.pgua.eic.sportdata.model.results.Result;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TelaAdminViewModel {

    // GERAL

    private AlunosRepository alunosRepository;
    private MateriaisRepository materiaisRepository;

    public TelaAdminViewModel(AlunosRepository alunosRepository,
            MateriaisRepository materiaisRepository) {

        this.alunosRepository = alunosRepository;
        this.materiaisRepository = materiaisRepository;

        updateListAluno();
        updateListMaterial();
    }

    /*
     * Aqui são definidas duas propriedades para controlar o texto
     * de um dos botões da tela e também se os textfields tfNome e tfCpf podem
     * ser editados.
     */

    private BooleanProperty podeEditar = new SimpleBooleanProperty(true);
    private StringProperty operacao = new SimpleStringProperty("Editar");
    private boolean atualizar = false;

    public BooleanProperty podeEditarProperty() {

        return podeEditar;
    }

    public StringProperty operacaoProperty() {

        return operacao;
    }

    /******************** MATERIAL ********************/

    /*
     * Aqui são definidas a propriedades que serão ligadas com os
     * textfield da tela.
     */

    private StringProperty spNomeMaterial = new SimpleStringProperty();
    private StringProperty spQuantidade = new SimpleStringProperty();

    /* Lista que será utilizada para povar a TableView */

    public ObservableList<MaterialRow> obsMateriais = FXCollections.observableArrayList();

    private ObjectProperty<MaterialRow> selecionadoMaterial = new SimpleObjectProperty<>();

    public ObjectProperty<MaterialRow> materialSelecionadoProperty() {

        return selecionadoMaterial;
    }

    public ObservableList<MaterialRow> getMateriais() {

        return this.obsMateriais;
    }

    public StringProperty nomeMaterialProperty() {
        return spNomeMaterial;
    }

    public StringProperty quantidadeProperty() {

        return spQuantidade;
    }

    public void editarMaterial() {
        String nomeMaterial = spNomeMaterial.getValue();
        int quantidade = Integer.parseInt(spQuantidade.getValue());

        if (atualizar) {
            materiaisRepository.atualizarMaterial(nomeMaterial, quantidade);
        }
        updateListMaterial();

    }

    public void atualizarMaterial() {


        operacao.setValue("Editar");
        podeEditar.setValue(false);
        atualizar = true;
    

        Material material = selecionadoMaterial.get().getMaterial();

        spNomeMaterial.setValue(material.getNomeMaterial());
        spQuantidade.setValue(String.valueOf(material.getQuantidade()));
        

    }

    public void cadastrarMaterial() {

        String nomeMaterial = spNomeMaterial.getValue();
        int quantidade = 0;
       

        quantidade = Integer.parseInt(spQuantidade.getValue());

        materiaisRepository.adicionarMaterial(nomeMaterial, quantidade);


        
        updateListMaterial();
        limparMaterial();
    }

    public void excluirMaterial() {

        Material material = selecionadoMaterial.get().getMaterial();
        materiaisRepository.deleteMaterial(material.getIdMaterial());

        updateListMaterial();
        limparMaterial();
    }

    public void limparMaterial() {

        spNomeMaterial.setValue("");
        spQuantidade.setValue("");
        podeEditar.setValue(true);
        atualizar = false;
    }

    private void updateListMaterial() {
        obsMateriais.clear();
        for (Material m : materiaisRepository.listarMaterial()) {
            obsMateriais.add(new MaterialRow(m));
        }
    }

    /******************** ALUNO ********************/

    /*
     * Aqui são definidas a propriedades que serão ligadas com os
     * textfield da tela.
     */

    private StringProperty spNomeAluno = new SimpleStringProperty();
    private StringProperty spCpf = new SimpleStringProperty();
    private StringProperty spTurma = new SimpleStringProperty();
    private StringProperty spSenha = new SimpleStringProperty();

    /* Lista que será utilizada para povar a TableView */

    private ObservableList<AlunoRow> obsAlunos = FXCollections.observableArrayList();

    /* Objeto que serve para indicar qual linha da tabela está selecionada. */

    private ObjectProperty<AlunoRow> selecionadoAluno = new SimpleObjectProperty<>();

    public ObjectProperty<AlunoRow> selecionadoProperty() {

        return selecionadoAluno;
    }

    public ObservableList<AlunoRow> getAlunos() {

        return this.obsAlunos;
    }

    public StringProperty nomeAlunoProperty() {

        return this.spNomeAluno;
    }

    public StringProperty cpfProperty() {

        return this.spCpf;
    }

    public StringProperty turmaProperty() {

        return this.spTurma;
    }

    public StringProperty senhaProperty() {

        return this.spSenha;
    }

    public void editarAluno() {
        String nome = spNomeAluno.getValue();
        String cpf = spCpf.getValue();
        String turma = spTurma.getValue();
        String senha = spSenha.getValue();

        if (atualizar) {
            alunosRepository.atualizarAlunos(cpf, nome, turma, senha);
        }

        updateListAluno();
        limparAluno();

    }

    public void atualizarAluno() {

        operacao.setValue("Editar");
        podeEditar.setValue(false);
        atualizar = true;
        Aluno aluno = selecionadoAluno.get().getAluno();
        spNomeAluno.setValue(aluno.getNomeAluno());
        spCpf.setValue(aluno.getCpf());
        spTurma.setValue(aluno.getTurma());
        spSenha.setValue(aluno.getSenha());

    }

    public void excluirAluno() {

        Aluno aluno = selecionadoAluno.get().getAluno();
        alunosRepository.deleteAluno(aluno.getIdAluno());

        updateListAluno();
        limparAluno();

    }

    public void limparAluno() {
        spCpf.setValue("");
        spNomeAluno.setValue("");
        spTurma.setValue("");
        spSenha.setValue("");

        podeEditar.setValue(true);
        atualizar = false;

    }

    private void updateListAluno() {
        obsAlunos.clear();
        for (Aluno a : alunosRepository.listarAluno()) {
            obsAlunos.add(new AlunoRow(a));
        }

    }
}
