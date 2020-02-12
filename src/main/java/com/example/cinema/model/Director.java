package com.example.cinema.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Director {
	@JsonProperty("id")
	private int id;
	@NotBlank
	@JsonProperty("firstName")
	private String firstName;
	@NotBlank
	@JsonProperty("lastName")
	private String lastName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty("birthDate")
	private LocalDate birthDate;
	@JsonProperty("films")
	private List<Film> filmList; 
	
	public Director(String firstName, 
					String lastName, 
					LocalDate birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.filmList = new ArrayList<>();
	}
	
	@JsonCreator
	public Director(int id, 
					String firstName, 
					String lastName, 
					LocalDate birthDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.filmList = new ArrayList<>();
	}
	
	public Director(int id, 
					String firstName, 
					String lastName, 
					LocalDate birthDate,
					List<Film> filmList) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.filmList = filmList;
	}
	
	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public List<Film> getFilmList() {
		return filmList;
	}
	
	public void addFilm(Film film) {
		this.filmList.add(film);
	}
}
