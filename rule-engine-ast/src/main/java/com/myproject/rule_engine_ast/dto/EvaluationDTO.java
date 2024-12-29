package com.myproject.rule_engine_ast.dto;

import java.util.Map;
import com.myproject.rule_engine_ast.model.ASTNode; // Ensure ASTNode is imported


public class EvaluationDTO {
    private String userId;
    private boolean isEligible;
    private ASTNode astNode;  // Field for ASTNode to evaluate
    private Map<String, Object> testData;  // Test data for evaluation

    // Constructors
    public EvaluationDTO() {
    }

    public EvaluationDTO(String userId, boolean isEligible, ASTNode astNode, Map<String, Object> testData) {
        this.userId = userId;
        this.isEligible = isEligible;
        this.astNode = astNode;
        this.testData = testData;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isEligible() {
        return isEligible;
    }

    public void setEligible(boolean eligible) {
        isEligible = eligible;
    }

    public ASTNode getAstNode() {
        return astNode;
    }

    public void setAstNode(ASTNode astNode) {
        this.astNode = astNode;
    }

    public Map<String, Object> getTestData() {
        return testData;
    }

    public void setTestData(Map<String, Object> testData) {
        this.testData = testData;
    }
}
