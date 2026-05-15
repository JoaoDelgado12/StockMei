# Stock MEI 📦

O **Stock MEI** é uma plataforma desenvolvida para simplificar a vida de comerciantes que atuam como vendedores na Shopee. O projeto nasceu da necessidade de substituir a interface nativa da Shopee — muitas vezes carregada de informações excessivas — por um ambiente mais limpo, amigável e focado na produtividade do microempreendedor.

## 🚀 Diferenciais do Projeto
* **Interface Clean:** Foco no que é essencial para o vendedor, removendo ruídos visuais.
* **Sincronização:** Atualização automática da página do vendedor para manter os dados sempre frescos.
* **Ambiente Amigável:** Experiência de utilizador (UX) pensada para quem precisa de agilidade no dia a dia.

## 🛠️ Tecnologias Utilizadas

Este projeto foi construído com foco em fundamentos e infraestrutura moderna:

* **Java (Sem Frameworks):** O back-end foi desenvolvido em Java puro, sem o uso de frameworks (como Spring), para garantir um entendimento profundo da linguagem e da lógica de negócio.
* **Docker:** Utilização de containers para isolar a aplicação e o banco de dados, facilitando a portabilidade e o entendimento do ciclo de vida de um container.
* **MySQL:** Banco de dados relacional utilizado para o armazenamento seguro de informações de stock e utilizadores.

## 📦 Estrutura de Infraestrutura (Docker)

O projeto utiliza Docker para orquestrar os serviços:
1.  **Backend (Java):** Processamento de dados e lógica de integração.
2.  **Database (MySQL):** Persistência de dados.

## ⚙️ Como Executar

1. **Clonar o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/stock-mei.git](https://github.com/seu-usuario/stock-mei.git)
   cd stock-mei
