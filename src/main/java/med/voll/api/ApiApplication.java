package med.voll.api;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class ApiApplication {



	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);



	}

}
