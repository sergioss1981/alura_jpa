package br.com.alura.financas.modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.alura.financas.enuns.TipoMovimentacaoEnum;

@Entity
@NamedQuery(query="SELECT DISTINCT AVG(m.valor) FROM Movimentacao m WHERE m.conta.id = :conta "
					+ " AND m.tipo = :tipo GROUP BY m.data ", name="getMediasPorDiaETtipo")
public class Movimentacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private TipoMovimentacaoEnum tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;
	
	private String descricao;
	
	@ManyToOne
	private Conta conta;
	
	@ManyToMany
	private List<Categoria> categoria;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoMovimentacaoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacaoEnum tipo) {
		this.tipo = tipo;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategorias(List<Categoria> categoria) {
		this.categoria = categoria;
	}

}
