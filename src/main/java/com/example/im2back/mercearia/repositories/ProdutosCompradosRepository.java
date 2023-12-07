package com.example.im2back.mercearia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.im2back.mercearia.model.carrinho.ProdutosComprados;

import jakarta.transaction.Transactional;

public interface ProdutosCompradosRepository extends JpaRepository<ProdutosComprados, Long> {
	
	 @Transactional
	 @Modifying
	 @Query("DELETE FROM ProdutosComprados pc WHERE pc.client.id = :id")
	 void deleteByClient_id(Long id);

	  @Query("SELECT SUM(p.preco) FROM ProdutosComprados p")
	    Double valorTotal();

	  @Query("SELECT SUM(pc.preco) FROM ProdutosComprados pc WHERE DATE(pc.moment) = CURRENT_DATE")
	  Double valorTotalDoDia();
	  
	  @Query("SELECT SUM(pc.preco) FROM ProdutosComprados pc WHERE YEAR(pc.moment) = YEAR(CURRENT_DATE) AND MONTH(pc.moment) = MONTH(CURRENT_DATE) - 1")
	  Double valorTotalMesAnterior();
	  
	  @Query("SELECT SUM(pc.preco) FROM ProdutosComprados pc WHERE pc.moment >= FUNCTION('DATE_FORMAT', CURRENT_DATE, '%Y-%m-01') AND pc.moment <= CURRENT_DATE")
	  Double valorVendidoDoInicioDoMesAtéAgora();
	  	  
	  @Query("SELECT DATE(pc.moment) AS data, SUM(pc.preco) AS valor_total " +
		       "FROM ProdutosComprados pc " +
		       "WHERE pc.moment >= CURRENT_DATE +1 " +
		       "GROUP BY DATE(pc.moment) " +
		       "ORDER BY DATE(pc.moment) DESC " +
		       "LIMIT 7")
		List<Object[]> obterSomaPrecoPorDataUltimos7Dias();
		
		
		

	
}
