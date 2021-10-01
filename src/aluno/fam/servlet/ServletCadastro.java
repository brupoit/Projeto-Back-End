package aluno.fam.servlet;



import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aluno.fam.dao.DaoAluno;
import aluno.fam.pojo.Aluno;

/**
 * Servlet implementation class ServletCadastro
 */
@WebServlet("/cadastrarAluno")
public class ServletCadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCadastro() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// parametros
		String raStr = request.getParameter("ra");
		String nome = request.getParameter("nome");
		String curso = request.getParameter("curso");
		String dataNscStr = request.getParameter("dataNsc");
		
		
		// converte os valores de String 
		int ra = Integer.parseInt(raStr);
		Date dataNsc = null;
		try {
			dataNsc = new SimpleDateFormat("dd/MM/yyyy").parse(dataNscStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Aluno aluno = new Aluno(ra, nome, curso, dataNsc);

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Registration result</title>");
		out.println("</head>");
		out.println("<body>");
		
		DaoAluno dao = new DaoAluno();
		if ( dao.inserirAluno(aluno)) {
			out.println("success");	
		} else {
			out.println("unsuccessfully");
		}
		out.println("</body>");
		out.println("</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
