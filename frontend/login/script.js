const formulario = document.querySelector("form");
const Iemail = document.querySelector("email");  
const Isenha = document.querySelector("senha");  


function logar() {

    fetch("http://localhost:8080/usuarios",
        {
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "GET",
            body: JSON.stringify ({
                email: Iemail.value,
                senha: Isenha.value,
            }),

        })
        .then(function(res){ console.log(res) })
        .catch(function(res){ console.log(res) }) 
};

function limpar (){
    Inome.value = "";
    Iemail.value = "";
    Isenha.value = "";
    Itelefone.value = "";
};

formulario.addEventListener("submit", function (event) {
    event.preventDefault(); // Impede o envio do formulário

   logar(); // Chama a função de cadastro
   limpar();
});