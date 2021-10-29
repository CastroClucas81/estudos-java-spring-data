package br.com.springdata.spring.data.service;

import br.com.springdata.spring.data.orm.Funcionario;
import br.com.springdata.spring.data.orm.FuncionarioProjecao;
import br.com.springdata.spring.data.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Autowired
	FuncionarioRepository funcionarioRepository;

	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("QUAL ACAO VOCE QUER EXECUTAR");
			System.out.println("0 - Sair ");
			System.out.println("1 - BUSCA FUNCIONARIO POR NOME ");
			System.out.println("2 - BUSCA FUNCIONARIO NOME, DATA CONTRATACAO E SALARIO MAIOR");
			System.out.println("3 - BUSCA FUNCIONARIO DATA CONTRATACAO ");
			System.out.println("4 - PESQUISA FUNCIONARIO FUNCIONARIO SALARIO ");

			//selecionar o q o cliente vai selecionar
			int action = scanner.nextInt();

			switch (action) {
				case 1:
					buscaFuncionarioNome(scanner);
					break;
				case 2:
					buscaFuncionarioNomeSalarioMaiorData(scanner);
					break;
				case 3:
					buscaFuncionarioDataContratacao(scanner);
					break;
				case 4:
					pesquisaFuncionarioSalario();
					break;
				default:
					system = false;
					break;
			}
		}
	}

	private void buscaFuncionarioNome(Scanner scanner) {
		System.out.println("QUAL NOME DESEJA PESQUISAR?");
		String nome = scanner.next();
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(l -> System.out.println(l));
	}

	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("QUAL NOME DESEJA PESQUISAR?");
		String nome = scanner.next();

		System.out.println("QUAL DATA CONTRATACAO DESEJA PESQUISAR?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);

		System.out.println("QUAL SALARIO PESQUISAR?");
		Double salario = scanner.nextDouble();

		List<Funcionario> list = funcionarioRepository
				.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);

		list.forEach(l -> System.out.println(l));
	}

	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("QUAL DATA CONTRATACAO DESEJA PESQUISAR?");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);

		List<Funcionario> list =funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(l -> System.out.println(l));
	}

	private void pesquisaFuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: id:" + f.getId()
				+ "| nome: " + f.getNome()
				+ " | salario: " + f.getSalario()));
	}
}
