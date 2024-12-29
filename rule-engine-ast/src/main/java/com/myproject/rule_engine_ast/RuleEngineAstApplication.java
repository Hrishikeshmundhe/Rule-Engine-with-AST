package com.myproject.rule_engine_ast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.myproject.rule_engine_ast.model")
public class RuleEngineAstApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuleEngineAstApplication.class, args);
	}

}
