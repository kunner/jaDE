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
	
	@GetMapping("/web/community/{to}")
	public String community(@PathVariable("to") String to) {
		return "/web/community/" + to;
	}
	@GetMapping("/web/main/{to}")
	public String main(@PathVariable("to") String to) {
		return "/web/main/" + to;
	}

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
				    .body(Mono.just(new PostJadeDTO("REFwWALk0KnxM8S55r5wAA==", "ZjH2yGNkSXC63TMeuSX5Hw==", "1MDHLqogOWJqzU7DtAspAg==", new PostJadeNestDTO("2011040", "20220302"))), PostJadeDTO.class)
				    .retrieve()
				    .bodyToMono(String.class)
				    .block();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;
	}
	@GetMapping("/sera")
	@ResponseBody
	public String httpClientSera() {
		
		WebClient client = WebClient.builder()
				  .baseUrl("https://cerachkrest-dev.omnifit.co.kr")
				  .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
				  .build();
		
		//.defaultCookie("cookieKey", "cookieValue")
		
		//.header("Authorization", "Bearer MY_SECRET_TOKEN")
		//(USER_NM=8mUd1hPyMMZXxxk4KWFuOw%3D%3D, BIRTH=tkiV2AJZP4zgKExUJNNHSw==, PHONE=BK4USiTqzkuZANioOgRLAA%3D%3D)
		String response = "";
		try {
			response = client.post()
				    .uri("/api/V2/measure_info")
				    .accept(MediaType.APPLICATION_JSON)
				    .body(Mono.just(new PostCeraCheckDTO("8mUd1hPyMMZXxxk4KWFuOw==", "tkiV2AJZP4zgKExUJNNHSw==", "BK4USiTqzkuZANioOgRLAA==")), PostCeraCheckDTO.class)
				    .retrieve()
				    .bodyToMono(String.class)
				    .block();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;
	}

	
	@PostMapping("/api/t1")
	@ResponseBody
	public String t1() {
		return "{\"tag\": \"스몰토크\", \"title\": \"부산출장 가는데, 들릴 만한 곳 추천 부탁드립니다.\","
				+ "\"name\": \"제임스\", \"date\": \"2022-03-01T03:23:00\"}";
	}
	
}
