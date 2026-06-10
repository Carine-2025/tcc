
const formulario = document.getElementById("form-login");

formulario.addEventListener("submit", async function(event) {

    event.preventDefault();

   const email = document.getElementById("email").value;
   const senha = document.getElementById("senha").value;

   const resposta = await fetch(
    `http://localhost:8080/usuarios/login?email=${email}&senha=${senha}`

   );

   if (!resposta.ok) {
    alert ("Email ou senha inválidos");
   }

   const usuario = await resposta.json();

   window.location.href = `../perfil/index.html?id=${usuario.id}`;

});


