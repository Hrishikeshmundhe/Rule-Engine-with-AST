Rule Engine with Abstract Syntax Tree (AST)

   This project is a Rule Engine application designed using Java and Spring Boot, leveraging an Abstract Syntax Tree (AST) to define, store, and evaluate rules. It includes functionalities for dynamic rule creation, storage in a database, evaluation against user-provided data, and robust error handling.

Features

1. Dynamic Rule Creation
   - Rules are represented using AST for structured and hierarchical processing.
   - Users can define custom rules dynamically through a JSON structure.

2. Rule Evaluation
   - Evaluate rules by providing test data and retrieve the results of rule execution.

3. Persistence
   - Rules and their AST nodes are stored in an H2 database for quick retrieval and evaluation.

4. API Endpoints
   - Expose RESTful APIs for rule creation, evaluation, and system health checks.

Implementation Details

1. Technologies Used
- Java 17
- Spring Boot
- H2 Database
- Postman for API testing
- Lombok for reducing boilerplate code
- Jackson for JSON serialization and deserialization

2. Key Components

     ASTNode Class

    - Represents nodes in the AST, supporting both operands and operators.
   - Fields:
      - type: Node type (e.g., operator or operand).
     
      - value: Value of the node (e.g., age > 18).
     
      - left and right: References to child nodes for operators.
          
      - Mapped to the ASTNODE table in the database.

    Rule Class
 
     - Represents a rule consisting of an AST structure.
    - Fields:
      - name: Rule name.
      - description: Optional description of the rule.
      - root: Reference to the root ASTNode of the rule.

   RuleDTO Class
     - Data Transfer Object for transferring rule-related data between layers.
    
     - Includes fields for rule metadata and the ASTNode structure.

   RuleEngineService Class
   - Handles business logic for rule creation, evaluation, and validation.

   - Methods:
      - createAndSaveRule(String ruleName, ASTNode astNode): Creates and persists a rule.
      - evaluateRule(Long ruleId, Map<String, Object> testData): Evaluates a rule against provided test data.

   RuleEngineController
      - Exposes APIs for interacting with the rule engine.
      - Endpoints:
        - POST /api/rules/create: Creates a new rule.
        - POST /api/rules/evaluate/{ruleId}: Evaluates a rule.
        - GET /api/rules/: Health check endpoint.


3)   Database Configuration
      
      - Configured to use H2 in-memory database for development and testing.
     
     - Tables:
        - RULE: Stores rule metadata.
        - ASTNODE: Stores AST node information.

4) Error Handling

     - Ensures validation of user input.

    - Provides meaningful error messages for invalid or missing data.

 5) Testing

     - Tested using Postman to verify all endpoints.

     - Unit tests cover service-layer logic.


6) How to Run the Application

   1. Clone the repository:

          git clone <repository_url>
   

   2. Navigate to the project directory:
  
          cd rule-engine-ast


   3. Build the project:
 
          mvn clean install


   4. Run the application:
  
          mvn spring-boot:run
  

7)  Access the API endpoints:
  
     - Health Check: [http://localhost:8080/api/rules/](http://localhost:8080/api/rules/)
 
     - Create Rule: POST http://localhost:8080/api/rules/create
   
     - Evaluate Rule: POST http://localhost:8080/api/rules/evaluate/{ruleId}


8) Future Enhancements

    - Add user authentication for API access.

    - Improve error handling with detailed exception messages.

    - Implement a front-end UI for rule management.

    - Extend support for more complex rule types and nested logic.


9) Contributing

     Contributions are welcome! Please fork the repository and submit a pull request.


10) License

      This project is licensed under the MIT License. See the LICENSE file for details.
 

11) Contact: 

     For any questions or issues, please reach out to the project maintainers.
