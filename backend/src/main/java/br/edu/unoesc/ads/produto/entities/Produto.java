package br.edu.unoesc.ads.produto.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.tomcat.util.codec.binary.Base64;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private BigDecimal quantidade;
	private BigDecimal valor;
	
	@Column(name = "foto")
	private byte[] foto;

	public Produto() { }

	public Produto(Long id, String nome, BigDecimal quantidade, BigDecimal valor) {
		this.id = id;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valor = valor;
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public BigDecimal getQuantidade() { return quantidade; }
	public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }

	public BigDecimal getValor() { return valor; }
	public void setValor(BigDecimal valor) { this.valor = valor; }

	public String getFotoBase64() { return Base64.encodeBase64String(this.getFoto()); }
	public byte[] getFoto() { return foto; }
	public void setFoto(byte[] foto) { this.foto = foto; }
}
