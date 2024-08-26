package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

// TODO

// Cambiar todos los int a Integer (parametros, tipos, etc) DONE
// Terminar de crear los getters, setters DONE
// Terminar de implementar los mappers DONE
// Una vez implementados los mappers, actualizar servicios para que usen el mapper en la conversion DTO/Entity DONE
// Insertar valores en la base de datos DONE
// Probar haciendo peticiones con Postman DONE
// Desarrollar el resto de los controladores con los distintos metodos HTTP
// Crear el repo en github