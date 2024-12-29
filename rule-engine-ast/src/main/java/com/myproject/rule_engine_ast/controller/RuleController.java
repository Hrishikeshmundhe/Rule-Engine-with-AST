package com.myproject.rule_engine_ast.controller;

import com.myproject.rule_engine_ast.model.Rule;
import com.myproject.rule_engine_ast.service.RuleService; // Ensure this service is created and available
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @PostMapping("/rules")
    public ResponseEntity<Rule> createRule(@RequestBody Rule rule) {
        Rule createdRule = ruleService.saveRule(rule);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRule);
    }

    @GetMapping("/rules")
    public ResponseEntity<List<Rule>> getAllRules() {
        List<Rule> rules = ruleService.getAllRules();
        return ResponseEntity.ok(rules);
    }
}
