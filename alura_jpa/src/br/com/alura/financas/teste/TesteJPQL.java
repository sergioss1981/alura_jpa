package br.com.alura.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.alura.financas.enuns.TipoMovimentacaoEnum;
import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.modelo.Movimentacao;
import br.com.alura.financas.util.JPAUtil;

public class TesteJPQL {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		StringBuilder jpql = new StringBuilder();
		
		jpql.append("SELECT m FROM Movimentacao m WHERE m.conta.id = :conta ");
		jpql.append("AND m.tipo = :tipo ");
		jpql.append("ORDER BY m.valor DESC ");
		
		Query query = em.createQuery(jpql.toString());
		query.setParameter("conta", conta.getId());
		query.setParameter("tipo", TipoMovimentacaoEnum.ENTRADA);
		
		List<Movimentacao> resultados = query.getResultList();
		
		for (Movimentacao movimentacao : resultados) {
			System.out.println("Descricao: " + movimentacao.getDescricao());
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
		}

		em.getTransaction().commit();
		em.close();

	}

}
