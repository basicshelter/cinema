package com.example.cinema.api;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.cinema.model.Director;
import com.example.cinema.service.DirectorService;

@RequestMapping("/api/director")
@RestController
public class DirectorController {

	private final DirectorService directorService;
	
	@Autowired
	public DirectorController(DirectorService directorService) {
		this.directorService = directorService;
	}
	
	@PostMapping
	public void insertDirector(@Valid @RequestBody Director director) {
		directorService.insertDirector(director);
	}
	
	@GetMapping
	public List<Director> getAllDirectors() {
		return directorService.getAllDirectors();
	}
	
	@GetMapping("search")
	public List<Director> getDirectorsWithFilmsByQuery(
			@RequestParam(value = "id", required = false) Integer id, 
			@RequestParam(value = "minReleaseDate", required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate minReleaseDate) {
		if (id == null) {
			if(minReleaseDate == null) {
				// if no query was passed
				return directorService.getAllDirectorsWithFilms();
			} else {
				// if only the date was passed
				return directorService.getDirectorsWithFilmsReleasedAfter(minReleaseDate);
			}
		} else if (minReleaseDate == null) {
			// if only the id was passed
			return new ArrayList<Director>(List.of(directorService.getDirectorByIdWithFilms(id).orElse(null)));
		} else {
			// if both id and date were passed
			return new ArrayList<Director>(List.of(directorService.getDirectorByIdWithFilmsReleasedAfter(id, minReleaseDate).orElse(null)));
		}
	}
	
	
	@GetMapping(path = "{id}")
	public Director getDirectorById(@PathVariable("id") int id) {
		return directorService.getDirectorById(id).orElse(null);
	}
	
	@DeleteMapping(path = "{id}")
	public void deleteDirectorById(@PathVariable("id") int id) {
		directorService.deleteDirector(id);
	}
	
	@PutMapping(path = "{id}")
	public void updateDirector(@PathVariable("id") int id, @Valid @NotNull @RequestBody Director director) {
		directorService.updateDirector(id, director);
	}
	
	
}
