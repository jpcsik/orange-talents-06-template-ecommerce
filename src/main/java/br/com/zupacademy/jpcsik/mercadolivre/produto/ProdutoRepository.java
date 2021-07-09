package br.com.zupacademy.jpcsik.mercadolivre.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	@Query(value="select count(*) from Opiniao where produto_id = ?1", nativeQuery = true)
	Integer totalDeNotasPorProduto(Long idProduto);
	
	@Query(value="select cast(avg(nota) as decimal(10,1)) from Opiniao where produto_id = ?1", nativeQuery = true)
	Double mediaDeNotasPorProduto(Long idProduto);
	
}
