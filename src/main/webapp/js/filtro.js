async function filtroEstoque(){
    try{
        const nome = document.getElementById("pesquisarNome").value.toLowerCase()
        const marca = document.getElementById("filtroMarca").value
        const data  = document.getElementById("filtroData").value
        
        const url = `http://localhost:8080/api/estoque/filtro?nome=${encodeURIComponent(nome)}&tipo=${encodeURIComponent(marca)}&data=${encodeURIComponent(data)}`
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
                <td>${item.nome}</td>
                <td>${item.quantidade}</td>
                <td>${item.marca}</td>
                <td>${item.preco}</td>
                <td>${parseFloat(item.desconto).toFixed(2)}</td>
                <td>${parseFloat(item.total).toFixed(2)}</td>
            </tr>
            `
            tabela.innerHTML += linha;
        })

    }catch(erro){
        console.error("Deu erro no js filtro")
    }
}

document.getElementById("btn-pesquisar").addEventListener("click", filtroEstoque);