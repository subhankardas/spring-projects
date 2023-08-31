# Spring Security - OAuth2 Authentication
Spring Authorization Server is an open-source framework built on top of Spring Security that allows you to create your own standards-based *OAuth2 Authorization Server* or *OpenID Connect Provider*. It implements many of the protocol endpoints available in the various OAuth2 and OIDC-related specifications, allowing you to focus more on your applications and users and less on flows and specifications. Because it sits atop Spring Security, it can also be used to create an authentication hub for adapting other authentication protocols to OAuth2.

Using Spring Security Authorization Server we will create our own standards-based OAuth2 Authorization Server and OpenID Connect Provider. Since it will come with most of the default configurations out-of-the-box. We will be ready to test auth using OAuth2 JWT with simple auth server and resource server.

### Steps to configure our OAuth2 Authorization & Resource Server:
1. We will need to register our client to the auth server *in-memory client repository* with client ID, secret, scopes, grant types and auth method for the token API. We can also configure our JWT expiration period i.e. TTL.
2. Now we just get our access token using the below API. Here the basic auth uses our client ID and secret as username and password.
```bash
curl --location --request POST 'localhost:8000/oauth2/token?grant_type=client_credentials&scope=user.read%20user.write' \
--header 'Authorization: Basic Y2xpZW50OnNlY3JldA=='
```
3. For our resource server we just need to set *OAuth2 JWT issuer URI*. Now using the access token from above API, we try to access our secured resources and we should be able to access secured resource based on user scopes in JWT.
```bash
curl --location 'localhost:8080/secure/post' --header 'Authorization: Bearer <access_token>'
```
4. Also we can get user details from JWT as shown in the endpoint.
```bash
curl --location 'localhost:8080/secure/me' --header 'Authorization: Bearer <access_token>'
```
