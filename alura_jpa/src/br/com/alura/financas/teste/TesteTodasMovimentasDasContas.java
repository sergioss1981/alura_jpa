package br.com.alura.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.util.JPAUtil;

public class TesteTodasMovimentasDasContas {

    public static void main(String[] args) {
        EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT DISTINCT c FROM Conta c LEFT JOIN FETCH c.movimentacoes ");
        
        Query query = em.createQuery(sb.toString());
        
        List<Conta> todasAsContas = query.getResultList();
        
        for (Conta conta : todasAsContas) {
        	System.out.println("Titular: " + conta.getTitular());
        	System.out.println("Movimentacoes: ");
        	System.out.println(conta.getMovimentacoes());
		}
        
		em.getTransaction().commit();
		em.close();
        
    }
}
