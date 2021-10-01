package aluno.fam.pojo;

import java.util.Date;

public class Aluno {

	private int ra;
	private String nome;
	private String curso;
	private Date dataNsc;
	
	public Aluno() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Aluno(int ra, String nome, String curso, Date dataNsc) {
		super();
		this.ra = ra;
		this.nome = nome;
		this.curso = curso;
		this.dataNsc = dataNsc;
	}
	public int getRa() {
		return ra;
	}
	public void setRe(int ra) {
		this.ra = ra;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Date getDataNsc() {
		return dataNsc;
	}
	public void setDataNsc(Date dataNsc) {
		this.dataNsc = dataNsc;
	}
	@Override
	public String toString() {
		return "Funcionario [ra=" + ra + ", nome=" + nome + ", curso=" + curso + ", dataNsc=" + dataNsc + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ra;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (ra != other.ra)
			return false;
		return true;
	}
}
