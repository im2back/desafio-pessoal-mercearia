package com.example.im2back.mercearia.infra.utils;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;



public class DadosGraficoDto {

    private Date dataBruta;
    private Double valor;
    
    @Getter @Setter
    private String data;
   
    public DadosGraficoDto() {
    }

    
    public DadosGraficoDto(Date data, Double valor) {
        this.dataBruta = data;
        this.valor = valor;
    }
    
    public DadosGraficoDto(String dataString, Double valor) {
        this.data = dataString;
        this.valor = valor;
    }

    
    public Date getDataBruta() {
        return dataBruta;
    }

    public void setDataBruta(Date data) {
        this.dataBruta = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}

	
	


