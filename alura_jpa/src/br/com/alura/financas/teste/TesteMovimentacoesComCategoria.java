package br.com.alura.financas.teste;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.alura.financas.enuns.TipoMovimentacaoEnum;
import br.com.alura.financas.modelo.Categoria;
import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.modelo.Movimentacao;
import br.com.alura.financas.util.JPAUtil;

public class TesteMovimentacoesComCategoria {

	public static void main(String[] args) {
				
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
//		Categoria categoria1 = new Categoria("Viagem");
//		Categoria categoria2 = new Categoria("Negocios");
		Categoria categoria1recuperada = em.find(Categoria.class, 1); 
		Categoria categoria2recuperada = em.find(Categoria.class, 2);
		
		Conta conta = new Conta();
		conta.setId(2);
		
//		Movimentacao movimentacao1 = new Movimentacao();
//		movimentacao1.setData(Calendar.getInstance());
//		movimentacao1.setDescricao("Viagem à SP");
//		movimentacao1.setTipo(TipoMovimentacaoEnum.SAIDA);
//		movimentacao1.setValor(new BigDecimal("1000.0"));
//		movimentacao1.setCategorias(Arrays.asList(categoria1, categoria2));
//		movimentacao1.setConta(conta);
//		
//		Movimentacao movimentacao2 = new Movimentacao();
//		movimentacao2.setData(Calendar.getInstance());
//		movimentacao2.setDescricao("Viagem ao RJ");
//		movimentacao2.setTipo(TipoMovimentacaoEnum.SAIDA);
//		movimentacao2.setValor(new BigDecimal("300.0"));
//		movimentacao2.setCategorias(Arrays.asList(categoria1, categoria2));
//		movimentacao2.setConta(conta);
		
		Movimentacao movimentacao3 = new Movimentacao();
		movimentacao3.setData(Calendar.getInstance());
		movimentacao3.setDescricao("Estorno da Viagem ao RJ");
		movimentacao3.setTipo(TipoMovimentacaoEnum.ENTRADA);
		movimentacao3.setValor(new BigDecimal("300.0"));
		movimentacao3.setCategorias(Arrays.asList(categoria1recuperada, categoria2recuperada));
		movimentacao3.setConta(conta);
		
		Movimentacao movimentacao4 = new Movimentacao();
		movimentacao4.setData(Calendar.getInstance());
		movimentacao4.setDescricao("Estorno da Viagem à SP");
		movimentacao4.setTipo(TipoMovimentacaoEnum.ENTRADA);
		movimentacao4.setValor(new BigDecimal("1000.0"));
		movimentacao4.setCategorias(Arrays.asList(categoria1recuperada, categoria2recuperada));
		movimentacao4.setConta(conta);
		
		

		
//		em.persist(categoria1);
//		em.persist(categoria2);
//		
//		em.persist(movimentacao1);
//		em.persist(movimentacao2);
		em.persist(movimentacao3);
		em.persist(movimentacao4);

		em.getTransaction().commit();
		em.close();
		
		
	}
	
}
