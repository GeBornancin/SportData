package ifpr.pgua.eic.sportdata.controllers.ViewModels;

import java.time.LocalDateTime;
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
import javafx.scene.control.Alert;

public class TelaGeralViewModel {

    private StringProperty spMaterial = new SimpleStringProperty();
    private StringProperty spQuantidade = new SimpleStringProperty();
    private StringProperty spAluno = new SimpleStringProperty();
    private StringProperty operacao = new SimpleStringProperty("Editar");
    private BooleanProperty podeEditar = new SimpleBooleanProperty(true);
    private BooleanProperty desativado = new SimpleBooleanProperty(true);

    private ObservableList<EmprestimoRow> obsEmprestimos = FXCollections.observableArrayList();
    private ObservableList<MaterialRow> obsMateriais = FXCollections.observableArrayList();

    private ObjectProperty<MaterialRow> materialSelecionadoProperty = new SimpleObjectProperty<>();
    private ObjectProperty<EmprestimoRow> emprestimoSelecionadoProperty = new SimpleObjectProperty<>();

    private MateriaisRepository materiaisRepository;
    private AlunosRepository alunosRepository;
    private EmprestimosRepository emprestimosRepository;

    Alert alert = new Alert(Alert.AlertType.NONE);

    public TelaGeralViewModel(AlunosRepository alunosRepository,
            MateriaisRepository materiaisRepository,
            EmprestimosRepository emprestimosRepository) {

        this.alunosRepository = alunosRepository;
        this.materiaisRepository = materiaisRepository;
        this.emprestimosRepository = emprestimosRepository;

        updateListMaterial();
        updateListEmprestimo();
    }

    /* Tabela de Materiais disponiveis */
    public ObjectProperty<MaterialRow> getMaterialSelecionadoProperty() {
        return materialSelecionadoProperty;
    }

    public ObservableList<MaterialRow> getMateriais() {

        return this.obsMateriais;
    }

    private void updateListMaterial() {
        obsMateriais.clear();
        for (Material m : materiaisRepository.listarMaterial()) {
            obsMateriais.add(new MaterialRow(m));
        }
    }
    /* Componentes Tela para emprestrar */

    public StringProperty getMaterial() {

        return spMaterial;
    }

    public StringProperty getQuantidadeProperty() {
        return spQuantidade;
    }

    public StringProperty getAlunoStringProperty() {
        return spAluno;
    }

    public BooleanProperty podeEditarProperty() {

        return podeEditar;
    }

    public StringProperty operacaoProperty() {

        return operacao;
    }

    /* Tabela de Emprestimos */

    public ObjectProperty<EmprestimoRow> getEmprestimoSelecionadoProperty() {
        return emprestimoSelecionadoProperty;
    }

    public ObservableList<EmprestimoRow> getEmprestimos() {

        return this.obsEmprestimos;
    }

    public BooleanProperty desativadoProperty() {
        return desativado;
    }

    private void updateListEmprestimo() {
        obsEmprestimos.clear();
        for (Emprestimo e : emprestimosRepository.listarEmprestimo()) {
            obsEmprestimos.add(new EmprestimoRow(e));
        }
    }

    /* Métodos empréstimo */

    public Result emprestarItem() {

        Aluno aluno = Sessao.getInstance().getAluno();
        Material material = materiaisRepository.getMaterialByNome(spMaterial.getValue());
        int quantidadeEmprestada = Integer.valueOf(spQuantidade.getValue());
        LocalDateTime dataEmprestimo = LocalDateTime.now();
        LocalDateTime dataDevolucao = null;

        emprestimosRepository.cadastrar(dataEmprestimo, aluno, material, quantidadeEmprestada, dataDevolucao);

        updateListMaterial();
        updateListEmprestimo();
        limpar();

        return null;
    }

    public void preencheTextFieldsParaAtualizar() {

        operacao.setValue("Editar");
        podeEditar.setValue(false);

        if (materialSelecionadoProperty.get() != null) {
            Material material = materialSelecionadoProperty.get().getMaterial();
            Aluno aluno = Sessao.getInstance().getAluno();

            spMaterial.setValue(material.getNomeMaterial());
            spAluno.setValue(aluno.getNomeAluno());
        }
    }

    public void devolver() {

        Emprestimo emprestimo = emprestimoSelecionadoProperty.get().getEmprestimo();
        Aluno alunoDaSessao = Sessao.getInstance().getAluno();

        
        if (emprestimo.getAluno().getNomeAluno().equals(alunoDaSessao.getNomeAluno())) {
            if (emprestimo.getDataDevolucao() == null) {
                emprestimosRepository.devolver(emprestimo);

                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Material Devolvido");
                alert.showAndWait();

          
                updateListMaterial();
                updateListEmprestimo();
                limpar();
              } else {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Material já foi devolvido");
                alert.showAndWait();
                desativado.setValue(true);
              }
        } else {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Este emprestimo não pertence ao Aluno: "+ alunoDaSessao);
            alert.showAndWait();
            desativado.setValue(true);

        }

    }

    public void atualizarEmprestimo() {
        desativado.setValue(false);
    }

    public void encerrarSessao() {
        Sessao sessao = Sessao.getInstance();
        Aluno aluno = sessao.getAluno();
        
        alert.setAlertType(Alert.AlertType.INFORMATION);
        if (aluno != null) {
            alert.setHeaderText(aluno.getNomeAluno() + " deslogado com sucesso!");
        } else {
            alert.setHeaderText("Não há usuário logado");
        }
        alert.showAndWait();
        sessao.setAluno(null);
        
    }

    public void limpar() {

        spAluno.setValue("");
        spMaterial.setValue("");
        spQuantidade.setValue("");
        desativado.setValue(true);
    }


}
