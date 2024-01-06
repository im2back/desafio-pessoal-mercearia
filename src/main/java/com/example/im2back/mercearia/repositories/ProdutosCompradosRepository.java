package com.example.im2back.mercearia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.im2back.mercearia.infra.utils.DadosGraficoDto;
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
	  
	  @Query("SELECT SUM(pc.preco) FROM ProdutosComprados pc WHERE (YEAR(pc.moment) = YEAR(CURRENT_DATE) AND MONTH(pc.moment) = MONTH(CURRENT_DATE) - 1) OR (YEAR(pc.moment) = YEAR(CURRENT_DATE) - 1 AND MONTH(pc.moment) = 12 AND MONTH(CURRENT_DATE) = 1)")
	  Double valorTotalMesAnterior();


	  
	  @Query("SELECT SUM(pc.preco) FROM ProdutosComprados pc WHERE pc.moment >= FUNCTION('DATE_FORMAT', CURRENT_DATE, '%Y-%m-01') AND pc.moment <= CURRENT_DATE")
	  Double valorVendidoDoInicioDoMesAtéAgora();
	  	  
	  



	    @Query("SELECT NEW com.example.im2back.mercearia.infra.utils.DadosGraficoDto(FUNCTION('DATE', p.moment), SUM(p.preco)) " +
	            "FROM ProdutosComprados p " +
	            "WHERE FUNCTION('DATE', p.moment) BETWEEN (CURRENT_DATE - 7) AND CURRENT_DATE " +
	            "GROUP BY FUNCTION('DATE', p.moment)" +
	    		"ORDER BY FUNCTION('DATE', p.moment) DESC")
	    List<DadosGraficoDto> obterSomaPrecoPorDataUltimos7Dias();
	
	  
	    
	    
	    
	    /*/
	  @Query("SELECT DATE(pc.moment) AS data, SUM(pc.preco) AS valor_total " +
		       "FROM ProdutosComprados pc " +
		       "WHERE pc.moment >= CURRENT_TIMESTAMP - INTERVAL 6 DAY AND pc.moment <= CURRENT_TIMESTAMP " +
		       "GROUP BY DATE(pc.moment)")
		List<Object[]> obterSomaPrecoPorDataUltimos7Dias();


		
		 SELECT DATE(moment) AS data, SUM(preco) AS valor_total
		FROM mercearia_api.produtos_comprados
		WHERE moment >= CURRENT_TIMESTAMP - INTERVAL 6 DAY AND moment <= CURRENT_TIMESTAMP
		GROUP BY DATE(moment);/*/


		
		
		

	
}
