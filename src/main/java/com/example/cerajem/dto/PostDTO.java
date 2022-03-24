package com.example.cerajem.dto;

public class PostDTO {

	String a;
	String b;
	PostNestDTO c;
	
	public PostDTO(String a, String b, PostNestDTO c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}
	public PostNestDTO getC() {
		return c;
	}
	public void setC(PostNestDTO c) {
		this.c = c;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
}
