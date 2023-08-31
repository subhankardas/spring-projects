package com.codespark.springsecurityresourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableMethodSecurity
public class SpringSecurityResourceServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityResourceServerApplication.class, args);
	}

}

@Service
class PostService {

	@PreAuthorize("hasAuthority('SCOPE_user.read')")
	public Post getPost() {
		return new Post("Hello World!", "Spring Security is awesome!", "CodeSpark");
	}

}

@RestController
@RequestMapping("/secure")
@RequiredArgsConstructor
class SecuredResourceController {

	private final PostService postService;

	@GetMapping("/post")
	public Post getPost() {
		return postService.getPost();
	}

	@GetMapping("/me")
	public String me(@AuthenticationPrincipal Jwt jwt) {
		return "Hello " + jwt.getSubject() + "!";
	}

}

@Data
@AllArgsConstructor
class Post {

	private String title;
	private String content;
	private String author;

}