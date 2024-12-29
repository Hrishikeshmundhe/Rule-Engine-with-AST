package com.myproject.rule_engine_ast.controller;

import com.myproject.rule_engine_ast.service.RuleEngineService;
import com.myproject.rule_engine_ast.dto.RuleDTO;
import com.myproject.rule_engine_ast.dto.EvaluationDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.myproject.rule_engine_ast.model.Rule;
import com.myproject.rule_engine_ast.model.ASTNode;

@RestController
@RequestMapping("/api/rules")
public class RuleEngineController {

    @Autowired
    private RuleEngineService ruleEngineService;

    @PostMapping("/create")
    public ResponseEntity<?> createRule(@RequestBody RuleDTO ruleDto) {
        // Call createAndSaveRule with rule name and AST structure from RuleDTO
        Rule rule = ruleEngineService.createAndSaveRule(ruleDto.getRuleName(), ruleDto.getAstNode());
        return ResponseEntity.ok("Rule created successfully with ID: " + rule.getId());
    }
  
    @PostMapping("/evaluate/{ruleId}")
    public ResponseEntity<?> evaluateRule(@PathVariable Long ruleId, @RequestBody EvaluationDTO evaluationDto) {
        // Retrieve rule by ID and evaluate using the test data provided in EvaluationDTO
        boolean result = ruleEngineService.evaluateRule(ruleId, evaluationDto.getTestData());
        return ResponseEntity.ok("Evaluation result: " + result);
    }

    // Health check endpoint
    @GetMapping("/")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("API is running!");
    }
}
