package br.edu.unoesc.ads.produto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.unoesc.ads.produto.dto.ProdutoDTO;
import br.edu.unoesc.ads.produto.entities.Produto;
import br.edu.unoesc.ads.produto.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository repositorio;
	
	@Transactional(readOnly = true)
	public Page<ProdutoDTO> findAll(Pageable pageable) {
		Page<Produto> resultado = repositorio.findAll(pageable);
		Page<ProdutoDTO> pagina = resultado.map(produto -> new ProdutoDTO(produto));
		
		return pagina;
	}
	
	@Transactional(readOnly = true)
	public ProdutoDTO findById(Long id) {
		Produto resultado = repositorio.findById(id).get();
		
		return new ProdutoDTO(resultado);
	}
}
