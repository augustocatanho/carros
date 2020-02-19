package com.carros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;

@SpringBootTest
class CarrosApplicationTests {
	
	@Autowired
	private CarroService carroService;

	@Test
	void testeCrudCarro() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("Esportivo");
		
		CarroDTO carroDTO = carroService.insert(carro);
		
		assertNotNull(carroDTO);
		assertNotNull(carroDTO.getId());
		
		carro.setNome("Ferrari Maranelo");
		
		carroDTO = carroService.update(carro, carro.getId());
		
		assertEquals(carro.getNome(), carroDTO.getNome());
		
		Optional<CarroDTO> carroOp = carroService.getCarroById(carro.getId());
		
		assertTrue(carroOp.isPresent());
		assertEquals("Ferrari Maranelo", carroOp.get().getNome());
		assertEquals("Esportivo", carroOp.get().getTipo());
		
		carroService.deleteCarroById(carroOp.get().getId());
		
		assertFalse(carroService.getCarroById(carroOp.get().getId()).isPresent());
		
		
		
	}

}
