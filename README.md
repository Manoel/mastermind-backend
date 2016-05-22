Mastermind Backend Implementation (Challenge One)
=============

## The Game

Let's play Mastermind! If you've never played Mastermind, take a few minutes to check out the rules here: https://en.wikipedia.org/wiki/Mastermind_(board_game)#Gameplay_and_rules. We would also recommend taking some time to play a game or two with your teammates to get a better understanding of the game. The typical Mastermind game is played with 4 colors and 4 positions; however, for our challenge, we will be using 8 colors and 8 positions. Our engineering challenge will revolve around building different parts of this game.
There will be three separate challenges to this task. Any task by itself is sufficient for a hackathon submission.

## Challenge One: Backend Implementation

Are you a backend architect that can spin up robust, well tested APIs? Then this may be the challenge for you. Build an API that you can play Mastermind with! The language and stack you choose to use is it up to you. The
endpoints you create and the structure are up to you but it must be able to support multiple users hitting the API at the same time playing different games of Mastermind and the API must respond with the proper number of exact and near matches for every guess.
To go a bit further, try implementing a multiplayer component to the game where a user can hit an endpoint to create a game and wait for another user to join the same game. Once the second user joins, the API waits for
guesses from both users and responds to both users with its response once both guesses come in. The user that guesses the code first wins and the final response shows both users' guessing history.

## End points

## POST /new_game

This endpoint requires you to POST with a user field.

### Params

```javascript
{ "user: "Manoel Menezes" }
```

### Response

```javascript
{
	"colors":["R","B","G","Y","O","P","C","M"],
	"code_length":8,
	"game_key":"9f0adb71-b0f0-440d-b93c-cb1395463761",
	"num_guesses":0,
	"past_results":[],
	"solved":false,
	"multiplayer":false,
	"can_start":true,
	"turn":1
}
```

### Example with curl

```javascript
curl -H "Accept: application/json" -H "Content-Type: application/json" -X POST "http://localhost:9000/new_game" -d '{"user":"Manoel Menezes"}'
```
### Every subsequent POST request will require you to supply the game_key field.

## POST /guess

This endpoint requires you to POST with the game_key and a code consisting of 8 letters of RBGYOPCM (corresponding to Red, Blue, Green, Yellow, Orange, Purple, Cyan, Magenta).

### Params

```javascript
{ 
    "code": "RPYGOGOP", 
    "game_key": "9f0adb71-b0f0-440d-b93c-cb1395463761" 
}
```

### Response

```javascript
{
	"code_length":8,
	"colors":["R","B","G","Y","O","P","C","M"],
	"game_key":"9f0adb71-b0f0-440d-b93c-cb1395463761",
	"guess":"BYBCCMYO",
	"num_guesses":1,
	"pastResults":[
		{
			"exact":2,
			"guess":"BYBCCMYO",
			"near":2}
	],
	"solved":false,
	"multiplayer":false,
	"turn":1,
	"user":"Manoel Menezes",
	"result":{
		"exact":2,
		"near":2
	}
}
```

### Example with curl

```javascript
curl -H "Accept: application/json" -H "Content-Type: application/json" -X POST "http://localhost:9000/guess" -d '{"code":"MMMMMMMM","game_key":"9f0adb71-b0f0-440d-b93c-cb1395463761"}'
```

### Once you guess the correct code, you will receive the time it took for you to complete the challenge

```javascript
{
	"code_length":8,
	"colors":["R","B","G","Y","O","P","C","M"],
	"game_key":"9f0adb71-b0f0-440d-b93c-cb1395463761",
	"guess":"GPPMGCPP",
	"num_guesses":2,
	"pastResults":[
		{
			"exact":1,
			"guess":"MMMMMMMM",
			"near":0
		},
		{
			"exact":8,
			"guess":"GPPMGCPP",
			"near":0
		}
	],
	"solved":true,
	"multiplayer":false,
	"turn":1,
	"user":"Manoel Menezes",
	"result":"You win!",
	"time_taken":107.0,
	"time_over":false
}
```

### After 5 minutes you will receive the following JSON object:

```javascript
{
	"code_length":8,
	"colors":["R","B","G","Y","O","P","C","M"],
	"game_key":"9f0adb71-b0f0-440d-b93c-cb1395463761",
	"guess":"MMMMMMMM",
	"num_guesses":7,
	"pastResults":[
		{
			"exact":1,
			"guess":"MMMMMMMM",
			"near":0
		}
	],
	"solved":false,
	"multiplayer":false,
	"turn":1,
	"user":"Manoel Menezes",
	"result":"Time over!",
	"time_taken":325.0,
	"time_over":true
}
```

## POST /new_multiplayer_game

This endpoint requires you to POST with a user field.

