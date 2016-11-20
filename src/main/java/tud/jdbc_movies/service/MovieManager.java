package tud.jdbc_movies.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tud.jdbc_movies.domain.Movie;


public class MovieManager {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTableMovie = "CREATE TABLE Movie(id bigint GENERATED BY DEFAULT AS IDENTITY UNIQUE,"
			+ "title VARCHAR(50), country VARCHAR(50), production VARCHAR(150), year INTEGER)";
	
	private PreparedStatement addMovieStmt;
	private PreparedStatement deleteMovieStmt;
	private PreparedStatement editMovieStmt;
	private PreparedStatement deleteAllMoviesStmt;
	private PreparedStatement getAllMoviesStmt;
	private static PreparedStatement getMovieStmt;
	
	private Statement statement;
	
	public MovieManager() {
		
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,null);
			boolean tableExists = false;
			
			while (rs.next()) {
				if ("Movie".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists){
				statement.executeUpdate(createTableMovie);
			}
			
			addMovieStmt = connection
					.prepareStatement("INSERT INTO Movie (title, country, production, year) "
							+ "VALUES (?, ?, ?, ?)");
			deleteMovieStmt = connection
					.prepareStatement("DELETE FROM Movie where id=?");
			editMovieStmt = connection
					.prepareStatement("UPDATE Movie SET title=?, country=?, production=?, year=?");
			deleteAllMoviesStmt = connection
					.prepareStatement("DELETE FROM Movie");
			getAllMoviesStmt = connection
					.prepareStatement("SELECT id, title, country, production, year FROM Movie");
			getMovieStmt = connection
					.prepareStatement("SELECT id, title, country, production, year FROM Movie "
							+ "WHERE id=?");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	public Connection getConnection() {
		return connection;
	}
	
	public int addMovie(Movie movie) {
		int count = 0;
		
		try {
			addMovieStmt.setString(1, movie.getTitle());
			addMovieStmt.setString(2, movie.getCountry());
			addMovieStmt.setString(3, movie.getProduction());
			addMovieStmt.setInt(4, movie.getYear());

			count = addMovieStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;		
	}
	
	public int deleteMovie(int id) {		
		int count = 0;
		
		try {
			deleteMovieStmt.setInt(1, id);
			count = deleteMovieStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;	
	}
	
	public int editMovie(Movie movie) {
		int count = 0;
		
		try {
			editMovieStmt.setString(1, movie.getTitle());
			editMovieStmt.setString(2, movie.getCountry());
			editMovieStmt.setString(3, movie.getProduction());
			editMovieStmt.setInt(4, movie.getYear());

			count = editMovieStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void deleteAllMovies() {
		try {
			deleteAllMoviesStmt.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Movie> getAllMovies() {
		List<Movie> movies = new ArrayList<Movie>();

		try {
			ResultSet rs = getAllMoviesStmt.executeQuery();

			while (rs.next()) {
				Movie m = new Movie();
				m.setId(rs.getInt("id"));
				m.setTitle(rs.getString("title"));
				m.setCountry(rs.getString("country"));
				m.setProduction(rs.getString("production"));
				m.setYear(rs.getInt("year"));
				movies.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movies;
	}
	
	public Movie getMovie(int id) {
		Movie m = new Movie();

		try {
			getMovieStmt.setInt(1, id);
			ResultSet rs = getMovieStmt.executeQuery();
			while (rs.next()) {		
				
				m.setId(rs.getInt("id"));
				m.setTitle(rs.getString("title"));
				m.setCountry(rs.getString("country"));
				m.setProduction(rs.getString("production"));
				m.setYear(rs.getInt("year"));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return m;
	}
	
	
}
