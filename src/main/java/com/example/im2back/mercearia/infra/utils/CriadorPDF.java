package com.example.im2back.mercearia.infra.utils;

import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class CriadorPDF {

    public void gerarPDF(List<ProdutosCompradosListDTO> listaProdutos, String nomeCliente, String nomeArquivo,Double total, String documento) {
        try {
            // Criar um novo documento PDF
            PDDocument document = new PDDocument();

            // Adicionar uma página ao documento
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Criar um objeto PDPageContentStream para escrever no conteúdo da página
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Adicionar informações do cliente
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 750); // Posição do texto na página
            contentStream.showText("Cliente: " + nomeCliente);
            contentStream.newLineAtOffset(0, -15); // Ajuste o valor para controlar a altura da nova linha
            contentStream.showText("Documento: " + documento);
            contentStream.endText();

            // Adicionar conteúdo ao PDF com base na lista de produtos
            float yPosition = 700; // Posição inicial do texto
            for (ProdutosCompradosListDTO produto : listaProdutos) {
                yPosition -= 50; // Ajuste a posição vertical para O PROXIMO OBJETO

                // Nome do produto
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText("Nome do Produto : " + produto.name());
                contentStream.endText();

                // Preço do produto
                yPosition -= 20;
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText("Preço : " + produto.price());
                contentStream.endText();

                // Data do produto
                yPosition -= 20;
                contentStream.beginText();
                contentStream.newLineAtOffset(50, yPosition);
                contentStream.showText("Data/Hora da compra  : " + produto.Data());
                contentStream.endText();         
            }
            // Data do produto
            yPosition -= 40;
            contentStream.beginText();
            contentStream.newLineAtOffset(50, yPosition);
            contentStream.showText("Total  : " + total );
            contentStream.endText();

            // Fechar o PDPageContentStream
            contentStream.close();

            // Salvar o documento PDF
            document.save(nomeArquivo);

            // Fechar o documento
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

