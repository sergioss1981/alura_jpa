package br.com.alura.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.alura.financas.enuns.TipoMovimentacaoEnum;
import br.com.alura.financas.modelo.Categoria;
import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.modelo.Movimentacao;
import br.com.alura.financas.util.JPAUtil;

public class TesteMovimentacoesPorCategoria {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Categoria categoria = new Categoria();
		categoria.setId(1);
		
		StringBuilder jpql = new StringBuilder();
		
		jpql.append(" SELECT m FROM Movimentacao m JOIN m.categoria c ");
		jpql.append(" WHERE c = :categoria");
		jpql.append(" ORDER BY m.valor DESC ");
		
		Query query = em.createQuery(jpql.toString());
		query.setParameter("categoria", categoria);
		
		List<Movimentacao> resultados = query.getResultList();
		
		for (Movimentacao movimentacao : resultados) {
			System.out.println("Descricao: " + movimentacao.getDescricao());
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
		}

		em.getTransaction().commit();
		em.close();

	}

}
