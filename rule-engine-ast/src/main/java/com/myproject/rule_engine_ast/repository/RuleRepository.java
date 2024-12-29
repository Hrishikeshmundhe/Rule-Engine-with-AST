package com.myproject.rule_engine_ast.repository;

import com.myproject.rule_engine_ast.model.Rule;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
    // Additional query methods (if needed) can be defined here
}
