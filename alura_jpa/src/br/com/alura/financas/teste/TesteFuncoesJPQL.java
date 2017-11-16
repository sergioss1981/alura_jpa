package br.com.alura.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.alura.financas.enuns.TipoMovimentacaoEnum;
import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.util.JPAUtil;

public class TesteFuncoesJPQL {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new Conta();
		conta.setId(2);
		
		StringBuilder jpql = new StringBuilder();
		
		jpql.append("SELECT SUM(m.valor) FROM Movimentacao m WHERE m.conta.id = :conta ");
		jpql.append("AND m.tipo = :tipo ");
		
		Query query = em.createQuery(jpql.toString());
		query.setParameter("conta", conta.getId());
		query.setParameter("tipo", TipoMovimentacaoEnum.ENTRADA);

		BigDecimal soma = (BigDecimal) query.getSingleResult();
		System.out.println("A soma é: " + soma);

		
		/*
		 * Retornar a media 
		 */
		StringBuilder jpqlAvg = new StringBuilder();
		
		jpqlAvg.append("SELECT AVG(m.valor) FROM Movimentacao m WHERE m.conta.id = :conta ");
		jpqlAvg.append("AND m.tipo = :tipo ");
		
		Query queryAvg = em.createQuery(jpqlAvg.toString());
		queryAvg.setParameter("conta", conta.getId());
		queryAvg.setParameter("tipo", TipoMovimentacaoEnum.ENTRADA);

		Double media = (Double) queryAvg.getSingleResult();
		System.out.println("A média é: " + media);
		
		
		/*
		 * Retornar a media por dia
		 */
		
//		MovimentacaoDao dao = new MovimentacaoDao(em);
		
		TypedQuery<Double> typedQuery = em.createNamedQuery("getMediasPorDiaETtipo", Double.class);
		typedQuery.setParameter("conta", conta.getId());
		typedQuery.setParameter("tipo", TipoMovimentacaoEnum.SAIDA);
		
//		List<Double> mediaDias = dao.getMediasPorDiaETtipo(TipoMovimentacaoEnum.SAIDA, conta);
		List<Double> mediaDias = typedQuery.getResultList();
		
		
		int dia = 1;
		for (Double mediaDia : mediaDias) {
			System.out.println("A média do DIA " + dia + "º é igual a: "  + mediaDia);
			dia ++;
		}
		
		
		em.getTransaction().commit();
		em.close();

	}

}
