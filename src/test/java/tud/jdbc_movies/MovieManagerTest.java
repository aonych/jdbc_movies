package tud.jdbc_movies;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import tud.jdbc_movies.domain.Movie;
import tud.jdbc_movies.service.MovieManager;

public class MovieManagerTest {
	
	MovieManager movieManager = new MovieManager();
	
	private final static String TITLE_1 = "Frozen";
	private final static String COUNTRY_1 = "USA";
	private final static String PRODUCTION_1 = "Walt Disney Pictures";
	private final static int YEAR_1 = 2013;
	
	private final static String TITLE_2 = "Madagascar";
	private final static String COUNTRY_2 = "USA";
	private final static String PRODUCTION_2 = "DreamWorks";
	private final static int YEAR_2 = 2005; 
	
	@Test
	public void checkConnection(){
		assertNotNull(movieManager.getConnection());
	}
	
	@Test
	public void checkAdd(){
		
		Movie movie = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		
		movieManager.deleteAllMovies();
		assertEquals(1,movieManager.addMovie(movie));
		
		List<Movie> movies = movieManager.getAllMovies();
		Movie movieRetrieved = movies.get(0);
		
		assertEquals(TITLE_1, movieRetrieved.getTitle());
		assertEquals(COUNTRY_1, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_1, movieRetrieved.getProduction());
		assertEquals(YEAR_1, movieRetrieved.getYear());
		
	}
	
	@Test
	public void checkDelete(){
		
		Movie movie1 = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		Movie movie2 = new Movie(TITLE_2, COUNTRY_2,PRODUCTION_2,YEAR_2);
		
		movieManager.deleteAllMovies();
		assertEquals(1,movieManager.addMovie(movie1));
		assertEquals(1,movieManager.addMovie(movie2));
		
		List<Movie> movies = movieManager.getAllMovies();
		long deletedID = movies.get(0).getId();
		int delete = movieManager.deleteMovie(deletedID);
		assertEquals(1, delete);
		
		movies = movieManager.getAllMovies();
		
		Movie movieRetrieved = movies.get(0);
		
		assertEquals(TITLE_2, movieRetrieved.getTitle());
		assertEquals(COUNTRY_2, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_2, movieRetrieved.getProduction());
		assertEquals(YEAR_2, movieRetrieved.getYear());
		
	}
	
	@Test
	public void checkEdit(){
		
		Movie movie1 = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		Movie movie_edit = new Movie(TITLE_2, COUNTRY_2,PRODUCTION_2,YEAR_2);
		
		movieManager.deleteAllMovies();
		assertEquals(1,movieManager.addMovie(movie1));
		assertEquals(1,movieManager.editMovie(movie_edit));
		
		List<Movie> movies = movieManager.getAllMovies();
		Movie movieRetrieved = movies.get(0);
		
		assertEquals(TITLE_2, movieRetrieved.getTitle());
		assertEquals(COUNTRY_2, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_2, movieRetrieved.getProduction());
		assertEquals(YEAR_2, movieRetrieved.getYear());
		
	}
	
	@Test
	public void checkGetAll(){
		
		Movie movie1 = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		Movie movie2 = new Movie(TITLE_2, COUNTRY_2,PRODUCTION_2,YEAR_2);
		
		movieManager.deleteAllMovies();
		assertEquals(1,movieManager.addMovie(movie1));
		assertEquals(1,movieManager.addMovie(movie2));
		
		List<Movie> movies = movieManager.getAllMovies();		
		movies = movieManager.getAllMovies();
		
		Movie movieRetrieved = movies.get(0);
		assertEquals(TITLE_1, movieRetrieved.getTitle());
		assertEquals(COUNTRY_1, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_1, movieRetrieved.getProduction());
		assertEquals(YEAR_1, movieRetrieved.getYear());
		
		movieRetrieved = movies.get(1);
		assertEquals(TITLE_2, movieRetrieved.getTitle());
		assertEquals(COUNTRY_2, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_2, movieRetrieved.getProduction());
		assertEquals(YEAR_2, movieRetrieved.getYear());
		
	}
	
	@Test
	public void checkGetOne(){
		
		Movie movie1 = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		Movie movie2 = new Movie(TITLE_2, COUNTRY_2,PRODUCTION_2,YEAR_2);
		
		movieManager.deleteAllMovies();
		assertEquals(1,movieManager.addMovie(movie1));
		assertEquals(1,movieManager.addMovie(movie2));
		
		List<Movie> movies = movieManager.getAllMovies();		
		movies = movieManager.getAllMovies();
		
		Movie movieRetrieved = movies.get(1);
		assertEquals(TITLE_2, movieRetrieved.getTitle());
		assertEquals(COUNTRY_2, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_2, movieRetrieved.getProduction());
		assertEquals(YEAR_2, movieRetrieved.getYear());
	}
	
	
	
}
