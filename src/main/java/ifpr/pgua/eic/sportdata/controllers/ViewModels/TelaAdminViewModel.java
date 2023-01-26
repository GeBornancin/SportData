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




    //GERAL
    
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

    
    /*
     * Aqui são definidas a propriedades que serão ligadas com os
     * textfield da tela.
     */

    private StringProperty spNomeAluno = new SimpleStringProperty();
    private StringProperty spCpf = new SimpleStringProperty();
    private StringProperty spTurma = new SimpleStringProperty();
    private StringProperty spSenha = new SimpleStringProperty();

    /*
     * Aqui são definidas duas propriedades para controlar o texto
     * de um dos botões da tela e também se os textfields tfNome e tfCpf podem
     * ser editados.
     */

    private BooleanProperty podeEditar = new SimpleBooleanProperty(true);
    private StringProperty operacao = new SimpleStringProperty("Editar");
    private boolean atualizar = false;
    
    /* Lista que será utilizada para povar a TableView */
    
    private ObservableList<AlunoRow> obsAlunos = FXCollections.observableArrayList();


     /* Objeto que serve para indicar qual linha da tabela está selecionada. */

    private ObjectProperty<AlunoRow> selecionado = new SimpleObjectProperty<>();

    public ObjectProperty<AlunoRow> selecionadoProperty() {

        return selecionado;
    }  

    public StringProperty operacaoProperty() {

        return operacao;
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

    public BooleanProperty podeEditarProperty() {

        return podeEditar;
    }


    public void atualizar() {

        operacao.setValue("Editar");
        podeEditar.setValue(false);
        atualizar = true;
        Aluno aluno = selecionado.get().getAluno();
        spNomeAluno.setValue(aluno.getNomeAluno());
        spCpf.setValue(aluno.getCpf());
        spTurma.setValue(aluno.getTurma());
        spSenha.setValue(aluno.getSenha());

        String nome = spNomeAluno.getValue();
        String cpf = spCpf.getValue();
        String turma = spTurma.getValue();
        String senha = spSenha.getValue();

        if(atualizar) {
            alunosRepository.atualizarAlunos(cpf, nome, turma, senha);
        }
    }

    


    public void limpar() {
       spCpf.setValue("");
        spNomeAluno.setValue("");
        spTurma.setValue("");
        spSenha.setValue("");

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
