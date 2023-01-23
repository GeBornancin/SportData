package ifpr.pgua.eic.sportdata.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.sportdata.model.FabricaConexoes;
import ifpr.pgua.eic.sportdata.model.entities.Material;
import ifpr.pgua.eic.sportdata.model.results.Result;

public class JDBCMaterialDAO implements MaterialDAO {
    private static final String INSERT = "INSERT INTO pi_material(nomeMaterial,quantidade) VALUES (?,?)";
    private static final String UPDATE = "UPDATE pi_material set nomeMaterial=?, quantidade? WHERE idMaterial=?";
    private static final String SELECT_ALL = "SELECT * FROM pi_material";
    private static final String SELECT_ID = "SELECT * FROM pi_material WHERE idMaterial=?";

    private FabricaConexoes fabricaConexoes;

    public JDBCMaterialDAO(FabricaConexoes fabricaConexoes){
        this.fabricaConexoes = fabricaConexoes;
    }

	@Override
	public Result create(Material obj) {
        try{
            Connection con = fabricaConexoes.getConnection();

            PreparedStatement pstm = con.prepareStatement(INSERT);

            pstm.setString(1, obj.getNomeMaterial());
            pstm.setInt(2, obj.getQuantidade());
            
            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Material criado com sucesso");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return Result.fail(e.getMessage());
        }
	}

	@Override
	public Result update(int idMaterial, Material obj) {
		try{
            Connection con = fabricaConexoes.getConnection();

            PreparedStatement pstm = con.prepareStatement(UPDATE);

            pstm.setString(1, obj.getNomeMaterial());
            pstm.setInt(2, obj.getQuantidade());
            pstm.setInt(3, idMaterial);

            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Material atualizado com sucesso");


        }catch(SQLException e){
            System.out.println(e.getMessage());
            return Result.fail(e.getMessage());
        }
		
	}

    private Material buildFrom(ResultSet rs) throws SQLException{

        int id = rs.getInt("idMaterial");
        String nomeMaterial = rs.getString("nomeMaterial");
        int quantidade = rs.getInt("quantidade");

        Material material = new Material(id, nomeMaterial, quantidade);

        return material;
    }

	@Override
	public List<Material> getAll() {
		List<Material> materiais = new ArrayList<>();
        try{
            Connection con = fabricaConexoes.getConnection();

            PreparedStatement pstm = con.prepareStatement(SELECT_ALL);

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                Material material = buildFrom(rs);
                materiais.add(material);
            }

            rs.close();
            pstm.close();
            con.close();

            return materiais;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
	}

	@Override
	public Material getById(int idMaterial) {
		try{
            //criando uma conexão
            Connection con = fabricaConexoes.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement(SELECT_ID);

            pstm.setInt(1, idMaterial);

            ResultSet rs = pstm.executeQuery();
            Material material = null; 

            while(rs.next()){
                material = buildFrom(rs); 
            }

            rs.close();
            pstm.close();
            con.close();

            return material;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
	}

	@Override
	public Result delete(int idMaterial) {
		// TODO Auto-generated method stub
		return null;
	}
    
}





