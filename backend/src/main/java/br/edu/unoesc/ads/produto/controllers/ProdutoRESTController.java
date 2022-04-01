package br.edu.unoesc.ads.produto.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.ads.produto.dto.ProdutoDTO;
import br.edu.unoesc.ads.produto.entities.Produto;
import br.edu.unoesc.ads.produto.repositories.ProdutoRepository;
import br.edu.unoesc.ads.produto.services.ProdutoService;

@RestController
@RequestMapping(value="api")
public class ProdutoRESTController {
	@Autowired
	private ProdutoService servico;
	
	@Autowired
	private ProdutoRepository repositorio;
	
	@GetMapping(value = "/produtos")
	public Page<ProdutoDTO> findAll(Pageable pageable) {
		// Lógica/regras de negócios
		return servico.findAll(pageable);
	}
	
	@GetMapping(value = "/produto/{id}")
	public ProdutoDTO findById(@PathVariable Long id) {
		return servico.findById(id);
	}
	
	// Incluir produto
	@PostMapping("/produto")
	public Produto salvarProduto(@RequestBody Produto produto) {
		// Não façam isso (repositório direto no controlador)
		return repositorio.save(produto);
	}
	
	// Alterar produto
	@PutMapping("/produto")
	public Produto atualizarProduto(@RequestBody Produto produto) {
		return repositorio.save(produto);
	}
	
	// Excluir produto
	@DeleteMapping("/produto")
	public void deletarProduto(@RequestBody Produto produto) {
		repositorio.delete(produto);
	}
	
	// Listar por quantidade
	@GetMapping(value={"/produtoporquantidade", "/produtoporquantidade/{quantidade}"})
	public List<Produto> listarPorQuantidade(@PathVariable Optional<BigDecimal> quantidade) {
		return repositorio.porQuantidade(quantidade.orElse(BigDecimal.ZERO));
	}
}
