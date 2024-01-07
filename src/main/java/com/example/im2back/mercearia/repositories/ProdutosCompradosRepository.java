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

	  @Query("SELECT SUM(p.preco) FROM ProdutosComprados p WHERE p.status = true")
	  Double valorTotal();

	  @Query("SELECT SUM(pc.preco) FROM ProdutosComprados pc WHERE DATE(pc.moment) = CURRENT_DATE AND pc.status = true")
	  Double valorTotalDoDia();
	  
	  @Query("SELECT SUM(pc.preco) FROM ProdutosComprados pc WHERE (YEAR(pc.moment) = YEAR(CURRENT_DATE) AND MONTH(pc.moment) = MONTH(CURRENT_DATE) - 1) OR (YEAR(pc.moment) = YEAR(CURRENT_DATE) - 1 AND MONTH(pc.moment) = 12 AND MONTH(CURRENT_DATE) = 1)")
	  Double valorTotalMesAnterior();


	  
	  @Query("SELECT SUM(pc.preco) FROM ProdutosComprados pc WHERE pc.moment >= FUNCTION('DATE_FORMAT', CURRENT_DATE, '%Y-%m-01') AND pc.status = true")
	  Double valorVendidoDoInicioDoMesAtéAgora();
	  	  
	  
	   @Query("SELECT NEW com.example.im2back.mercearia.infra.utils.DadosGraficoDto(FUNCTION('DATE', p.moment), SUM(p.preco)) " +
	            "FROM ProdutosComprados p " +
	            "WHERE FUNCTION('DATE', p.moment) BETWEEN (CURRENT_DATE - 7) AND CURRENT_DATE " +
	            "GROUP BY FUNCTION('DATE', p.moment)" +
	    		"ORDER BY FUNCTION('DATE', p.moment) DESC")
	  List<DadosGraficoDto> obterSomaPrecoPorDataUltimos7Dias();
	
	  List<ProdutosComprados> findByStatusTrue();
	    
	  			
	
}
