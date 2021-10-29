package br.com.springdata.spring.data.service;

import br.com.springdata.spring.data.orm.Cargo;
import br.com.springdata.spring.data.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

	private Boolean system = true;

	@Autowired
	CargoRepository cargoRepository;

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
		System.out.println("DESCRICAO DO CARGO");
		//next é pra pegar o que o cliente digitou
		String descricao = scanner.next();
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("SALVO COM SUCESSO!");
	}

	private void atualizar(Scanner scanner) {
		System.out.println("ID DO REGISTRO");
		int id = scanner.nextInt();
		System.out.println("DESCRICAO DO CARGO");
		String descricao = scanner.next();

		Cargo cargo	= new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("ATUALIZADO COM SUCESSO!");

	}

	private void visualizar() {
		Iterable<Cargo> cargos = cargoRepository.findAll();
		cargos.forEach(c -> System.out.println(c));
		System.out.println("CONFIRA A LISTA ACIMA!");
	}

	private void deletar(Scanner scanner) {
		System.out.println("ID DO REGISTRO");
		int id = scanner.nextInt();
		cargoRepository.deleteById(id);
		System.out.println("DELETADO COM SUCESSO!");
	}
}
