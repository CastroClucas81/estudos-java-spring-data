package br.com.springdata.spring.data.service;

import br.com.springdata.spring.data.orm.Funcionario;
import br.com.springdata.spring.data.repository.FuncionarioRepository;
import br.com.springdata.spring.data.specification.SpecificationFuncionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {
	@Autowired
	FuncionarioRepository funcionarioRepository;

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public void inicial(Scanner scanner) {
		System.out.println("DIGITE O NOME");
		String nome = scanner.next();

		if (nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}

		System.out.println("DIGITE O CPF");
		String cpf = scanner.next();

		if (cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}

		System.out.println("DIGITE O SALARIO");
		Double salario = scanner.nextDouble();

		if (salario == 0) {
			salario = null;
		}

		System.out.println("DIGITE A DATA DE CONTRATACAO");
		String data = scanner.next();
		LocalDate dataContratacao;

		if (data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(data, formatter);
		}

		List<Funcionario> funcionarios = funcionarioRepository
				.findAll(Specification.where(SpecificationFuncionario.nome(nome))
						.or(Specification.where(SpecificationFuncionario.cpf(cpf))
						.or(Specification.where(SpecificationFuncionario.salario(salario))
						.or(Specification.where(SpecificationFuncionario.dataContratacao(dataContratacao))
				))));

		funcionarios.forEach(System.out::println);
	}
}
