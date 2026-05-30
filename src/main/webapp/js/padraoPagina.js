document.addEventListener("DOMContentLoaded", function(){
    const botao = document.querySelector(".user-profile");
    const submenu = document.querySelector(".profile-off");

    botao.addEventListener("click", function(){
        submenu.classList.toggle("profile-on")
    });

    const btn_cadastro = document.querySelector(".header-cadastro");
    const submenu_cadastro = document.querySelector(".secao-cadastro-off");

    btn_cadastro.addEventListener("click", function(){
        submenu_cadastro.classList.toggle("secao-cadastro-on")
    });
});