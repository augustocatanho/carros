package com.carros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;
import com.carros.domain.exception.ObjectNotFoundException;

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
		
		carroDTO = carroService.getCarroById(carro.getId());
		
		assertNotNull(carroDTO);
		assertEquals("Ferrari Maranelo", carroDTO.getNome());
		assertEquals("Esportivo", carroDTO.getTipo());
		
		carroService.deleteCarroById(carroDTO.getId());
		
		try {
			carroService.getCarroById(carroDTO.getId());
			fail("O carro não foi excluído");
		} catch (ObjectNotFoundException e) {
			//ok;
		}
		
	}

}
