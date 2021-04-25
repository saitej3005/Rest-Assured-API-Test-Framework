Feature: Error validation for Create Bin API

@PostsAPI
@test
Scenario: Validate CreateBin API for Content Type
Given I add header for API
| header                                | value      |
| X-Master-Key | $2b$10$vQPikd1lKLRr90.C.PWlO.pBZkUcwjTLfRBVFwxSdvi27lmcolRa2  |
| X-Bin-Name              | Test Version |
| X-Bin-Versioning              | true |
| X-Bin-Private             | false |
And I add param for Post request
| param                                | value      |
| description         | Test JSON Bin               |
When I calls "createbin" API with "Post" http request
Then the status code is 400
Then validate the response details
| param                                | value      |
| message         | You need to pass Content-Type set to application/json               |

@PostsAPI
@test
Scenario: Validate CreateBin API for Header "X-Master Key"
  Given I add header for API
    | header                                | value      |
    | Content-Type         | application/json |
    | X-Bin-Name              | Test Version |
    | X-Bin-Versioning              | true |
    | X-Bin-Private             | false |
  And I add param for Post request
    | param                                | value      |
    | description         | Test JSON Bin               |
  When I calls "createbin" API with "Post" http request
  Then the status code is 401
  Then validate the response details
    | param                                | value      |
    | message         | You need to pass X-Master-Key in the header              |


@PostsAPI
@test
Scenario: Validate CreateBin API for Body
  Given I add header for API
    | header                                | value      |
    | Content-Type         | application/json |
    | X-Master-Key | $2b$10$vQPikd1lKLRr90.C.PWlO.pBZkUcwjTLfRBVFwxSdvi27lmcolRa2  |
    | X-Bin-Versioning              | true |
    | X-Bin-Private             | false |
    | X-Bin-Name              | Test Version |
  When I calls "createbin" API with "Post" http request
  Then the status code is 400
  Then validate the response details
    | param                                | value      |
    | message         | Bin cannot be blank              |


  @PostsAPI
  @test
  Scenario: Validate CreateBin API for Bin Name more than 128 chars
    Given I add header for API
      | header                                | value      |
      | X-Master-Key | $2b$10$vQPikd1lKLRr90.C.PWlO.pBZkUcwjTLfRBVFwxSdvi27lmcolRa2  |
      | X-Bin-Name              | Test Bin Name with More than allowed size providing to test error code with this scenario  Test Bin Name with More than allowed size providing|
      | Content-Type         | application/json |
      | X-Bin-Versioning              | true |
      | X-Bin-Private             | false |
    And I add param for Post request
      | param                                | value      |
      | description         | Test JSON Bin               |
    When I calls "createbin" API with "Post" http request
    Then the status code is 400
    Then validate the response details
      | param                                | value      |
      | message         | X-Bin-Name cannot be blank or over 128 characters               |

