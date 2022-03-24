package com.example.cerajem.controller;

import java.net.URI;

import java.util.Collections;

import javax.net.ssl.SSLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import com.example.cerajem.dto.PostJadeDTO;
import com.example.cerajem.dto.PostJadeNestDTO;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import com.example.cerajem.dto.PostCeraCheckDTO;


import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;



@Controller
public class HomeController {

	@GetMapping("/jade")
	@ResponseBody
	public String httpClientJade() {
		
		
		String response = "";

		try {
			
			/*
			final SslContext sslContext = SslContextBuilder
			        .forClient()
			        .trustManager(InsecureTrustManagerFactory.INSTANCE)
			        .build(); 
	    
			  .clientConnector(new ReactorClientHttpConnector(httpClient))*/
			//HttpClient httpClient = HttpClient.create().secure(t -> t.sslContext(sslContext));

			WebClient client = WebClient.builder()
			  .baseUrl("https://cerahr.ceragem.com:9950")
			  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
			  .build();
		
			/*
				response = client.post()
					    .uri("/restful/getToken")
					    .accept(MediaType.APPLICATION_JSON)
					    .retrieve()
					    .bodyToMono(String.class)
					    .block();*/
				response = client.post()
				    .uri("/restful")
				    .accept(MediaType.APPLICATION_JSON)
				    .body(Mono.just(), PostJadeDTO.class)
				    .retrieve()
				    .bodyToMono(String.class)
				    .block();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;
	}
	
}
