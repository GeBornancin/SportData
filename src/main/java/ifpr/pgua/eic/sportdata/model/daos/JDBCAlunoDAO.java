package ifpr.pgua.eic.sportdata.model.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ifpr.pgua.eic.sportdata.model.FabricaConexoes;
import ifpr.pgua.eic.sportdata.model.entities.Aluno;
import ifpr.pgua.eic.sportdata.model.results.Result;

public class JDBCAlunoDAO implements AlunoDAO {

    private FabricaConexoes fabricaConexoes;

    public JDBCAlunoDAO(FabricaConexoes fabricaConexoes){
        this.fabricaConexoes = fabricaConexoes;
    }

	@Override
	public Result create(Aluno aluno) {
		try{
            Connection con = fabricaConexoes.getConnection();
            
            PreparedStatement pstm = con.prepareStatement("INSERT INTO alunos(idAluno, cpf,nomeAluno,turma,senha) VALUES (?,?,?,?)");

            pstm.setInt(1, aluno.getIdAluno());
            pstm.setInt(2, aluno.getCpf());
            pstm.setString(3, aluno.getNomeAluno());
            pstm.setString(4, aluno.getTurma());
            pstm.setInt(5, aluno.getSenha());

            pstm.execute();

            pstm.close();
            con.close();
            return Result.success("Aluno criado com sucesso.");

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return Result.fail(e.getMessage());
        }
	}

	@Override
	public Result update(int cpf, Aluno aluno) {
		// TODO Auto-generated method stub
		return null;
	}

    private Aluno buildFrom(ResultSet result) throws SQLException{
        
        int idAluno = result.getInt("idAluno");
        int cpf = result.getInt("cpf");
        String nomeAluno = result.getString("nomeAluno");
        String turma = result.getString("turma");
        int senha = result.getInt("senha");
        
        Aluno aluno = new Aluno(idAluno, cpf, nomeAluno, turma, senha);

        return aluno;
    }

	@Override
	public List<Aluno> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Aluno getByIdAluno(int idAluno) {

		Aluno aluno = null;
        try{
            //criando uma conexão
            Connection con = fabricaConexoes.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM alunos WHERE idAluno=?");

            pstm.setInt(1, idAluno);

            ResultSet rs = pstm.executeQuery();
            
            while(rs.next()){
                aluno = buildFrom(rs);
            }


            rs.close();
            pstm.close();
            con.close();

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        return aluno;
	}

	@Override
	public Result delete(int cpf) {
		// TODO Auto-generated method stub
		return null;
	}

	
    
}