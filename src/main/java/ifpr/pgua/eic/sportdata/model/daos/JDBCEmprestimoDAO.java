package ifpr.pgua.eic.sportdata.model.daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import ifpr.pgua.eic.sportdata.model.FabricaConexoes;
import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.entities.Material;
import ifpr.pgua.eic.sportdata.model.entities.Emprestimo;

import ifpr.pgua.eic.sportdata.model.results.Result;


public class JDBCEmprestimoDAO implements EmprestimoDAO{

    // private static final String INSERT = "INSERT INTO pi_emprestimo(dataEmprestimo, idAluno, quantidade, dataDevolucao) VALUES (?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM pi_emprestimo";

    private FabricaConexoes fabricaConexoes;

    public JDBCEmprestimoDAO(FabricaConexoes fabricaConexoes){
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public Result create(Emprestimo emprestimo) {

        try{
            Connection con = fabricaConexoes.getConnection();

            PreparedStatement pstm = con.prepareStatement("INSERT INTO pi_emprestimo(dataEmprestimo, idAluno, idMaterial, quantidadeEmprestada, dataDevolucao) VALUES (?,?,?,?,?)");

            System.out.println(emprestimo.getDataEmprestimo());
            System.out.println(emprestimo.getAluno().getIdAluno());
            System.out.println(emprestimo.getMaterial().getIdMaterial());
            System.out.println(emprestimo.getQuantidadeEmprestada());

            pstm.setTimestamp(1, Timestamp.valueOf(emprestimo.getDataEmprestimo()));
            pstm.setInt(2, emprestimo.getAluno().getIdAluno());
            pstm.setInt(3, emprestimo.getMaterial().getIdMaterial());
            pstm.setInt(4, emprestimo.getQuantidadeEmprestada());
            pstm.setTimestamp(5, null);

            int result = pstm.executeUpdate();
            if (result > 0) {
                pstm.close();
                con.close();
                return Result.success("Emprestimo criado com sucesso");
            } else {
                pstm.close();
                con.close();
                return Result.fail("Não foi possível criar o empréstimo");
            }
        }catch(SQLException e){
            return Result.fail(e.getMessage());
        }

    }

    @Override
public Result returnEmprestimo(Emprestimo emprestimo) {
    try {
        Connection con = fabricaConexoes.getConnection();

        PreparedStatement pstm = con.prepareStatement("UPDATE pi_emprestimo SET dataDevolucao = ? WHERE idEmprestimo = ?");
        pstm.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
        pstm.setInt(2,emprestimo.getIdEmprestimo());
        

        int result = pstm.executeUpdate();
        if (result == 0) {
            return Result.fail("Emprestimo não encontrado.");
        }

        pstm.close();
        con.close();

        return Result.success("Emprestimo devolvido com sucesso");
    } catch (SQLException e) {
        return Result.fail(e.getMessage());
    }
}
    
    private Emprestimo buildFrom(ResultSet rs) throws SQLException {
       
        int idEmprestimo = rs.getInt("idEmprestimo");
        LocalDateTime dataEmprestimo = rs.getTimestamp("dataEmprestimo").toLocalDateTime();
        int quantidadeEmprestada = rs.getInt("quantidadeEmprestada");
        LocalDateTime dataDevolucao = null;
        try {
            dataDevolucao = rs.getTimestamp("dataDevolucao").toLocalDateTime();
        } catch (NullPointerException error) {
            // TODO: handle exception
        }
        

        Emprestimo emprestimo = new Emprestimo(idEmprestimo, dataEmprestimo, quantidadeEmprestada, dataDevolucao);
        
        return emprestimo;
    }

  

    @Override
    public List<Emprestimo> getAll() {
        List<Emprestimo> lista = new ArrayList<>();

        try{
            Connection con = fabricaConexoes.getConnection();

            PreparedStatement pstm = con.prepareStatement(SELECT_ALL);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                Emprestimo emprestimo = buildFrom(rs);
                lista.add(emprestimo);
            }

            rs.close();
            pstm.close();
            con.close();

            return lista;

        }catch(SQLException e){
            System.out.println(" Erro no getAll Emprestimo" + e.getMessage());
            return Collections.emptyList();
        }
    }

    
    
}