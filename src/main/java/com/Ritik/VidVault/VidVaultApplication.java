package com.Ritik.VidVault;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VidVaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(VidVaultApplication.class, args);
		Dotenv dotenv = Dotenv.load();
		System.setProperty("GOOGLE_APPLICATION_CREDENTIALS", dotenv.get("GOOGLE_APPLICATION_CREDENTIALS"));

	}

}
