package com.myproject.rule_engine_ast.dto;

import com.myproject.rule_engine_ast.model.ASTNode;

public class RuleDTO {
    private String ruleId;
    private String ruleName;
    private String description;
    private String createdDate;
    private String modifiedDate;
    private ASTNode astNode; // Add this line

    // Getters and Setters for existing fields
    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    // Getters and Setters for ASTNode
    public ASTNode getAstNode() {
        return astNode;
    }

    public void setAstNode(ASTNode astNode) {
        this.astNode = astNode;
    }
}
