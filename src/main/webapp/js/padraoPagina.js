document.addEventListener("DOMContentLoaded", function(){
    const botao = document.querySelector(".user-profile");
    const submenu = document.querySelector(".profile-off");

    botao.addEventListener("click", function(){
        if(submenu.classList.contains("profile-on")){
            submenu.classList.replace("profile-on", "profile-off")
        }else{
        submenu.classList.replace("profile-off", "profile-on")
        }
    });

    const btn_cadastro = document.querySelector(".header-mais");
    const submenu_cadastro = document.querySelector(".secao-mais-off");

    btn_cadastro.addEventListener("click", function(){
        if(submenu_cadastro.classList.contains("secao-mais-on")){
            submenu_cadastro.classList.replace("secao-mais-on", "secao-mais-off")
        }else{
        submenu_cadastro.classList.replace("secao-mais-off", "secao-mais-on")
        }
    });

    document.addEventListener('click', (e) =>{
        if(!botao.contains(e.target) && !submenu.contains(e.target)){
            submenu.classList.replace("profile-on", "profile-off")
        }

        if(!btn_cadastro.contains(e.target) && !submenu_cadastro.contains(e.target)){
            submenu_cadastro.classList.replace("secao-mais-on","secao-mais-off")
        }
    } )
});