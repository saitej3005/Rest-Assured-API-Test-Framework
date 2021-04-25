### Skeleton of  Automation Test Framework

    .
    ├── src/main                # placeholder for all java and API related code 
    ├── src/test                # Client Source files like Feature File and Step definitiins
    ├── Pom.xml                 # maven dependency file 
    ├── Package.json            # node modules and dev utilities
    └── README.md

### API Testing


    ├── ...
    ├── src                  # files 
    │   ├── features         # Contains given scenarios in different feature files
    │   ├── Utils            # Rest Assured Request and Response Specification and also base URL addition
    │   ├── stepdefs          # Defining Given, when and then
    │   ├── target/cucumber-html-reports     # cucumber_test_report in html and json formate  └── ...

#### Features Files,

Created  Features files based on the given Business requirement.


Feature: Validating JsonCRUD API
Feature: Validating JsonBinVersions
Feature: Error validation for Create Bin API


#### Step Definitions,

Used Cucumber to write test cases which is called as feature files.
Feature file is written in simple english flow Gherkin syntax.
Step definition include class, method, and object handling etc (OOPS Concept).



### Test Report

3 Feature files     
14 Scenarios   (0 failed, 14 passed)

Test Execution

Execute from IDE by right clicking on features folder. run mvn clean test verify from command line.
Reports

Reports are generated and placed in target folder target/cucumber-html-reports/overview-features.html


### Name and optionally contact information

Sairam Emmidishetti
saitej3005@gmail.com


