package com.example.learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy
public class EasyNotesApplication {

	public static void main(String[] args) {
		SayHello hello = (name)->{return "Hello, "+name;};
		System.out.println(hello.sayHelloTo("Arindam Mondal"));
		SpringApplication.run(EasyNotesApplication.class, args); 
	}
}
