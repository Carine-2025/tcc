async function carregarPerfil() {
    const parametros = new URLSearchParams(window.location.search);
    const id = parametros.get("id");

    const resposta = await fetch(
        `http://localhost:8080/usuarios/${id}`
    )

    const usuario = await resposta.json();

document.getElementById("saudacao").textContent = 
`Bem vindo, ${usuario.nome}`;

document.getElementById("nome").textContent = 
usuario.nome;

document.getElementById("email").textContent = 
usuario.email;

document.getElementById("telefone").textContent = 
usuario.telefone;

}

carregarPerfil();

function logout() {
    localStorage.removeItem("usuario");
    window.location.href = "../login/index.html";
}