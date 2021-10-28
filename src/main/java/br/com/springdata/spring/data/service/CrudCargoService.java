package br.com.springdata.spring.data.service;

import br.com.springdata.spring.data.orm.Cargo;
import br.com.springdata.spring.data.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

	@Autowired
	CargoRepository cargoRepository;

	//o cliente vai selecionar a funcao por este método
	public void inicial(Scanner scanner) {
		salvar(scanner);
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
}
