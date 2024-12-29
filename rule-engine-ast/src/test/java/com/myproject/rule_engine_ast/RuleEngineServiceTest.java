package com.myproject.rule_engine_ast;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional; // Import for Optional

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.myproject.rule_engine_ast.model.ASTNode;
import com.myproject.rule_engine_ast.model.Rule; // Import for Rule
import com.myproject.rule_engine_ast.repository.RuleRepository;
import com.myproject.rule_engine_ast.service.RuleEngineService;

public class RuleEngineServiceTest {

    @Mock
    private RuleRepository ruleRepository; // Mock the RuleRepository

    @InjectMocks
    private RuleEngineService ruleEngineService; // Inject mocks into RuleEngineService

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    public void testEvaluateRule() {
        // Create a rule represented as an ASTNode
        ASTNode ageRule = new ASTNode("operand", "age > 18");
        
        // Create a map to represent user data
        Map<String, Object> user = new HashMap<>();
        user.put("age", 20); // Example user age

        // Mock the RuleRepository behavior to return a Rule when queried by ID
        // Assuming you have a constructor in the Rule class that takes the rule name and ASTNodes
        when(ruleRepository.findById(1L)).thenReturn(Optional.of(new Rule("Test Rule", List.of(ageRule))));
        
        // Evaluate the rule against the user data
        boolean result = ruleEngineService.evaluateRule(1L, user);

        assertTrue(result); // Expect true since 20 > 18
    }
}
