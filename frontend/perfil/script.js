const id = new URLSearchParams(window.location.search).get("id");
const email = sessionStorage.getItem("usuarioEmail");
const senha = sessionStorage.getItem("usuarioSenha");

// se não tiver credenciais guardadas, não tem como continuar nesta tela
if (!email || !senha || !id) {
    window.location.href = "../login/index.html";
}

let usuarioAtual = null; // guarda os dados carregados para reaproveitar no editar

async function carregarPerfil() {

    const resposta = await fetch(`http://localhost:8080/usuarios/${id}/consultar`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, senha })
    });

    if (!resposta.ok) {
        // sessão inválida (senha errada, usuário não é o dono do perfil, etc)
        alert("Sessão expirada ou inválida. Faça login novamente.");
        logout();
        return;
    }

    const usuario = await resposta.json();
    usuarioAtual = usuario;

    document.getElementById("saudacao").textContent = `Bem vindo, ${usuario.nome}`;
    document.getElementById("nome").textContent = usuario.nome;
    document.getElementById("email").textContent = usuario.email;
    document.getElementById("telefone").textContent = usuario.telefone;
}

carregarPerfil();

document.querySelector(".btn-primary").addEventListener("click", async () => {

    const novoNome = prompt("Nome:", usuarioAtual.nome);
    if (novoNome === null) return; // usuário cancelou

    const novoTelefone = prompt("Telefone:", usuarioAtual.telefone);
    if (novoTelefone === null) return;

    const usuarioEditado = {
        nome: novoNome,
        email: usuarioAtual.email,
        senha: senha,
        telefone: novoTelefone
    };

    const resposta = await fetch(`http://localhost:8080/usuarios/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            usuario: usuarioEditado,
            email: email,
            senha: senha
        })
    });

    if (!resposta.ok) {
        alert("Não foi possível editar o perfil.");
        return;
    }

    alert("Perfil atualizado com sucesso!");
    carregarPerfil();
});

document.querySelector(".btn-danger").addEventListener("click", async () => {

    const confirmar = confirm("Tem certeza que deseja excluir sua conta? Essa ação não pode ser desfeita.");
    if (!confirmar) return;

    const resposta = await fetch(`http://localhost:8080/usuarios/${id}`, {
        method: "DELETE",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, senha })
    });

    if (!resposta.ok) {
        alert("Não foi possível excluir o perfil.");
        return;
    }

    alert("Perfil excluído com sucesso.");
    logout();
});

function logout() {
    sessionStorage.removeItem("usuarioEmail");
    sessionStorage.removeItem("usuarioSenha");
    window.location.href = "../login/index.html";
}