package br.com.springdata.spring.data;

import br.com.springdata.spring.data.orm.Cargo;
import br.com.springdata.spring.data.repository.CargoRepository;
import br.com.springdata.spring.data.service.CrudCargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	private Boolean system = true;

	@Autowired
	CrudCargoService crudCargoService;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//pegar valores do console
		Scanner scanner = new Scanner(System.in);

		while (system) {
			System.out.println("QUAL ACAO VOCE QUER EXECUTAR");
			System.out.println("0 - Sair ");
			System.out.println("1 - Cargo ");

			int action = scanner.nextInt();
			if (action == 1) {
				crudCargoService.inicial(scanner);
			} else {
				system = false;
			}
		}
	}
}
