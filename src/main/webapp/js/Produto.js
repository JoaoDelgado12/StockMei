document.addEventListener("DOMContentLoaded", function(){
    // Dados simulação do Banco de Dados
const produtosDoEstoque = [
    { id: 101, nome: "Teclado Mecânico RGB", quantidadeAtual: 2, quantidadeMinima: 5, precoVenda: 349.90 },
    { id: 102, nome: "Mouse Gamer Sem Fio", quantidadeAtual: 7, quantidadeMinima: 5, precoVenda: 199.00 },
    { id: 103, nome: "Monitor 24' IPS 144Hz", quantidadeAtual: 18, quantidadeMinima: 4, precoVenda: 980.00 },
    { id: 104, nome: "Headset Pro USB", quantidadeAtual: 4, quantidadeMinima: 5, precoVenda: 279.90 },
    { id: 105, nome: "Mousepad Extra Grande", quantidadeAtual: 20, quantidadeMinima: 8, precoVenda: 89.90 }
];

const painelCards = document.getElementById('painelCards');

function gerarCards() {
    painelCards.innerHTML = '';

    produtosDoEstoque.forEach(prod => {
        const card = document.createElement('div');
        card.classList.add('card-produto');

        // 🎯 Aplicação da Lógica de Cores pelas Classes CSS
        if (prod.quantidadeAtual < prod.quantidadeMinima) {
            card.classList.add('card-critico');
        } else if (prod.quantidadeAtual <= prod.quantidadeMinima + 5) {
            card.classList.add('card-atencao');
        } else {
            card.classList.add('card-normal');
        }

        // Layout interno de cada Card
        card.innerHTML = `
            <div>
                <div class="card-titulo">${prod.nome}</div>
                <div class="card-info">Estoque Atual: <strong>${prod.quantidadeAtual} un</strong></div>
                <div class="card-info" style="font-size: 0.85rem; color: #94a3b8;">Mínimo requerido: ${prod.quantidadeMinima} un</div>
            </div>
            
            <div class="card-rodape">
                <div class="card-preco">R$ ${prod.precoVenda.toFixed(2)}</div>
                <a href="detalhes.html?id=${prod.id}" class="btn-link-seta">
                    Gerenciar &rarr;
                </a>
            </div>
        `;

        painelCards.appendChild(card);
    });
}

// Executa ao carregar
gerarCards();
})