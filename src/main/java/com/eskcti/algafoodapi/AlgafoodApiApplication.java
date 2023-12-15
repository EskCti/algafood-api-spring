package com.eskcti.algafoodapi;

import com.eskcti.algafoodapi.core.io.Base64ProtocolResolver;
import com.eskcti.algafoodapi.infrastruct.repositories.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTF"));
		var app = new SpringApplication(AlgafoodApiApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
//		SpringApplication.run(AlgafoodApiApplication.class, args);
	}

	@GetMapping("/public")
	String publicRoute() {
		return "<h1>funcionando route public</h1>";
	}

	@GetMapping("/private")
	String privateRoute() {
		return "<h1>funcionando route private</h1>";
	}
}
