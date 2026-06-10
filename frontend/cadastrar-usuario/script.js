const formulario = document.querySelector("form");
const Inome = document.querySelector(".nome");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");
const Itelefone = document.querySelector(".telefone");

function cadastrar() {

    fetch("http://localhost:8080/usuarios",
        {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            method: "POST",
            body: JSON.stringify({
                nome: Inome.value,
                email: Iemail.value,
                senha: Isenha.value,
                telefone: Itelefone.value
            }),

        })
        .then(function (res) {
            console.log(res);

            if (res.ok) {
                alert("Cadastro realizado com sucesso!");
                limpar();
            } else {
                alert("Erro ao cadastrar usuário.");
            }
        })
        .catch(function (erro) {
            console.log(erro);

            alert("Erro ao conectar com o servidor.");
        })


};

function limpar() {
    Inome.value = "";
    Iemail.value = "";
    Isenha.value = "";
    Itelefone.value = "";
};

formulario.addEventListener("submit", function (event) {
    event.preventDefault(); // Impede o envio do formulário

    cadastrar(); // Chama a função de cadastro
});

