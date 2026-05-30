async function filtroEstoque(){
    try{
        const nome = document.getElementById("pesquisarNome").value.toLowerCase()
        const tipo = document.getElementById("tipoMovimentacao").value
        const data  = document.getElementById("filtroData").value
        
        const url = `http://localhost:8080/api/estoque?nome=${encodeURIComponent(nome)}&tipo=${encodeURIComponent(tipo)}&data=${encodeURIComponent(data)}`
        const response = await fetch(url)
        const dados = await response.json()
        
        const tabela = document.getElementById("tabelaEstoque")
        
        tabela.innerHTML = ""

        const filtrados = dados.filter(item => {
            const matchNome = nome === "" || item.nomeproduto.toLowerCase()
            const matchTipo = tipo === "" || item.status
            const matchData = data === "" || item.datafabricao
            
            return matchTipo && matchData && matchNome
        })

        filtrados.forEach(item => {
            const linha = `
            <tr>
                <td>${item.codigobarras}</td>
                <td>${item.nomeprodutoi}</td>
                <td>${item.fabricante}</td>
                <td>${item.marca}</td>
                <td>${item.quantidade}</td>
                <td>${parseFloat(item.total).toFixed(2)}</td>
                <td>${parseFloat(item.preco_venda).toFixed(2)}</td>
                <td>${parseFloat(item.preco_compra).toFixed(2)}</td>
            </tr>
            `
            tabela.innerHTML += linha;
        })

    }catch(erro){
        console.error("Deu erro no js filtro")
    }
}

document.getElementById("btn-pesquisar").addEventListener("click", filtroEstoque);