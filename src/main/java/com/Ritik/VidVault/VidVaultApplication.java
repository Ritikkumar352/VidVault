package com.Ritik.VidVault;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VidVaultApplication {

	public static void main(String[] args) {
//		Dotenv dotenv = Dotenv.load(); // Load .env *before* Spring Boot starts
//		System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", dotenv.get("GOOGLE_APPLICATION_CREDENTIALS"));
//		System.out.println("GCP Credentials Path: " + System.getenv("GOOGLE_APPLICATION_CREDENTIALS"));
		SpringApplication.run(VidVaultApplication.class, args); // Spring Boot starts
	}

}
