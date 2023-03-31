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

import com.example.vendedor.domain.entity.Loja;
import com.example.vendedor.domain.repository.Lojas;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("vendedor/lojas")
@RequiredArgsConstructor
public class LojaController {
	private final Lojas lojasRepository;
	
	@PostMapping
	@ResponseStatus(CREATED)
	public Loja salvar(@Valid @RequestBody Loja loja) {
		return lojasRepository.save(loja);
	}
	
	@GetMapping("{id}")
	public Loja buscarId(@PathVariable Integer id) {
		return lojasRepository.findById(id)
				.orElseThrow(() -> 
					new ResponseStatusException(NOT_FOUND, "Não foi encontrado nenhuma loja no sistema."));
	}
	
	@GetMapping
	@ResponseStatus(NO_CONTENT)
	public List<Loja> buscarTodos(Loja filtroLoja){
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
		Example<Loja> example = Example.of(filtroLoja, matcher);
		return lojasRepository.findAll(example);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		lojasRepository.findById(id).map(lojaExistens -> {
			lojasRepository.delete(lojaExistens);
			return Void.TYPE;
		}).orElseThrow(() -> 
		new ResponseStatusException(NOT_FOUND, "Não foi encontrado nenhuma loja no sistema."));
		
	}
	
	@PutMapping("{id}")
	@ResponseStatus(NO_CONTENT)
	public void atualizar(@PathVariable Integer id, @RequestBody @Valid Loja loja) {
		lojasRepository.findById(id).map(lojaExistente -> {
			loja.setId(lojaExistente.getId());
			loja.setDataCadastro(lojaExistente.getDataCadastro());
			lojasRepository.save(loja);
			return Void.TYPE;
		}).orElseThrow(() -> 
		new ResponseStatusException(NOT_FOUND, "Não foi encontrado nenhuma loja no sistema."));
	}

}
