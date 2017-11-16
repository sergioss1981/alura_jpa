package br.com.alura.financas.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.alura.financas.enuns.TipoMovimentacaoEnum;
import br.com.alura.financas.modelo.Categoria;
import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.modelo.Movimentacao;
import br.com.alura.financas.util.JPAUtil;

public class PopulaMovimentacoesComCategoria {

	public static void main(String[] args) {
				
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		Categoria categoria1recuperada = em.find(Categoria.class, 1); 
		Categoria categoria2recuperada = em.find(Categoria.class, 2);
		
		Conta conta = new Conta();
		conta.setId(2);
		
		Movimentacao movimentacao1 = new Movimentacao();
		movimentacao1.setData(Calendar.getInstance());
		movimentacao1.setDescricao("Viagem à SP");
		movimentacao1.setTipo(TipoMovimentacaoEnum.SAIDA);
		movimentacao1.setValor(new BigDecimal("2000.0"));
		movimentacao1.setCategorias(Arrays.asList(categoria1recuperada, categoria2recuperada));
		movimentacao1.setConta(conta);
		
		Movimentacao movimentacao2 = new Movimentacao();

		Calendar amanha = Calendar.getInstance();
		amanha.add(Calendar.DAY_OF_MONTH, 1);
		
		movimentacao2.setData(amanha);
		
		movimentacao2.setDescricao("Viagem ao RJ");
		movimentacao2.setTipo(TipoMovimentacaoEnum.SAIDA);
		movimentacao2.setValor(new BigDecimal("400.0"));
		movimentacao2.setCategorias(Arrays.asList(categoria1recuperada, categoria2recuperada));
		movimentacao2.setConta(conta);
		
		em.persist(movimentacao1);
		em.persist(movimentacao2);

		em.getTransaction().commit();
		em.close();
		
	}
	
}