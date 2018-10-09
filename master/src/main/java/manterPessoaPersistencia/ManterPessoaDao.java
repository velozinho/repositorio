package manterPessoaPersistencia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import manterPessoaEntidades.Pessoa;

public class ManterPessoaDao {

	EntityManager em;

	public ManterPessoaDao() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ManterPessoa");
		em = emf.createEntityManager();
	}

	public List<Pessoa> buscarPessoas() {
		StringBuilder str = new StringBuilder();
		str.append("FROM ").append(Pessoa.class.getName()).append(" p JOIN FETCH p.endereco");

		return em.createQuery(str.toString(), Pessoa.class).getResultList();
	}

	public void inserirPessoa(Pessoa p) {
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			System.out.println("pessoa inserida com sucesso");
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	public void deletarPessoa(Pessoa p) {
		try {
			em.getTransaction().begin();
			em.remove(p);
			em.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	public Pessoa buscarPessoa(String cpf) {
		return em.find(Pessoa.class, cpf);
	}

	public void editarPessoa(Pessoa p) {
	
		try {
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
	}catch(Exception e) {
		e.printStackTrace();
		em.getTransaction().rollback();
	}
		
	}

}
