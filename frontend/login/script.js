const formulario = document.getElementById("form-login");

formulario.addEventListener("submit", async function (event) {

    event.preventDefault();

    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;

    const resposta = await fetch(
        `http://localhost:8080/usuarios/login?email=${email}&senha=${senha}`
    );

    if (!resposta.ok) {
        alert("Email ou senha inválidos");
        return;
    }

    const usuario = await resposta.json();

    // guarda email e senha para usar nas próximas telas (perfil)
    // já que a API exige credenciais em toda ação autenticada
    sessionStorage.setItem("usuarioEmail", email);
    sessionStorage.setItem("usuarioSenha", senha);

    window.location.href = `../perfil/index.html?id=${usuario.id}`;
});