package com.myproject.rule_engine_ast.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.CascadeType;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "RULE")
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruleName;

    @OneToMany(mappedBy = "rule", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ASTNode> astNodes;

    public Rule() {}

    public Rule(String ruleName) {
        this.ruleName = ruleName;
    }

    // New constructor to accept rule name and AST nodes
    public Rule(String ruleName, List<ASTNode> astNodes) {
        this.ruleName = ruleName;
        this.astNodes = astNodes;
    }

    public Long getId() {
        return id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public List<ASTNode> getAstNodes() {
        return astNodes;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public void setAstNodes(List<ASTNode> astNodes) {
        this.astNodes = astNodes;
    }
}
