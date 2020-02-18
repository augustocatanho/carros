package com.carros.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.carros.domain.dto.CarroDTO;

@Service
public class CarroService {

	@Autowired
	private CarroRepository rep;

	public List<CarroDTO> getCarros() {
		return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
	}
	
	public Optional<CarroDTO> getCarroById(Long id){
		return rep.findById(id).map(CarroDTO::create);
	}
	
	public List<CarroDTO> getCarrosByTipo(String tipo) {
		return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}
	
	public Carro save(Carro carro) {
		return rep.save(carro);
	}

	public Carro update(Carro carro, Long id) {
		Assert.notNull(id,"Não foi possível atualizar o registro.");
		
		Optional<Carro> optional = rep.findById(id);
		if(optional.isPresent()) {
			Carro carroDB = optional.get();
			carroDB.setNome(carro.getNome());
			carroDB.setTipo(carro.getTipo());
			
			rep.save(carroDB);
			return carroDB;
		} else {
			throw new RuntimeException("Não foi possível atualizar o registro.");
		}
	}
	
	public void deleteCarroById(Long id){
		if(getCarroById(id).isPresent()) {
			rep.deleteById(id);
		}
	}
	
}
