package manterPessoaBusiness;

import java.util.ArrayList;
import java.util.List;

import manterPessoaDTO.EnderecoDto;
import manterPessoaDTO.PessoaDto;
import manterPessoaEntidades.Endereco;
import manterPessoaEntidades.Pessoa;
import manterPessoaPersistencia.ManterPessoaDao;

public class ManterPessoaBusiness {

	ManterPessoaDao dao;
	
	public ManterPessoaBusiness() {
		dao = new ManterPessoaDao();
	}
	
	public List<PessoaDto> buscarPessoas() {
		List<Pessoa> pessoas = dao.buscarPessoas();
		List<PessoaDto> pessoasDto = new ArrayList<>();
		for(Pessoa p : pessoas) {
			PessoaDto pD = new PessoaDto();
			EnderecoDto endD = new EnderecoDto();
			pD.setNome(p.getNome());
			pD.setCpf(p.getCpf());
			pD.setEmail(p.getEmail());
			endD.setCep(p.getEndereco().getCep());
			endD.setBairro(p.getEndereco().getBairro());
			endD.setCidade(p.getEndereco().getCidade());
			endD.setComplemento(p.getEndereco().getComplemento());
			endD.setLogradouro(p.getEndereco().getLogradouro());
			endD.setNumero(p.getEndereco().getNumero());
			endD.setUf(p.getEndereco().getUf());
			pD.setEndereco(endD);
			pessoasDto.add(pD);	
		}
		
		return pessoasDto;
		
	}

	public void inserirPessoa(PessoaDto pDto) {
		
		Pessoa p = dtoParaPessoa(pDto);
		Endereco end = dtoParaEndereco(pDto);
		
		p.setEndereco(end);
		
		dao.inserirPessoa(p);
		
	}
	
	public void deletarPessoa(String cpf) {
		Pessoa p = dao.buscarPessoa(cpf);
		dao.deletarPessoa(p);
		
	}

	private Endereco dtoParaEndereco(PessoaDto pDto) {
		Endereco end = new Endereco();
		end.setCep(pDto.getEndereco().getCep());
		end.setCidade(pDto.getEndereco().getCidade());
		end.setBairro(pDto.getEndereco().getBairro());
		end.setComplemento(pDto.getEndereco().getComplemento());
		end.setLogradouro(pDto.getEndereco().getLogradouro());
		end.setNumero(pDto.getEndereco().getNumero());
		end.setUf(pDto.getEndereco().getUf());
		return end;
	}

	private Pessoa dtoParaPessoa(PessoaDto pDto) {
		Pessoa p = new Pessoa();
		p.setNome(pDto.getNome());
		p.setCpf(pDto.getCpf());
		p.setEmail(pDto.getEmail());
		return p;
	}

	public void editarPessoa(PessoaDto pDto) {
	
		Pessoa p = dtoParaPessoa(pDto);
		Endereco end = dtoParaEndereco(pDto);
		p.setEndereco(end);
		dao.editarPessoa(p);
		
	}
	
}
