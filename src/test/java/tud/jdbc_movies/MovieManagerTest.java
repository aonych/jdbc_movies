package tud.jdbc_movies;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import tud.jdbc_movies.domain.Character;
import tud.jdbc_movies.domain.Movie;
import tud.jdbc_movies.service.CharacterManager;
import tud.jdbc_movies.service.MovieManager;

public class MovieManagerTest {
	
	MovieManager movieManager = new MovieManager();
	CharacterManager characterManager = new CharacterManager();
	
	private final static String TITLE_1 = "Frozen";
	private final static String COUNTRY_1 = "USA";
	private final static String PRODUCTION_1 = "Walt Disney Pictures";
	private final static int YEAR_1 = 2013;
	
	private final static String TITLE_2 = "Madagascar";
	private final static String COUNTRY_2 = "USA";
	private final static String PRODUCTION_2 = "DreamWorks";
	private final static int YEAR_2 = 2005; 
	
	private final static String NAME_1 = "Elsa";
	private final static String QUALITY_1 = "panowanie nad zima";
	private final static String TYPE_1 = "Kobieta";
	
	private final static String NAME_2 = "Krol Julian";
	private final static String QUALITY_2 = "doskonalosc";
	private final static String TYPE_2 = "Lemur";
	
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
		int deletedID = movies.get(0).getId();
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
	
	
	
	@Test
	public void checkNotExist(){

		Movie movie = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		
		movieManager.deleteAllMovies();
		assertEquals(1,movieManager.addMovie(movie));	
		
		Movie getMovie = movieManager.getMovie(19);
		
		assertEquals(null, getMovie.getTitle());
		assertEquals(null, getMovie.getCountry());
		assertEquals(null, getMovie.getProduction());
		assertEquals(0, getMovie.getYear());
		
	} 
	
	@Test
	public void checkDeleteWithCharacters(){
		
		Movie movie1 = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		Movie movie2 = new Movie(TITLE_2, COUNTRY_2,PRODUCTION_2,YEAR_2);
		
		movieManager.deleteAllMovies();
		characterManager.deleteAllCharacters();
		
		assertEquals(1,movieManager.addMovie(movie1));
		assertEquals(1,movieManager.addMovie(movie2));
		
		List<Movie> movies = movieManager.getAllMovies();
		int movie1ID = movies.get(0).getId();
		int movie2ID = movies.get(1).getId();
		
		Character character1 = new Character(NAME_1, QUALITY_1, TYPE_1, movie1ID);		
		Character character2 = new Character(NAME_2, QUALITY_2, TYPE_2, movie2ID);
		assertEquals(1,characterManager.addCharacter(character1));
		assertEquals(1,characterManager.addCharacter(character2));
		
		List<Character> characters = characterManager.getAllCharacters();
		
		Character characterRetrieved = characters.get(0);
		assertEquals(NAME_1, characterRetrieved.getName());
		assertEquals(QUALITY_1, characterRetrieved.getQuality());
		assertEquals(TYPE_1, characterRetrieved.getType());
		assertEquals(movie1ID, characterRetrieved.getMovieFK());
		
		characterRetrieved = characters.get(1);
		assertEquals(NAME_2, characterRetrieved.getName());
		assertEquals(QUALITY_2, characterRetrieved.getQuality());
		assertEquals(TYPE_2, characterRetrieved.getType());
		assertEquals(movie2ID, characterRetrieved.getMovieFK());
		
		int delete = movieManager.deleteMovie(movie1ID);
		assertEquals(1, delete);
		
		movies = movieManager.getAllMovies();
		
		Movie movieRetrieved = movies.get(0);		
		assertEquals(TITLE_2, movieRetrieved.getTitle());
		assertEquals(COUNTRY_2, movieRetrieved.getCountry());
		assertEquals(PRODUCTION_2, movieRetrieved.getProduction());
		assertEquals(YEAR_2, movieRetrieved.getYear());
		
		
		characters = characterManager.getAllCharacters();
		characterRetrieved = characters.get(0);
		assertEquals(NAME_2, characterRetrieved.getName());
		assertEquals(QUALITY_2, characterRetrieved.getQuality());
		assertEquals(TYPE_2, characterRetrieved.getType());
		assertEquals(movie2ID, characterRetrieved.getMovieFK());
		
	}
		
}
