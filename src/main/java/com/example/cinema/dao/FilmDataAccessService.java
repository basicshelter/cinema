package com.example.cinema.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.cinema.exception.FilmNotFoundException;
import com.example.cinema.model.Film;

@Repository("film-postgres")
public class FilmDataAccessService implements FilmDao {
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public FilmDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public int insertFilm(Film film) {
		final String sql = ""
				 + "INSERT INTO Film ("
				 + " director_id, "
				 + " name, "
				 + " release_date,"
				 + " genre) "
				 + "VALUES (?, ?, ?, ?)";
       return jdbcTemplate.update(
               sql,
               film.getDirectorId(),
               film.getName(),
               film.getReleaseDate(),
               film.getGenre()
       );
	}

	@Override
	public List<Film> selectAllFilms() {
		final String sql = "select * from Film";
		return jdbcTemplate.query(sql, (resultSet, i) -> 
			mapFilmFull(resultSet)
		);
	}

	@Override
	public Optional<Film> selectFilmById(int id) {
		final String sql = "SELECT * FROM Film WHERE id = ?";
		Film film;
		try {
			film = jdbcTemplate.queryForObject(sql, new Object[] {id}, (resultSet, i) -> 
				mapFilmPart(resultSet)
			);
		} catch (DataAccessException e) {
			throw new FilmNotFoundException(id);
		}
		return Optional.ofNullable(film);
	}

	@Override
	public int deleteFilmById(int id) {
		final String sql = "DELETE FROM Film WHERE id = ?";
		return jdbcTemplate.update(sql, id);
	}

	@Override
	public int updateFilmById(int id, Film film) {
		final String sql = ""
				 + "UPDATE Film "
				 + "SET director_id = ?, "
				 + "name = ?, "
				 + "release_date = ?,"
				 + "genre = ? "
				 + "WHERE id = ?";
		return jdbcTemplate.update(
               sql,
               film.getDirectorId(),
               film.getName(),
               film.getReleaseDate(),
               film.getGenre(),
               id
       );
	}

	public static Film mapFilmPart(ResultSet resultSet) throws SQLException {
		String name = resultSet.getString("name");
		LocalDate releaseDate = resultSet.getDate("release_date").toLocalDate();
		String genre = resultSet.getString("genre");
		return new Film(name, releaseDate, genre);
	}
	
	public static Film mapFilmFull(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("id");
		int directorId = resultSet.getInt("director_id");
		String name = resultSet.getString("name");
		LocalDate releaseDate = resultSet.getDate("release_date").toLocalDate();
		String genre = resultSet.getString("genre");
		return new Film (id, directorId, name, releaseDate, genre);
	}
}
