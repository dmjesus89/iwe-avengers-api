Feature: Perform integrated tests on the Avengers registration API

Background:
* url 'https://mrufr5vffa.execute-api.us-east-2.amazonaws.com/dev'

* def getToken =
"""
function() {
 var TokenGenerator = Java.type('com.iwe.avengers.test.authorization.TokenGenerator');
 var sg = new TokenGenerator();
 return sg.getToken();
}
"""
* def token = call getToken


Scenario: GET Avenger Not Found 
Given path 'avengers', 'invalid'
And header Authorization = 'Bearer ' + token
When method get
Then status 404

Scenario: Post Avenger
Given path 'avengers'
And header Authorization = 'Bearer ' + token
And request { name: 'Captain America', secretIdentity: 'Steve Rogers'}
When method post
Then status 201
And match response == {id:'#string', name: 'Captain America', secretIdentity: 'Steve Rogers'}

* def savedAvenger = response

Scenario: Update Avenger
Given path 'avengers', savedAvenger.id
And header Authorization = 'Bearer ' + token
And request { name: 'Hulk', secretIdentity: 'Bruce banner'}
When method put
Then status 200
And match response == {id: savedAvenger.id, name: 'Hulk', secretIdentity: 'Bruce Banner'}

* def updatedAvenger = response

#Get Avenger by id
Given path 'avengers', updatedAvenger.id
And header Authorization = 'Bearer ' + token
When metohd get
Then status 200 
And match response == updatedAvenger

#Scenario: Delete Avenger Success 
Given path 'avengers', updatedAvenger.id
And header Authorization = 'Bearer ' + token
When method delete
Then status 204

#Scenario: Delete Avenger Not Found 
Given path 'avengers',  updatedAvenger.id
And header Authorization = 'Bearer ' + token
When method delete
Then status 404

#Scenario: Update Avenger Not Found
Given path 'avengers', updatedAvenger.id
And header Authorization = 'Bearer ' + token
And request { name: 'Hulk', secretIdentity: 'Bruce banner'}
When method put
Then status 404


Scenario: New Avenger must return 400 for invalid payload
Given path 'avengers'
And header Authorization = 'Bearer ' + token
And request { secretIdentity: 'Steve Rogers'}
When method post
Then status 400











