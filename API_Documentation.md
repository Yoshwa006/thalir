ModelController (/api/models)
-----------------------------

GET     /api/models
Input:      -
Output:     List of all models (200 OK)

GET     /api/models/{id}
Input:      -
Output:     Model object / 404 if not found

POST    /api/models
Input:      { "name": "...", "description": "..." }
Output:     Created model (201 Created)

PUT     /api/models/{id}
Input:      { "name": "...", "description": "..." }
Output:     Updated model / 404 if not found

DELETE  /api/models/{id}
Input:      -
Output:     Success message / 404 if not found

GET     /api/models/public/check
Input:      -
Output:     "Server is Running!" (200 OK)


AuthController (/api/auth)
--------------------------

POST    /api/auth/register
Input:      { "username": "...", "email": "...", "password": "..." }
Output:     "Registration successful" (200 OK)

POST    /api/auth/login
Input:      { "email": "...", "password": "..." }
Output:     "Login successful" (200 OK)
OR "Invalid email or password"


General Notes
-------------
- All endpoints use JSON format for both input and output.
- Always set the header: Content-Type: application/json
- Common status codes: 200 OK, 201 Created, 404 Not Found
