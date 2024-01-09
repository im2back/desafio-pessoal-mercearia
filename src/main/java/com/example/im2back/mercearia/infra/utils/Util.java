package com.example.im2back.mercearia.infra.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.im2back.mercearia.model.carrinho.ProdutosComprados;

public class Util {
    private static final SimpleDateFormat FORMATO_BRASILEIRO = new SimpleDateFormat("dd/MM/yyyy");
    private static final Double VALOR_PADRAO = 0.0;
    
    public static String formatarDataBrasileira(Date data) {
        return FORMATO_BRASILEIRO.format(data);
    }
    
        
    public static Double verificarValorNulo(Double valor) {
        return (valor == null) ? VALOR_PADRAO : valor;
    }

	public static List<ProdutosComprados> triarCarrinho(List<ProdutosComprados> listaDeProdutos){	
	    return listaDeProdutos.stream()
	            .filter(p -> p.isStatus())
	            .collect(Collectors.toList());		
}
   
}

