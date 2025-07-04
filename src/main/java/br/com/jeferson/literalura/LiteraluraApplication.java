package br.com.jeferson.literalura;

import br.com.jeferson.literalura.client.GutendexApiClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		GutendexApiClient apiClient = new GutendexApiClient();
		apiClient.obterDadosApi();
	}
}
