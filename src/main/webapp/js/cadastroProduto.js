document.addEventListener('DOMContentLoaded', async function(){
    const url = `http://localhost:8080/api/consulta/marca`

    const resp = await  fetch(url);
    const dadosMarca = await resp.json();

    const inputMarca = document.getElementById("marca");
    const janelaAuto = document.getElementById("janelaAuto");

    inputMarca.addEventListener('input', function(){
        if(this.value.toLowerCase().length() === 0) return;
        this.innerHTML = '';

        const tresFiltrados = dadosMarca.filter( item => item.toLowerCase().includes(this.value.toLowerCase())).slice(0,3);

        tresFiltrados.forEach(marcas => {
                const li = document.createElement("li");
                li.classList.add('topico-item');
                li.innerHTML = marcas;

                li.addEventListener('click', function() {
                    inputMarca.value = marcas;
                    janelaAuto.innerHTML = '';
                });

                janelaAuto.appendChild(li);

        });
    })
    
    document.addEventListener('click', (e) => {
        if(e.target !== inputMarca){
            janelaAuto.innerHTML = '';
        }
    })
})


const bxestoque = document.querySelector(".containerEstoque");
const btnestoque = document.getElementById("addEstoque");

btnestoque.addEventListener('click', function(){
    bxestoque.classList.toggle("containerEstoqueON");
} );





