package br.com.springdata.spring.data.service;

import br.com.springdata.spring.data.orm.UnidadeTrabalho;
import br.com.springdata.spring.data.repository.UnidadeTrabalhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {
	private Boolean system = true;

	@Autowired
	UnidadeTrabalhoRepository unidadeTrabalhoRepository;

	//o cliente vai selecionar a funcao por este método
	public void inicial(Scanner scanner) {
		while (system) {
			System.out.println("QUAL ACAO VOCE QUER EXECUTAR");
			System.out.println("0 - Sair ");
			System.out.println("1 - SALVAR ");
			System.out.println("2 - ATUALIZAR ");
			System.out.println("3 - VISUALIZAR ");
			System.out.println("4 - DELETAR ");

			//selecionar o q o cliente vai selecionar
			int action = scanner.nextInt();

			switch (action) {
				case 1:
					salvar(scanner);
					break;
				case 2:
					atualizar(scanner);
					break;
				case 3:
					visualizar();
					break;
				case 4:
					deletar(scanner);
					break;
				default:
					system = false;
					break;
			}
		}
	}

	private void salvar(Scanner scanner) {
		System.out.println("Digite o nome da unidade");
		String nome = scanner.next();

		System.out.println("Digite o endereco");
		String endereco = scanner.next();

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setDescricao(nome);
		unidadeTrabalho.setEndereco(endereco);

		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Salvo");
	}

	private void atualizar(Scanner scanner) {
		System.out.println("Digite o id");
		Integer id = scanner.nextInt();

		System.out.println("Digite o nome da unidade");
		String nome = scanner.next();

		System.out.println("Digite o endereco");
		String endereco = scanner.next();

		UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho();
		unidadeTrabalho.setId(id);
		unidadeTrabalho.setDescricao(nome);
		unidadeTrabalho.setEndereco(endereco);

		unidadeTrabalhoRepository.save(unidadeTrabalho);
		System.out.println("Alterado");
	}

	private void visualizar() {
		Iterable<UnidadeTrabalho> unidades = unidadeTrabalhoRepository.findAll();
		unidades.forEach(unidade -> System.out.println(unidade));
	}

	private void deletar(Scanner scanner) {
		System.out.println("Id");
		int id = scanner.nextInt();
		unidadeTrabalhoRepository.deleteById(id);
		System.out.println("Deletado");
	}
}
