package com.gateway.gatewayservice.config;

import com.gateway.gatewayservice.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class GatewayConfig {

	@Autowired
	private JwtAuthenticationFilter filter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes().route("USER-SERVICE", r -> r.path("/users/**").filters(f -> f.filter(filter)).uri("http://localhost:3005"))
				.route("COMMENT-SERVICE", r -> r.path("/posts/{postId}/comments/**").filters(f -> f.filter(filter)).uri("http://localhost:3015"))
				.route("POST-SERVICE", r -> r.path("/posts/**").filters(f -> f.filter(filter)).uri("http://localhost:3010"))
				.route("LIKE-SERVICE", r -> r.path("/postsOrComments/{postOrCommentId}/likes/**").filters(f -> f.filter(filter)).uri("http://localhost:3020"))
				.route("AUTH", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("http://localhost:6000")).build();
	}

}
