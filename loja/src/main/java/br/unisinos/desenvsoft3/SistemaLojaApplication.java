package br.unisinos.desenvsoft3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class SistemaLojaApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SistemaLojaApplication.class, args);
    }
}