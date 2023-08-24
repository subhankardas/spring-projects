# Spring Security - Basic Authentication
 In the basic authentication, we send a username and password as part of our request. When we provide a username and password, it allows us to access the resource.

 ## Steps to secure web resources - In-memory user authorization
 1. Open **pom.xml** and add the **spring-boot-starter-security**. It automatically configures the basic security for us.
 2. Restart the server, we get a *password* in the log. Each time the server starts up the password will be different.
 3. In the REST client use *basic authorization* with username as **user** and **password** from the logs. This is the default configured user details.
 4. Now to avoid dynamically generated password on runtime, we can set the username and password in the *application.properties* files to override the default user details.
 5. We can also create bean for *UserDetailsService* interface and configure for adding multiple user details for auth provider using in memory user manager.
 6. Now finally to authorize REST endpoints we will need to create bean for *SecurityFilterChain* interface and configure URL patterns and auth accesses for users. This will override the *DefaultSecurityFilterChain* implementation.

 ### Sample HTTP request
 ```bash
 curl --location 'http://localhost:8080/private/users' --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ=' 
 ```
 Here the *Authorization* header value i.e. *Basic YWRtaW46cGFzc3dvcmQ=* indicates the auth type is Basic and token i.e. Base64 encoding of *username : password*.

 ### Custom user details service - DAO based user authorization
 7. Now to implement database controlled user authorization, we will first comment out the above in-memory user details service and create our custom service that implements UserDetailsService interface.
 8. We then override and provide implementation for *loadUserByUsername(String username)* method which will similarly return UserDetails object that is used to authenticate and authorize requests.
 9. One more important step is to configure the *PasswordEncoder* with an implementation of our choice here we have used *BCryptPasswordEncoder* class. 

 #### Roles vs. Authorities
 Both are same i.e. implementation of *GrantedAuthority* interface just that for roles the string values are appended with *"ROLE_"* in the prefix.

 UserDetails Authorities:
 ```java
 authorities(Collection<? extends GrantedAuthority>)
 Collection<? extends GrantedAuthority> getAuthorities();
 ```
 Roles:
```java
authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
```