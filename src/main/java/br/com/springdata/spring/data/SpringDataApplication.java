package br.com.springdata.spring.data;

import br.com.springdata.spring.data.orm.Cargo;
import br.com.springdata.spring.data.repository.CargoRepository;
import br.com.springdata.spring.data.service.*;
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
	@Autowired
	CrudFuncionarioService crudFuncionarioService;
	@Autowired
	CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;
	@Autowired
	RelatoriosService relatoriosService;
	@Autowired
	RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

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
			System.out.println("2 - Funcionario ");
			System.out.println("3 - Unidade Trabalho ");
			System.out.println("4 - Relatorios ");
			System.out.println("5 - Relatorio dinamico ");

			int action = scanner.nextInt();

			switch (action) {
				case 1:
					crudCargoService.inicial(scanner);
					break;
				case 2:
					crudFuncionarioService.inicial(scanner);
					break;
				case 3:
					crudUnidadeTrabalhoService.inicial(scanner);
					break;
				case 4:
					relatoriosService.inicial(scanner);
					break;
				case 5:
					relatorioFuncionarioDinamico.inicial(scanner);
				default:
					system = false;
					break;
			}
		}
	}
}
