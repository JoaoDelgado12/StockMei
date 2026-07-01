/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Master
 */
public class CadastroProdutoModel {

    // Atributos da seção principal (Fundo Branco)
    private String nome;
    private String marca;
    private String fornecedor;
    private String quantidadeEstoque;
    private String quantidadeMinima;
    private String precoVendaUnidade;
    private String localArmazenamento;
    private String precoCompraUnidade;
    private String quantidadeCompra;
    private String dtaCompra;
    private String precoCompraTotal;

    // Construtor padrão
    public CadastroProdutoModel() {
    }

    // Construtor completo
    public CadastroProdutoModel(String nome, String marca, String fornecedor, String quantidadeEstoque, String quantidadeMinima, 
    		String precoVendaUnidade, String localArmazenamento, String precoCompraUnidade, 
    		String quantidadeCompra, String dtaCompra, String precoCompraTotal) {
        this.nome = nome;
        this.marca = marca;
        this.fornecedor = fornecedor;
        this.quantidadeEstoque = quantidadeEstoque;
        this.quantidadeMinima = quantidadeMinima;
        this.precoVendaUnidade = precoVendaUnidade;
        this.localArmazenamento = localArmazenamento;
        this.precoCompraUnidade = precoCompraUnidade;
        this.quantidadeCompra = quantidadeCompra;
        this.dtaCompra = dtaCompra;
        this.precoCompraTotal = precoCompraTotal;
    }

    // ==========================================
    // GETTERS E SETTERS
    // ==========================================

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(String quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(String quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getPrecoVendaUnidade() {
        return precoVendaUnidade;
    }

    public void setPrecoVendaUnidade(String precoVendaUnidade) {
        this.precoVendaUnidade = precoVendaUnidade;
    }

    public String getLocalArmazenamento() {
        return localArmazenamento;
    }

    public void setLocalArmazenamento(String localArmazenamento) {
        this.localArmazenamento = localArmazenamento;
    }

    public String getPrecoCompraUnidade() {
        return precoCompraUnidade;
    }

    public void setPrecoCompraUnidade(String precoCompraUnidade) {
        this.precoCompraUnidade = precoCompraUnidade;
    }

    public String getQuantidadeCompra() {
        return quantidadeCompra;
    }

    public void setQuantidadeCompra(String quantidadeCompra) {
        this.quantidadeCompra = quantidadeCompra;
    }

    public String getDtaCompra() {
        return dtaCompra;
    }

    public void setDtaCompra(String dtaCompra) {
        this.dtaCompra = dtaCompra;
    }

	public String getPrecoCompraTotal() {
		return precoCompraTotal;
	}

	public void setPrecoCompraTotal(String precoCompraTotal) {
		this.precoCompraTotal = precoCompraTotal;
	}
}
