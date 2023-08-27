# Spring Security - JSON Web Token (JWT) Authentication
 This project is an extension of the basic authentication implementation, instead of validating username and password based on the Basic token we will use Bearer token with a signed JWT in the *Authorization* header.

 ## Steps to secure web resources - JWT based authorization
 1. Open **pom.xml** and the dependencies from *io.jsonwebtoken* along with others, to add support for Java-JWT integration and Spring Security.
 2. Next in the *SecurityFilterChain* configured implementation add our custom *JwtAuthenticationFilter* before the *UsernamePasswordAuthenticationFilter* in the filter chain. This way we make sure our JWT validation logic is checked before the default username and password validation is done by Spring Security.
 3. Now our custom JWT filter will intercept requests, extract token from header, validate the token, parse the claims and query our database for the encoded username/subject in the token. If such user is present in our database set authentication in the *SecurityContextHolder* with the username and user authorities from database. Pass on the request in the filter chain.
 4. Now the request in authorized in the next filter using the authentication set in the context.

 ### Sample HTTP request
 ```bash
 curl --location 'http://localhost:8080/private/users' --header 'Authorization: Bearer <token>'
 ```
 Now we need to generate our JWT for users via an authentication server that signs and provides a valid JWT for our use. For a simple demo purpose we will use [jwt.io](https://jwt.io/) for signing our JWT.

On the [jwt.io](https://jwt.io/) website we will update the payload as per our requirement. We will need to update the *sub* field as *user/admin* in our case as these are valid usernames. Also paste the *secret key* from properties in the textfield with the Base64 encoded option as checked since we are using the key with Base64 encoding in our JWT parser key.

### JSON Web Token (JWT) Structure
JWT tokens consist of 3 parts separated by a period(.):
1. **Header** - contains metadata about the type of token and the cryptographic algorithms used to secure its contents.
2. **Payload** - contains verifiable security statements, such as the identity of the user and the permissions they are allowed.
3. **Signature** - used to validate that the token is trustworthy and has not been tampered with. When you use a JWT, you must check its signature before storing and using it.

The JWT typically looks like:
```
<header>.<payload>.<signature>
```
### Simple Authorization Server
Now to enable our server to provide signed JWT based on user credentials. We will need to perform the following steps:

5. Add a new */login* endpoint that accepts user credentials and authenticate based on stored user details in database.
6. If user is present and password matches, we generate the token based of stored user roles and return in the */login* endpoint.
7. We will also add the JWT expiration check in our *JwtAuthenticationFilter* implementation along with token validation before setting the username and password authentication in the *SecurityContextHolder*.
8. Now on restarting the server we can login with valid credentials and access secured resources with the new generated JWT. Also note that the generated token has an expiration configured in the properties.
```bash
curl --location 'http://localhost:8080/login' --header 'Content-Type: application/json' \
--data '{
    "username": "user",
    "password": "password"
}'
```
