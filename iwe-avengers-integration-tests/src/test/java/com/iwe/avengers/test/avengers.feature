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

Scenario: Get Avenger2 by Id

Given path 'avengers', 'aaaa-aaaa-aaaa-aaab'
When method get
Then status 200

And match $ == {id: '#string', name: 'Captain America', secretIdentity: 'Steve Rogers'}



