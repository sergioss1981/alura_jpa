package br.com.alura.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.alura.financas.enuns.TipoMovimentacaoEnum;
import br.com.alura.financas.modelo.Conta;

public class MovimentacaoDao {
	
	private EntityManager em;
	
	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}

	public List<Double> getMediasPorDiaETtipo(TipoMovimentacaoEnum saida, Conta conta) {

		StringBuilder jpqlAvgDia = new StringBuilder();
		
		jpqlAvgDia.append("SELECT DISTINCT AVG(m.valor) FROM Movimentacao m WHERE m.conta.id = :conta ");
		jpqlAvgDia.append("AND m.tipo = :tipo ");
		jpqlAvgDia.append("GROUP BY m.data ");
		
		TypedQuery<Double> queryAvgDia = em.createQuery(jpqlAvgDia.toString(), Double.class);
		queryAvgDia.setParameter("conta", conta.getId());
		queryAvgDia.setParameter("tipo", TipoMovimentacaoEnum.ENTRADA);

		List<Double> mediaDias = queryAvgDia.getResultList();

		return mediaDias;
	}

}
