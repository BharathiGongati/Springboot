package org.jsp.springDataJpa.dto;

import java.util.List;

import lombok.Data;

@Data

public class ResponseStructure <T>{
	private T data;
	private String message;
	private int statuscode;
	
	

}
