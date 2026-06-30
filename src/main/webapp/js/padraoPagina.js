document.addEventListener("DOMContentLoaded", function(){
    const botao = document.querySelector(".user-profile");
    const submenu = document.querySelector(".profile-off");

    botao.addEventListener("click", function(){
        if(submenu.classList.value == "profile-on"){
            submenu.classList.replace("profile-on", "profile-off")
        }else{
        submenu.classList.replace("profile-off", "profile-on")
        }
    });

    const btn_cadastro = document.querySelector(".header-cadastro");
    const submenu_cadastro = document.querySelector(".secao-cadastro-off");

    btn_cadastro.addEventListener("click", function(){
        if(submenu_cadastro.classList.value == "secao-cadastro-on"){
            submenu_cadastro.classList.replace("secao-cadastro-on", "secao-cadastro-off")
        }else{
        submenu_cadastro.classList.replace("secao-cadastro-off", "secao-cadastro-on")
        }
    });

    document.addEventListener('click', (e) =>{
        if(!botao.contains(e.target) && !submenu.contains(e.target)){
            submenu.classList.replace("profile-on", "profile-off")
        }

        if(!btn_cadastro.contains(e.target) && !submenu_cadastro.contains(e.target)){
            submenu_cadastro.classList.replace("secao-cadastro-on","secao-cadastro-off")
        }
    } )
});