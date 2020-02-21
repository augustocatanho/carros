package com.carros.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.carros.domain.Carro;
import com.carros.domain.CarroService;
import com.carros.domain.dto.CarroDTO;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

	@Autowired
	private CarroService service;

	@GetMapping
	public ResponseEntity<List<CarroDTO>> get() {
		return ResponseEntity.ok(service.getCarros());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CarroDTO> get(@PathVariable Long id) {
		CarroDTO carro = service.getCarroById(id);

		return ResponseEntity.ok(carro);
	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<CarroDTO>> getCarrosByTipo(@PathVariable String tipo) {
		List<CarroDTO> carros = service.getCarrosByTipo(tipo);

		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
	}

	@PostMapping
	public ResponseEntity<?> post(@RequestBody Carro carro) {
		CarroDTO carroDTO = service.insert(carro);
		return ResponseEntity.created(getURI(carroDTO.getId())).build();
	}

	private URI getURI(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity<CarroDTO> put(@PathVariable Long id, @RequestBody Carro carro) {

		carro.setId(id);
		CarroDTO c = service.update(carro, id);

		return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		service.deleteCarroById(id);

		return ResponseEntity.ok().build();
	}

}
