The main idea of this project was to create a mobile app to Vanhack.
Whit this app the users would be able to apply for jobs listed on the platform.

The backend side of this project was done by Jonatha Moreno Schmitz.

How to use this API

POST METHODS
--------------
/login -> responsible for the user login
Receives: 
 - A JSON with the following format: {"username":"jonatha","password":"password"}
Returns: a JWT Token

/user -> responsible for creating another user
Receives: 
 - A header named "Authorization" which the value is the JWT Token received from the /login
 - A JSON with the following format: { "username": "JonathaMS", "name": "Jonatha Schmitz", "email":"jonatha@gmail.com", "password" : "teste",  "permission" : "1" }
Returns: 
 - One of the three messages: “User Created”, “User already exists” or “Error creating user”.

GET METHODS
--------------
/user -> responsible for returnin all users of the database
Receives:
- A header named "Authorization" which the value is the JWT Token received from the /login
Returns: 
 -A JSON containing all users of the database with the following format:
[
    [1,"jonatha","Jonatha 123 Schmitz","jonatha.schmitz@gmail.com","password","2"],
    [5,"JonathaMS","Jonatha Schmitz","jonathae2@gmail.com","teste","1"]
]
The values returned by ths JSON equals to the following fields in order = id, username, name, email, password, permission.

/user/:id -> responsible for returnin a single user of the database
Receives:
- A header named "Authorization" which the value is the JWT Token received from the /login
- The id of the user to be returned on the query param.
Returns: 
 -A JSON containing the selected user with the following format:
    [1,"jonatha","Jonatha 123 Schmitz","jonatha.schmitz@gmail.com","password","2"],
The values returned by ths JSON equals to the following fields in order = id, username, name, email, password, permission.

PUT METHODS
--------------
/user -> responsible for updating the selected user
Receives: 
 - A header named "Authorization" which the value is the JWT Token received from the /login
 - A JSON with the following format: { “id” : “1”,  "username": "JonathaMS", "name": "Jonatha Schmitz", "email":"jonatha@gmail.com", "password" : "teste",  "permission" : "1" }
Returns: 
 - One of the three messages: “User Updated”, “User not found” or “Error creating user”.s

DELETE METHODS
--------------
/user/:id -> responsible for updating the selected user
Receives: 
 - A header named "Authorization" which the value is the JWT Token received from the /login
- The id of the user to be deleted on the query param.
Returns: 
 - One of the three messages: “User Deleted” or “User not found”.

