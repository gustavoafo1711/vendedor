package com.example.vendedor.rest.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.vendedor.domain.entity.Tipo;
import com.example.vendedor.domain.repository.Tipos;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("vendedor/tipo-vendedores")
@RequiredArgsConstructor
public class TipoVendedorController {

	private final Tipos tiposRepository;
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Tipo salvar (@Valid @RequestBody Tipo tipo) {
		return tiposRepository.save(tipo);
	}
	
	@GetMapping("{id}")
	public Tipo buscarId (@PathVariable Integer id) {
		return tiposRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Tipo de Vendedor não encontrado."));
	}
	
	@GetMapping
	public List<Tipo> buscarTodos(Tipo filtroTipo){
		ExampleMatcher exampleMatcher =ExampleMatcher.matching()
					.withIgnoreCase()
					.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Tipo> example = Example.of(filtroTipo, exampleMatcher);
		return tiposRepository.findAll(example);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @Valid @RequestBody Tipo tipo) {
		tiposRepository.findById(id).map(tipoExixtente -> {
			tipo.setId(tipoExixtente.getId());
			tiposRepository.save(tipo);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Tipo de Vendedor não encontrado."));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void deletar(@PathVariable Integer id, Tipo tipo) {
		tiposRepository.findById(id).map(tipoExistente -> {
			tiposRepository.delete(tipo);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Tipo de Vendedor não encontrado."));
		
	}
	
}