### Params

```javascript
{ "user: "Manoel Menezes" }
```

### Response (multiplayer is true and can_start is false because other player needs to join)

```javascript
{
	"colors":["R","B","G","Y","O","P","C","M"],
	"code_length":8,
	"game_key":"b91dfc81-0f8c-4cb5-accb-beed03b8f64c",
	"num_guesses":0,
	"past_results":[],
	"solved":false,
	"multiplayer":true,
	"can_start":false,
	"turn":1
}
```


### Example with curl

```javascript
curl -H "Accept: application/json" -H "Content-Type: application/json" -X POST "http://localhost:9000/new_multiplayer_game" -d '{"user":"Manoel Menezes"}'
```

## POST /join

This endpoint requires you to POST with a user field and the game_key.

### Params

```javascript
{ "user: "Manoel Menezes", "game_key": "b91dfc81-0f8c-4cb5-accb-beed03b8f64c" }
```

### Response (multiplayer is true and can_start is true because other player has already joined)

```javascript
{
	"colors":["R","B","G","Y","O","P","C","M"],
	"code_length":8,
	"game_key":"b91dfc81-0f8c-4cb5-accb-beed03b8f64c",
	"num_guesses":0,
	"past_results":[],
	"solved":false,
	"multiplayer":true,
	"can_start":true,
	"turn":1
}
```

### Example with curl

```javascript
curl -H "Accept: application/json" -H "Content-Type: application/json" -X POST "http://localhost:9000/join" -d '{"user":"Alynne", "game_key":"b91dfc81-0f8c-4cb5-ab-beed03b8f64c"}'
```

## POST /guess_multiplayer

This endpoint requires you to POST with the game_key, a code consisting of 8 letters of RBGYOPCM (corresponding to Red, Blue, Green, Yellow, Orange, Purple, Cyan, Magenta) and the user indicating if it is the first player or the second player.

### Params

```javascript
{ 
    "code": "RPYGOGOP", 
    "game_key": "9f0adb71-b0f0-440d-b93c-cb1395463761",
    "user": 1 
}
```

### Response (turn now is player 2)

```javascript
{
	"code_length":8,
	"colors":["R","B","G","Y","O","P","C","M"],
	"game_key":"09127d4e-64c1-43a3-be4d-8eb69ec7f50d",
	"guess":"MRGBYCCC",
	"num_guesses":1,
	"pastResults":[
		{
			"exact":0,
			"guess":"MRGBYCCC",
			"near":3
		}
	],
	"solved":false,
	"multiplayer":true,
	"turn":2,
	"user":"Alynne",
	"result":{
		"exact":0,
		"near":3
	}
}
```

### Example with curl

```javascript
curl -H "Accept: application/json" -H "Content-Type: application/json" -X POST "http://localhost:9000/guess_multiplayer" -d '{"user":1, "game_key":"09127d4e-64c1-43a3-be4d-8eb69ec7f50d", "code":"MRGBYCCC"}'
```

### Once you guess the correct code, you will receive the time it took for you to complete the challenge

```javascript
{
	"code_length":8,
	"colors":["R","B","G","Y","O","P","C","M"],
	"game_key":"a409c305-ddf3-422e-ac13-6886859b46f6",
	"guess":"MCCRYGOC",
	"num_guesses":3,
	"pastResults":[
		{
			"exact":3,
			"guess":"MRGBYCCC",
			"near":4
		},
		{
			"exact":3,
			"guess":"MRGBYCCC",
			"near":4
		},
		{
			"exact":8,
			"guess":"MCCRYGOC",
			"near":0}
	],
	"solved":true,
	"multiplayer":true,
	"turn":2,
	"user":"Alynne",
	"result":"You win!",
	"time_taken":61.0,
	"time_over":false
}
```

Back End
========

* Uses Spring Boot to build the web application for the back end.

* Uses Spring MVC to build a REST API.

## Dependencies

* spring-boot-starter-web
* spring-boot-starter-jdbc
* hsqldb
* json-path
* org.json

## Back end project

* **src/main/java**
  * **com.axiomzen.mastermind.presentation**: REST Controller
  * **com.axiomzen.mastermind.application**: Application service and Commands
  * **com.axiomzen.mastermind.domain**: Domain classes (Aggregates, Value Objects, Repository interface) 
  * **com.axiomzen.mastermind.infrastructure**: Repository implementation.

* **src/main/resources**: Application configuration ( **the back end server is listening on port 9000** ), database schema creation.  
  

Tools
====

You need to install the following tools if you want to run this application:

* JDK 8(http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Maven(http://maven.apache.org/) (the application is tested with Maven 3.0.5)

Running the Application
=======================

You can run each application by using the following command:

    mvn clean spring-boot:run
