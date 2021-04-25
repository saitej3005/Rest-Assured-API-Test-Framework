Feature: Validating JsonCRUD API

  @PostsAPI
  @test
  Scenario: Create Json Bin using POST API request and verify Response details
    Given I add header for API
      | header                                | value      |
      | Content-Type         | application/json |
      | X-Master-Key | $2b$10$vQPikd1lKLRr90.C.PWlO.pBZkUcwjTLfRBVFwxSdvi27lmcolRa2  |
      | X-Bin-Name              | Test Bin |
    And I add param for Post request
      | param                                | value      |
      | description         | Test JSON Bin               |
    When I calls "createbin" API with "Post" http request
    Then the status code is 200
    And validate the response details
      | param                                | value      |
      | name       | Test Bin |
      | private       | true |
      | description         | Test JSON Bin               |


  @GetAPI
  @test
  Scenario: Read Json Bin using Get Request and verify response details
    Given I add header for API
      | header                                | value      |
      | Content-Type         | application/json |
      | X-Master-Key | $2b$10$vQPikd1lKLRr90.C.PWlO.pBZkUcwjTLfRBVFwxSdvi27lmcolRa2  |
      | X-Bin-Name              | Test Bin |
    When I calls "readBin" API with "Get" http request
    Then the status code is 200
    And validate the response details
      | param                                | value      |
      | description       | Test JSON Bin |
      | private       | true |



  @PutAPI
  @test
  Scenario: Update Json Bin using PUT request and verify response
    Given I add header for API
      | header                                | value      |
      | Content-Type         | application/json |
      | X-Master-Key | $2b$10$vQPikd1lKLRr90.C.PWlO.pBZkUcwjTLfRBVFwxSdvi27lmcolRa2  |
      | X-Bin-Name              | Test Bin |
      | X-Bin-Private             | false |
    And I add param for Post request
      | param                                | value      |
      | description         | Test Updated Bin               |
    When I calls "updateBin" API with "Put" http request
    Then the status code is 200
    And validate the response details
      | param                                | value      |
      | description         | Test Updated Bin               |
      | private       | true |

  @GetAPI
  @test
  Scenario: Count Bin versions using Get Request and verify response details
    Given I add header for API
      | header                                | value      |
      | Content-Type         | application/json |
      | X-Master-Key | $2b$10$vQPikd1lKLRr90.C.PWlO.pBZkUcwjTLfRBVFwxSdvi27lmcolRa2  |
    When I calls "countBinVersions" API with "Get" http request
    Then the status code is 200
    And validate the response details
      | param                                | value      |
      | versionCount       | 0 |
      | private       | true |



  @DeleteAPI
   @test
  Scenario: Delete Json Bin and verify Bin deleted successfully
     Given I add header for API
       | header                                | value      |
       | Content-Type         | application/json |
       | X-Master-Key | $2b$10$vQPikd1lKLRr90.C.PWlO.pBZkUcwjTLfRBVFwxSdvi27lmcolRa2  |
       | X-Bin-Name              | Test Bin |
    When I calls "deleteBin" API with "Delete" http request
    Then the status code is 200
    And validate the response details
       | param                                | value      |
       | message       | Bin deleted successfully |












