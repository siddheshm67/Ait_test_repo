package com.hsa.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;


@SpringBootApplication
@EnableAdminServer
public class HsaAdminServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HsaAdminServerApplication.class, args);
	}

}
