
package com.myproject.rule_engine_ast.service;

import java.util.Map;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myproject.rule_engine_ast.model.Rule; // Import for Rule
import com.myproject.rule_engine_ast.model.ASTNode; // Import for ASTNode
import com.myproject.rule_engine_ast.repository.RuleRepository; // Import for RuleRepository



@Service // This makes it a service component in Spring
public class RuleEngineService {

    @Autowired
    private RuleRepository ruleRepository;

    // Method to create and save a rule (AST) from a string
    public Rule createAndSaveRule(String ruleName, ASTNode ast) {
        Rule rule = new Rule(ruleName); // Create Rule entity with rule name
        rule.setAstNodes(List.of(ast)); // Attach ASTNode (representing the rule conditions)
        return ruleRepository.save(rule); // Save rule in the database
    }

    // Combine multiple rules using the specified operator
    public ASTNode combineRules(List<ASTNode> rules, String operator) {
        if (rules == null || rules.isEmpty()) {
            return null; // Return null if the rules list is empty or null
        }
        ASTNode combinedNode = rules.get(0); // Start with the first rule

        // Loop through the remaining rules and combine them based on the operator
        for (int i = 1; i < rules.size(); i++) {
            combinedNode = new ASTNode(operator, combinedNode, rules.get(i));
        }
        return combinedNode; // Return the root node that combines all rules
    }

    // Method to evaluate a rule from the database by its ID
    public boolean evaluateRule(Long ruleId, Map<String, Object> testData) {
        Rule rule = getRuleById(ruleId);
        if (rule == null) {
            return false; // Rule not found
        }
        ASTNode rootNode = rule.getAstNodes().get(0); // Assuming the root node of the rule
        return evaluateRule(rootNode, testData); // Use the existing evaluateRule logic
    }

    // Retrieve Rule by ID from the database
    public Rule getRuleById(Long id) {
        return ruleRepository.findById(id).orElse(null);
    }

    // Evaluate a rule against the provided test data (for ASTNode evaluation)
    private boolean evaluateRule(ASTNode node, Map<String, Object> testData) {
        if (node == null) {
            return false; // Node is null, cannot evaluate
        }

        String nodeType = node.getType();
        String nodeValue = node.getValue();

        // Check if the node type is an operand
        if ("operand".equals(nodeType)) {
            if (nodeValue == null || nodeValue.isEmpty()) {
                return false; // Invalid operand
            }
            return evaluateCondition(node, testData); // Use evaluateCondition for operands
        }

        // Check if the node type is an operator
        if ("operator".equals(nodeType)) {
            // Ensure both left and right nodes are present
            ASTNode leftNode = node.getLeft();
            ASTNode rightNode = node.getRight();

            if (leftNode != null && rightNode != null) {
                boolean leftResult = evaluateRule(leftNode, testData); // Evaluate left node
                boolean rightResult = evaluateRule(rightNode, testData); // Evaluate right node

                // Apply the operator logic safely
                if ("AND".equals(nodeValue)) {
                    return leftResult && rightResult;
                } else if ("OR".equals(nodeValue)) {
                    return leftResult || rightResult;
                }
            }
        }

        // If node is neither operand nor operator or if evaluation fails
        return false;
    }

    // Evaluate individual conditions
    private boolean evaluateCondition(ASTNode condition, Map<String, Object> data) {
        if (condition.getType().equals("operand")) {
            String operand = condition.getValue();
            String[] parts = operand.split(" ");
            String attribute = parts[0];
            String operator = parts[1];
            String value = parts[2].replace("'", "");

            switch (attribute) {
                case "age":
                    int age = (int) data.get(attribute);
                    int conditionAge = Integer.parseInt(value);
                    return evaluateNumericCondition(age, operator, conditionAge);
                case "department":
                    String department = (String) data.get(attribute);
                    return evaluateStringCondition(department, operator, value);
                case "salary":
                    int salary = (int) data.get(attribute);
                    int conditionSalary = Integer.parseInt(value);
                    return evaluateNumericCondition(salary, operator, conditionSalary);
                case "experience":
                    int experience = (int) data.get(attribute);
                    int conditionExperience = Integer.parseInt(value);
                    return evaluateNumericCondition(experience, operator, conditionExperience);
                default:
                    return false; // Unknown attribute
            }
        }
        return false;
    }

    private boolean evaluateNumericCondition(int attributeValue, String operator, int conditionValue) {
        switch (operator) {
            case ">":
                return attributeValue > conditionValue;
            case "<":
                return attributeValue < conditionValue;
            case "=":
                return attributeValue == conditionValue;
            default:
                return false; // Unknown operator
        }
    }

    private boolean evaluateStringCondition(String attributeValue, String operator, String conditionValue) {
        if ("=".equals(operator)) {
            return attributeValue.equals(conditionValue);
        }
        return false; // Unknown operator or unsupported string comparison
    }
}
