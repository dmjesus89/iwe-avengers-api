Feature: Perform integrated tests on the Avengers registration API

Background:
* url 'https://mrufr5vffa.execute-api.us-east-2.amazonaws.com/dev'

Scenario: Get Avenger by Id
Given path 'avengers', 'aaaa-aaaa-aaaa-aaaa'
When method get
Then status 200
And match $ == {id: 'aaaa-aaaa-aaaa-aaaa', name: 'Iron Man', secretIdentity: 'Tony Stark'}

Scenario: Post Avenger
Given path 'avengers'
And request { name: 'Captain America', secretIdentity: 'Steve Rogers'}
When method post
Then status 201
And match response == {id:'#string', name: 'Captain America', secretIdentity: 'Steve Rogers'}

Scenario: Post Avenger 2
Given path 'avengers'
And request { name: 'Captain America', secretIdentity: 'Steve Rogers'}
When method post
Then status 201
And match response == {id:'#string', name: 'Captain America', secretIdentity: 'Steve Rogers'}

Scenario: Delete Avenger  Found 
Given path 'avengers', 'aaaa-aaaa-aaaa-aaaa'
When method delete
Then status 204

Scenario: Delete Avenger Not Found 
Given path 'avengers', 'aaaa-aaaa-aaaa-aaaa'
When method get
Then status 404


Scenario: Delete Avenger Not Found 
Given path 'avengers', 'invalid'
When method delete
Then status 404

Scenario: Update Avenger
Given path 'avengers', 'aaaa-aaaa-aaaa-aaaa'
And request { name: 'Hulk', secretIdentity: 'Bruce banner'}
When method put
Then status 200
#And match response == {id:'#string', name: 'Hulk', secretIdentity: 'Bruce Banner'}

Scenario: Update Avenger Not Found
Given path 'avengers', 'aaaa-aaaa-aaaa-aaaa'
And request { name: 'Hulk', secretIdentity: 'Bruce banner'}
When method put
Then status 404
#And match response == {id:'#string', name: 'Hulk', secretIdentity: 'Bruce Banner'}


Scenario: New Avenger must return 400 for invalid payload
Given path 'avengers'
And request { secretIdentity: 'Steve Rogers'}
When method post
Then status 400

Scenario: Update Avenger 
Given path 'avengers', 'aaaa-aaaa-aaaa-aaad'
And request { secretIdentity: 'Steve Rogers'}
When method post
Then status 403

Scenario: Avenger Not Found 
Given path 'avengers', 'invalid'
When method get
Then status 404








