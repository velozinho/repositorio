package manterPessoaDTO;

public class PessoaDto {
	
	private String cpf;
	private String nome;
	private String email;
	private EnderecoDto endereco;
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public EnderecoDto getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "PessoaDto [cpf=" + cpf + ", nome=" + nome + ", email=" + email + ", endereco=" + endereco + "]";
	}


}
