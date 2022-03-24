package com.example.cerajem.dto;

public class PostJadeDTO {
    String p1;
    String p2;
    public PostJadeDTO(String p1, String p2, String p3, PostJadeNestDTO pARAM) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		PARAM = pARAM;
	}
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
	public String getP2() {
		return p2;
	}
	public void setP2(String p2) {
		this.p2 = p2;
	}
	public String getP3() {
		return p3;
	}
	public void setP3(String p3) {
		this.p3 = p3;
	}
	public PostJadeNestDTO getPARAM() {
		return PARAM;
	}
	public void setPARAM(PostJadeNestDTO pARAM) {
		PARAM = pARAM;
	}
	String p3;
    PostJadeNestDTO PARAM;
}
