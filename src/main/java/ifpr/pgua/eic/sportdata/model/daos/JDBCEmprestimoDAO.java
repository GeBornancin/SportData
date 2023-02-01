package ifpr.pgua.eic.sportdata.model.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import ifpr.pgua.eic.sportdata.model.FabricaConexoes;
import ifpr.pgua.eic.sportdata.model.entities.Emprestimo;
import ifpr.pgua.eic.sportdata.model.entities.ItemEmprestimo;
import ifpr.pgua.eic.sportdata.model.results.Result;


public class JDBCEmprestimoDAO implements EmprestimoDAO{

    private static final String INSERT = "INSERT INTO pi_emprestimo(dataEmprestimo, idAluno) VALUES (?,?,?,?)";
    private static final String INSERT_ITEM = "INSERT INTO pi_itememprestimo(idEmprestimo,idMaterial,quantidade) VALUES (?,?,?,?)";
    private static final String SELECT_ALL = "SELECT * FROM emprestimos";

    private FabricaConexoes fabricaConexoes;

    public JDBCEmprestimoDAO(FabricaConexoes fabricaConexoes){
        this.fabricaConexoes = fabricaConexoes;
    }

    @Override
    public Result create(Emprestimo emprestimo) {
        try{
            Connection con = fabricaConexoes.getConnection();

            PreparedStatement pstm = con.prepareStatement(INSERT,Statement.RETURN_GENERATED_KEYS);

            pstm.setTimestamp(1, Timestamp.valueOf(emprestimo.getDataEmprestimo()));
            pstm.setInt(2, emprestimo.getAluno().getIdAluno());

            pstm.execute();

            ResultSet resultSet = pstm.getGeneratedKeys();
            resultSet.next();
            int idEmprestimo = resultSet.getInt(1);

            PreparedStatement pstmItem = con.prepareStatement(INSERT_ITEM);

            for(ItemEmprestimo item:emprestimo.getItens()){
                pstmItem.setInt(1, idEmprestimo);
                pstmItem.setInt(2, item.getMaterial().getIdMaterial());
                pstmItem.setInt(3, item.getQuantidade());

                pstmItem.execute();
            }

            pstmItem.close();
            pstm.close();

            con.close();

            return Result.success("Emprestimo criado com sucesso");

        }catch(SQLException e){
            return Result.fail(e.getMessage());
        }
    }

    public List<Emprestimo> getAll() {
        List<Emprestimo> emprestimos = new ArrayList<>();

        try{
            Connection con = fabricaConexoes.getConnection();

            PreparedStatement pstm = con.prepareStatement(SELECT_ALL);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                Emprestimo emprestimo = buildFrom(rs);
                emprestimos.add(emprestimo);
            }

            rs.close();
            pstm.close();
            con.close();

            return emprestimos;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    private Emprestimo buildFrom(ResultSet rs) throws SQLException {
       
        int idEmprestimo = rs.getInt("idEmprestimo");
        LocalDateTime dataEmprestimo = rs.getTimestamp("dataEmprestimo").toLocalDateTime();

        Emprestimo emprestimo = new Emprestimo(idEmprestimo, dataEmprestimo);
        
        return emprestimo;
    }

    private List<ItemEmprestimo> loadItens(int idEmprestimo) throws SQLException{

        List<ItemEmprestimo> itens = new ArrayList<>();

        Connection con = fabricaConexoes.getConnection();

        PreparedStatement pstm = con.prepareStatement("SELECT * FROM itememprestimo WHERE idEmprestimo=?");

        pstm.setInt(1,idEmprestimo);

        ResultSet result = pstm.executeQuery();

        while(result.next()){
            int idItem = result.getInt("idItemEmprestimo");
            int quantidade = result.getInt("quantidade");

            ItemEmprestimo item = new ItemEmprestimo();
            item.setIdItemEmprestimo(idItem);
            item.setQuantidade(quantidade);

            itens.add(item);
        }

        result.close();
        pstm.close();
        con.close();

        return itens;

    }
}