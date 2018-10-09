package manterPessoaServlets;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import manterPessoaBusiness.ManterPessoaBusiness;
import manterPessoaDTO.PessoaDto;

/**
 * Servlet implementation class GetPessoaServlet
 */
@WebServlet("/Pessoas/*")
public class PessoaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PessoaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ManterPessoaBusiness buss = new ManterPessoaBusiness();
		List<PessoaDto> pessoasDto = buss.buscarPessoas();
		
		
		Gson gson = new Gson();
	
		String json1 = gson.toJson(pessoasDto);
		
		resp.setContentType("application/json");
		resp.getWriter().append(json1);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ManterPessoaBusiness buss = new ManterPessoaBusiness();
		
		String json = req.getReader()
				.lines().collect(Collectors.joining());
		
		Gson gson = new Gson();
		PessoaDto pDto = gson.fromJson(json, PessoaDto.class);
		
		buss.inserirPessoa(pDto);
		System.out.println(pDto);
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ManterPessoaBusiness buss = new ManterPessoaBusiness();
		
		String json = req.getReader()
				.lines().collect(Collectors.joining());
		
		Gson gson = new Gson();
		
		String cpf = req.getParameter("cpf");
		
		buss.deletarPessoa(cpf);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ManterPessoaBusiness buss = new ManterPessoaBusiness();
		
		String json = req.getReader()
				.lines().collect(Collectors.joining());
		
		Gson gson = new Gson();
		PessoaDto pDto = gson.fromJson(json, PessoaDto.class);
		
		buss.editarPessoa(pDto);
		
	}

}
