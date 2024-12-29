package com.myproject.rule_engine_ast.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.myproject.rule_engine_ast.model.Rule;

@Entity
@Table(name = "ASTNODE")
public class ASTNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    @Column(name = "node_value") // Changed column name to avoid conflict
    private String value;

    @ManyToOne
    @JoinColumn(name = "rule_id")
    @JsonBackReference
    private Rule rule;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "left_id")
    @JsonManagedReference
    private ASTNode left;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "right_id")
    @JsonBackReference
    private ASTNode right;

    // Default constructor
    public ASTNode() {}

    // Constructor for a leaf node with type and value
    public ASTNode(String type, String value) {
        this.type = type;
        this.value = value;
    }

    // Constructor for an internal node with type, left, and right children
    public ASTNode(String type, ASTNode left, ASTNode right) {
        this.type = type;
        this.left = left;
        this.right = right;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Rule getRule() {
        return rule;
    }

    public void setRule(Rule rule) {
        this.rule = rule;
    }

    public ASTNode getLeft() {
        return left;
    }

    public void setLeft(ASTNode left) {
        this.left = left;
    }

    public ASTNode getRight() {
        return right;
    }

    public void setRight(ASTNode right) {
        this.right = right;
    }
}
