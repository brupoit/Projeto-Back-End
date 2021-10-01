package aluno.fam.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import aluno.fam.pojo.Aluno;

public class DaoAluno {

	private Connection connection;
	
	private Connection getConnection() throws SQLException {
		return connection = DriverManager.getConnection(
				"jdbc:hsqldb:hsql://localhost/",
		        "SA", "");
	}
	
	public List<Aluno> getLista() {
		List<Aluno> result = new ArrayList<>();
		Statement stmt;
		try {
			stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(
					"SELECT ra, nome, curso, dataNsc FROM alunos");
			while (rs.next()) {
				int ra = rs.getInt("ra");
				String nome = rs.getString(2);
				String curso = rs.getString(3);
				Date dataNsc = rs.getDate(4);
				result.add(new Aluno(ra, nome, curso, new java.util.Date(dataNsc.getTime())));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Aluno buscarPeloRa( int ra ) {
		Aluno result = null;
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement("SELECT ra, nome, curso, dataNsc FROM alunos WHERE ra = ?");
			pstmt.setInt( 1, ra );
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String nome = rs.getString(2);
				String curso = rs.getString(3);
				Date dataNsc = rs.getDate(4);
				result = new Aluno (ra, nome, curso, new java.util.Date(dataNsc.getTime()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean excluirAluno( Aluno aluno ) {
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement("DELETE FROM alunos WHERE ra = ?");
			pstmt.setInt( 1, aluno.getRa() );
			return pstmt.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean inserirAluno( Aluno aluno) {
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement("INSERT INTO alunos VALUES(?,?,?,?)");
			pstmt.setInt( 1, aluno.getRa() );
			pstmt.setString(2, aluno.getNome());
			pstmt.setString(3, aluno.getCurso());
			pstmt.setDate(4, new Date(aluno.getDataNsc().getTime()));
			return pstmt.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public boolean alterarAluno( Aluno antigo, Aluno novo ) {
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement("UPDATE alunos set RA = ?, nome = ?, curso = ?, dataNsc = ? where "
					+ "ra=? and nome=? and curso=? and dataNsc=?");
			pstmt.setInt( 1, antigo.getRa() );
			pstmt.setString(2, antigo.getNome());
			pstmt.setString(3, antigo.getCurso());
			pstmt.setDate(4, new Date(antigo.getDataNsc().getTime()));
			pstmt.setInt( 5, novo.getRa() );
			pstmt.setString(6, novo.getNome());
			pstmt.setString(7, novo.getCurso());
			pstmt.setDate(8, new Date(novo.getDataNsc().getTime()));
			
			return pstmt.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
}
