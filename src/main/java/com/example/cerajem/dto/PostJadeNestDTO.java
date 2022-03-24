package com.example.cerajem.dto;

public class PostJadeNestDTO {
	String C_CD;
	String YMD;
	public String getC_CD() {
		return C_CD;
	}
	public void setC_CD(String c_CD) {
		C_CD = c_CD;
	}
	public String getYMD() {
		return YMD;
	}
	public void setYMD(String yMD) {
		YMD = yMD;
	}
	public PostJadeNestDTO(String c_CD, String yMD) {
		super();
		C_CD = c_CD;
		YMD = yMD;
	}
	
}
