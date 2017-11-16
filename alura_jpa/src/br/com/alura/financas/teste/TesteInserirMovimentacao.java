package br.com.alura.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.financas.modelo.Conta;
import br.com.alura.financas.modelo.Movimentacao;
import br.com.alura.financas.util.JPAUtil;

public class TesteInserirMovimentacao {
	
	public static void main(String[] args) {

        EntityManager em = new JPAUtil().getEntityManager();

        Conta conta = em.find(Conta.class, 1);

        List<Movimentacao> movimentacoes = conta.getMovimentacoes();


        for (Movimentacao movimentacao : movimentacoes) {
            System.out.println("Movimentação: " + movimentacao.getDescricao());
        }

        em.close();
    }

}
