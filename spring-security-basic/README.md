# Spring Security - Basic Authentication
 In the basic authentication, we send a username and password as part of our request. When we provide a username and password, it allows us to access the resource.

 ## Steps to secure web resources
 1. Open **pom.xml** and add the **spring-boot-starter-security**. It automatically configures the basic security for us.
 2. Restart the server, we get a *password* in the log. Each time the server starts up the password will be different.
 3. In the REST client use *basic authorization* with username as **user** and **password** from the logs. This is the default configured user details.
 4. Now to avoid dynamically generated password on runtime, we can set the username and password in the *application.properties* files to override the default user details.
 5. We can also create bean for *UserDetailsService* interface and configure for adding multiple user details for auth provider using in memory user manager.
 6. Now finally to secure REST endpoints we will need to create bean for *SecurityFilterChain* interface and configure URL patterns and auth accesses for users. This will override the *DefaultSecurityFilterChain* implementation.

 ### Sample HTTP request
 ```bash
 curl --location 'http://localhost:8080/private/users' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ=' 
 ```
 Here the *Authorization* header value i.e. *Basic YWRtaW46cGFzc3dvcmQ=* indicates the auth type is Basic and token i.e. Base64 encoding of *username : password*.