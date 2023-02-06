package ifpr.pgua.eic.sportdata.model.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public class Emprestimo {
    
    private int idEmprestimo;
    private Aluno aluno;
    private List<ItemEmprestimo> itens;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;

    public Emprestimo(int idEmprestimo, Aluno aluno, List<ItemEmprestimo> itens, LocalDateTime dataEmprestimo, LocalDateTime dtDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.aluno = aluno;
        this.itens = itens;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(int idEmprestimo, LocalDateTime dataEmprestimo) {
        this.idEmprestimo = idEmprestimo;
        this.dataEmprestimo = dataEmprestimo;
    }

    public Emprestimo(Aluno aluno, List<ItemEmprestimo> itens, LocalDateTime dataEmprestimo) {
        this.aluno = aluno;
        this.itens = itens;
        this.dataEmprestimo = dataEmprestimo;
    }

    public void adicionarMaterial(Material m,int quantidade){
        
        Optional<ItemEmprestimo> item =  itens.stream().filter((it)->it.getMaterial().getIdMaterial()==m.getIdMaterial()).findFirst();
        
        if(item.isPresent()){
            ItemEmprestimo it =item.get();
            it.setQuantidade(it.getQuantidade()+quantidade);
        }else{
            ItemEmprestimo it = new ItemEmprestimo();
            it.setMaterial(m);
            it.setQuantidade(quantidade);
            itens.add(it);
        }
    }
    


    public boolean removerMaterial(Material m, int quantidade) {
        
        Optional<ItemEmprestimo> item = itens.stream().filter((it)->it.getMaterial().getIdMaterial()==m.getIdMaterial()).findFirst();

        if(item.isPresent()) {
            ItemEmprestimo it = item.get();
            it.setQuantidade(it.getQuantidade() - quantidade);
            if(it.getQuantidade() <= 0) {
                itens.remove(it);
            }
            return true;
        }
        return false;
    }


        public int getIdEmprestimo() {
            return idEmprestimo;
        }

        public void setIdEmprestimo(int idEmprestimo) {
            this.idEmprestimo = idEmprestimo;
        }

        public Aluno getAluno() {
            return aluno;
        }

        public void setAluno(Aluno aluno) {
            this.aluno = aluno;
        }

        public List<ItemEmprestimo> getItens() {
            return itens;
        }

        public void setItens(List<ItemEmprestimo> itens) {
            this.itens = itens;
        }

        public LocalDateTime getDataEmprestimo() {
            return dataEmprestimo;
        }

        public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
            this.dataEmprestimo = dataEmprestimo;
        }

        public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
        }

        public void setDataDevolucao(LocalDateTime dataDevolucao) {
            this.dataDevolucao = dataDevolucao;
        }

}
