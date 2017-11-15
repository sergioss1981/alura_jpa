package br.com.alura.financas.teste;

import javax.persistence.EntityManager;

import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.util.JPAUtil;

public class TesteBuscaConta {
	
	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		em.getTransaction().begin();
		
		Conta conta = em.find(Conta.class, 2);
		conta.setTitular("Astrogildo de Tal");
		
		System.out.println("Titular: " + conta.getTitular());
		
		em.getTransaction().commit();
		
		em.close();
	}

}
