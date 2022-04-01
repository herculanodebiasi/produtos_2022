package br.edu.unoesc.ads.produto.dto;

import java.math.BigDecimal;
import br.edu.unoesc.ads.produto.entities.Produto;

public class ProdutoDTO {
	private Long id;
	private String nome;
	private BigDecimal quantidade;
	private BigDecimal valor;
	
	public ProdutoDTO() { }

	public ProdutoDTO(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.quantidade = produto.getQuantidade();
		this.valor = produto.getValor();
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public BigDecimal getQuantidade() { return quantidade; }
	public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }

	public BigDecimal getValor() { return valor; }
	public void setValor(BigDecimal valor) { this.valor = valor; }
}
