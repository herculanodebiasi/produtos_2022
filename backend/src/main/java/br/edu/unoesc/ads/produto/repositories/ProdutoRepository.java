package br.edu.unoesc.ads.produto.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.unoesc.ads.produto.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	@Query("select p from Produto p where p.quantidade >= :quantidade")
	List<Produto> porQuantidade(@Param("quantidade") BigDecimal quantidade);
}
