package br.com.jeferson.literalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class LiteraluraApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LiteraluraApplication.class, args);

		Menu menu = context.getBean(Menu.class);
		menu.exibirMenu();
	}
}
