// Elementos do DOM
const form = document.getElementById('formVenda');
const inputNome = document.getElementById('nome');
const inputPreco = document.getElementById('precoUnitario');
const inputQuantidade = document.getElementById('quantidade');
const inputDesconto = document.getElementById('desconto');

const lblTotalItem = document.getElementById('lblTotalItem');
const lblTotalGeral = document.getElementById('lblTotalGeral');

const btnAdicionar = document.getElementById('btnAdicionar');
const carrinhoLista = document.getElementById('carrinhoLista');
const carrinhoVazio = document.getElementById('carrinhoVazio');
const dadosCarrinhoHidden = document.getElementById('dadosCarrinhoHidden');

let carrinho = [];

// Função para calcular o total em tempo de digitação
function calcularTotalItem() {
    const preco = parseFloat(inputPreco.value) || 0;
    const qtd = parseInt(inputQuantidade.value) || 0;
    const desc = parseFloat(inputDesconto.value) || 0;

    let total = (preco * qtd) - desc;
    if (total < 0) total = 0; // Impede total negativo

    lblTotalItem.innerText = total.toFixed(2);
    return total;
}

// Ouvintes de evento para atualizar o cálculo em tempo real
[inputPreco, inputQuantidade, inputDesconto].forEach(input => {
    input.addEventListener('input', calcularTotalItem);
});

// Evento de Adicionar ao Carrinho
btnAdicionar.addEventListener('click', () => {
    // Validação simples
    if (!inputNome.value || !inputPreco.value || inputQuantidade.value < 1) {
        alert('Por favor, preencha corretamente os campos do produto antes de adicionar.');
        return;
    }

    const totalItem = calcularTotalItem();

    // Objeto do Item
    const novoItem = {
        id: Date.now(),
        nome: inputNome.value,
        quantidade: parseInt(inputQuantidade.value),
        precoUnitario: parseFloat(inputPreco.value),
        desconto: parseFloat(inputDesconto.value) || 0,
        total: totalItem
    };

    carrinho.push(novoItem);
    atualizarInterfaceCarrinho();
    limparCamposProduto();
});

// Função para atualizar a lista visual do carrinho
function atualizarInterfaceCarrinho() {
    // Remove o texto de carrinho vazio
    if(carrinho.length > 0) {
        carrinhoVazio.style.display = 'none';
    } else {
        carrinhoVazio.style.display = 'block';
    }

    // Renderiza os itens
    carrinhoLista.innerHTML = '';
    if (carrinho.length === 0) carrinhoLista.appendChild(carrinhoVazio);

    let totalGeral = 0;

    carrinho.forEach((item, index) => {
        totalGeral += item.total;

        const li = document.createElement('li');
        li.classList.add('carrinho-item');
        li.innerHTML = `
            <div class="item-info">
                <strong>${item.nome}</strong>
                <span>Qtd: ${item.quantidade} | Desc: R$ ${item.desconto.toFixed(2)}</span>
            </div>
            <div class="container-preco-remover">
                <div class="item-preco">R$ ${item.total.toFixed(2)}</div>
                <div class="btn-remover" title="Remover item">&times;</div>
            </div>
        `;

        const btnRemover = li.querySelector('.btn-remover');
        btnRemover.addEventListener('click', () => {
            // Remove 1 elemento do array 'carrinho' na posição do index atual
            carrinho.splice(index, 1); 
            
            // Aplica uma animação suave de saída antes de atualizar a lista (opcional)
            li.style.animation = 'surgir 0.2s reverse forwards';
            
            // Aguarda a animação terminar e atualiza o carrinho na tela
            setTimeout(() => {
                atualizarInterfaceCarrinho();
            }, 200);
        });
        
        carrinhoLista.appendChild(li);
    });

    // Atualiza o display de Total Geral
    lblTotalGeral.innerText = totalGeral.toFixed(2);

    // Alimenta o input oculto convertendo a lista em JSON para enviar no POST
    dadosCarrinhoHidden.value = JSON.stringify(carrinho);
}

function limparCamposProduto() {
    inputNome.value = '';
    inputPreco.value = '';
    inputQuantidade.value = '1';
    inputDesconto.value = '0.00';
    lblTotalItem.innerText = '0.00';
    inputNome.focus();
}

// Tratamento do Envio (POST)
form.addEventListener('submit', (e) => {
    if(carrinho.length === 0) {
        e.preventDefault(); // Bloqueia o envio se não houver itens
        alert('Seu carrinho está vazio! Adicione pelo menos um item para finalizar a compra.');
    }
});