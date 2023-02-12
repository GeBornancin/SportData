package ifpr.pgua.eic.sportdata.model.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public class Emprestimo {
    
    private int idEmprestimo;
    private Aluno aluno;
    private Material material;
    private int quantidadeEmprestada;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;

    
    public Emprestimo(Aluno aluno, Material material, int quantidadeEmprestada, LocalDateTime dataEmprestimo, LocalDateTime dataDevolucao) {
        this.aluno = aluno;
        this.material = material;
        this.quantidadeEmprestada = quantidadeEmprestada;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Emprestimo(int idEmprestimo, LocalDateTime dataEmprestimo, int quantidadeEmprestada,
            LocalDateTime dataDevolucao) {
        this.idEmprestimo = idEmprestimo;
        this.quantidadeEmprestada = quantidadeEmprestada;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
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

        public Material getMaterial() {
		    return material;
	    }   

        public void setMaterial(Material material) {
            this.material = material;
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

        public int getQuantidadeEmprestada() {
            return quantidadeEmprestada;
        }

            public void setQuantidadeEmprestada(int quantidadeEmprestada) {
            this.quantidadeEmprestada = quantidadeEmprestada;
        }

            @Override
            public String toString() {
                return "Emprestimo [idEmprestimo=" + idEmprestimo + ", aluno=" + aluno + ", material=" + material
                        + ", quantidadeEmprestada=" + quantidadeEmprestada + ", dataEmprestimo=" + dataEmprestimo
                        + ", dataDevolucao=" + dataDevolucao + "]";
            }
       
}
