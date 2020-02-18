package com.carros.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
	
	@GetMapping()
	public String get() {
		return "Olá Mundo";
	}
	
	@GetMapping("/signIn/{login}/{senha}")
	public String loginGet(@PathVariable String login, @PathVariable String senha) {
		return "Login: " + login + "\n" + "Senha: " + senha;
	}
	
	@PostMapping("/singIn/{login}/{senha}")
	public String loginPost(@PathVariable String login, @PathVariable String senha) {
		return "Login: " + login + "\n" + "Senha: " + senha;
	}

}
