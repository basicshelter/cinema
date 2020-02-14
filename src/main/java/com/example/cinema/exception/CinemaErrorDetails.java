package com.example.cinema.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CinemaErrorDetails {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime timestamp;
	private int status;
	private String message;
	
	public CinemaErrorDetails (LocalDateTime timestamp, int status, String message) {
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public int getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}

	
}
