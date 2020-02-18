package com.carros.domain.dto;

import org.modelmapper.ModelMapper;

import com.carros.domain.Carro;

import lombok.Data;

@Data
public class CarroDTO {

	private Long id;
	private String nome;
	private String tipo;
	
	public static CarroDTO create(Carro c) {
		ModelMapper model = new ModelMapper();
		return model.map(c, CarroDTO.class);
	}
}
