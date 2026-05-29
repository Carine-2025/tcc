package br.com.criandoapi.projeto_tcc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);
	}

}

//erro no teste do localhost (final da aula 5. Obs.: inserir dados no banco para realizar o teste.
//Erro: os atributos da classe Usuario estavam divergentes da tabela no banco de dados. Foi deletado a coluna "username" e alterado o nome da coluna "nome_completo" para "nome".
