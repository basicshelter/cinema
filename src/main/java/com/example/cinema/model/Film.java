package com.example.cinema.model;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Film {
	@JsonProperty("id")
	private int id;
	@NotNull(message = "{film.directorId.notNull}")
	@Positive(message = "{film.directorId.positive}")
	@JsonProperty("directorId")
	private int directorId;
	@NotBlank(message = "{film.name.notBlank}")
	@JsonProperty("name") 
	private String name;
	@JsonProperty("releaseDate")
	@NotNull(message = "{film.releaseDate.notNull}")
	@Past(message = "{film.releaseDate.past}")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate releaseDate;
	@NotBlank(message = "{film.genre.notBlank}")
	@JsonProperty("genre")
	private String genre;
	
	@JsonCreator
	public Film(String name, 
				LocalDate releaseDate, 
				String genre) {
		this.name = name;
		this.releaseDate = releaseDate;
		this.genre = genre;
	}
	
	public Film(int directorId, 
				String name, 
				LocalDate releaseDate, 
				String genre) {
	this.directorId = directorId;
	this.name = name;
	this.releaseDate = releaseDate;
	this.genre = genre;
}
	
	public Film(int id, 
				int directorId, 
				String name, 
				LocalDate releaseDate, 
				String genre) {
		this.id = id;
		this.directorId = directorId;
		this.name = name;
		this.releaseDate = releaseDate;
		this.genre = genre;
	}
	
	public int getId() {
		return id;
	}
	
	public int getDirectorId() {
		return directorId;
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	
	public String getGenre() {
		return genre;
	}
}
