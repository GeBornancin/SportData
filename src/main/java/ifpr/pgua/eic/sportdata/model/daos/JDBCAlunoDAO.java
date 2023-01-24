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
            
            PreparedStatement pstm = con.prepareStatement("INSERT INTO pi_aluno(cpf,nomeAluno,turma,senha) VALUES (?,?,?,?)");

            pstm.setString(1, aluno.getCpf());
            pstm.setString(2, aluno.getNomeAluno());
            pstm.setString(3, aluno.getTurma());
            pstm.setString(4, aluno.getSenha());

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
	public Result update(String cpf, Aluno aluno) {
		// TODO Auto-generated method stub
		return null;
	}

    private Aluno buildFrom(ResultSet result) throws SQLException{
        
        int idAluno = result.getInt("idAluno");
        String cpf = result.getString("cpf");
        String nomeAluno = result.getString("nomeAluno");
        String turma = result.getString("turma");
        String senha = result.getString("senha");
        
        Aluno aluno = new Aluno(idAluno, cpf, nomeAluno, turma, senha);

        return aluno;
    }

	@Override
	public List<Aluno> listAll() {
		ArrayList<Aluno> alunos = new ArrayList<>();
		try{
            Connection con = fabricaConexoes.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM pi_aluno");

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                Aluno aluno = buildFrom(rs);
                alunos.add(aluno);
            }

            rs.close();
            pstm.close();
            con.close();

            return alunos;

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
       
	}

	@Override
	public Aluno getByIdAluno(int idAluno) {

		Aluno aluno = null;
        try{
            //criando uma conexão
            Connection con = fabricaConexoes.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM pi_aluno WHERE idAluno=?");

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
    public Aluno getAlunoFromEmprestimo(int idEmprestimo) {
        Aluno a = null;
        try{

            Connection con = fabricaConexoes.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT idAluno FROM pi_emprestimo WHERE idAluno=?");

            pstm.setInt(1, idEmprestimo);

            ResultSet resultSetIdAluno = pstm.executeQuery();
            resultSetIdAluno.next();

            int idAluno = resultSetIdAluno.getInt("idAluno");

            a = getByIdAluno(idAluno);

            resultSetIdAluno.close();
            pstm.close();
            con.close();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return a;
    }
 
        @Override
        public Result deleteAluno(int idAluno) {
        try{

            Connection con = fabricaConexoes.getConnection(); 
            PreparedStatement pstm = con.prepareStatement("DELETE FROM pi_aluno WHERE idAluno=?");
            
            pstm.close();
            con.close();
                
            }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }


}
