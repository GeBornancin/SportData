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
import ifpr.pgua.eic.sportdata.model.results.Result;


public class JDBCEmprestimoDAO implements EmprestimoDAO{

    private static final String INSERT = "INSERT INTO pi_emprestimo(dataEmprestimo, idAluno, idMaterial) VALUES (?,?,?,?)";
    // private static final String INSERT_

}