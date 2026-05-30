async function carregarEstoque(){
    try {
        const response = await fetch("http://localhost:8080/api/estoque");
        const dados = await response.json();
        const tabela = document.getElementById("corpoTabela");
        tabela.innerHTML= "";

        dados.forEach(item => {
            const linha =`
          <tr>
              <td>${item.codigobarras}</td>
              <td>${item.nome}</td>
              <td>${item.fabricante}</td>
              <td>${item.marca}</td>
              <td>${item.datadefabricacao}</td>
              <td>${item.dataVencimento}</td>
              <td>${item.quantidade}</td>
              <td>${item.valor}</td>
              <td>${item.total}</td>
              <td>${item.status}</td>
          </tr>    
          `;
            tabela.innerHTML += linha;
    })
    }catch(erro){
        console.log("Erro ao carregar os produtos", erro);
    }
}


async function carregarResumo(){
    try{
        const response = await fetch("http://localhost:8080/api/estoque/resumo");
        const dados = await response.json();

        document.getElementById("cardEntrada").innerHTML = dados.entradaVal;
        document.getElementById("cardSaida").innerHTML = dados.saidaVal;
        document.getElementById("cardTotal").innerHTML = dados.totalVal;
    }catch(erro){
        console.log("Erro da consulta dos dados", erro);

    }
}

document.addEventListener("DOMContentLoaded", function(){
    const pagina = document.getElementById("pagina");
    pagina.addEventListener("input", function(){
        this.value = this.value.replace(/\D/g,"");
        
    });
});

window.onload = () => {
    carregarEstoque();
    carregarResumo();
}