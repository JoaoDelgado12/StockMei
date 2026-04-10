/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Master
 */
public record ProdutoModel(
 String codigoBarras,
 String nomeProduto,
 String fabricante,
 String marca,
 String dataFabricacao,
 String dataVencimento,
 String quantidade,
 String valor,
 String total, int id) {
    public ProdutoModel(
 String codigoBarras,
 String nomeProduto,
 String fabricante,
 String marca,
 String dataFabricacao,
 String dataVencimento,
 String quantidade,
 String valor,
 String total){
        this(codigoBarras, nomeProduto,
 fabricante,
 marca,
 dataFabricacao,
 dataVencimento,
 quantidade,
 valor,
 total, -1);
    }
}
