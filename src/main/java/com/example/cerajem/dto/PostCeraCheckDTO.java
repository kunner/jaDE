package com.example.cerajem.dto;

public class PostCeraCheckDTO {
	String USER_NM;
	public PostCeraCheckDTO(String uSER_NM, String bIRTH, String pHONE) {
		super();
		USER_NM = uSER_NM;
		BIRTH = bIRTH;
		PHONE = pHONE;
	}
	public String getUSER_NM() {
		return USER_NM;
	}
	public void setUSER_NM(String uSER_NM) {
		USER_NM = uSER_NM;
	}
	public String getBIRTH() {
		return BIRTH;
	}
	public void setBIRTH(String bIRTH) {
		BIRTH = bIRTH;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	String BIRTH;
	String PHONE;
}
