document.addEventListener("DOMContentLoaded", function(){
    const botao = document.querySelector(".user-profile");
    const submenu = document.querySelector(".profile-off");

    botao.addEventListener("click", function(){
        submenu.classList.toggle("profile-on")
    });
});