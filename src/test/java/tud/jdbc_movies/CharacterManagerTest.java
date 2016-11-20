package tud.jdbc_movies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import tud.jdbc_movies.domain.Movie;
import tud.jdbc_movies.domain.Character;
import tud.jdbc_movies.service.CharacterManager;
import tud.jdbc_movies.service.MovieManager;

public class CharacterManagerTest {

	CharacterManager characterManager = new CharacterManager();
	MovieManager movieManager = new MovieManager();
	
	private final static String NAME_1 = "Elsa";
	private final static String QUALITY_1 = "panowanie nad zima";
	private final static String TYPE_1 = "Kobieta";
	
	private final static String NAME_2 = "Olaf";
	private final static String QUALITY_2 = "poruszanie sie, mowienie";
	private final static String TYPE_2 = "Balwan";
	
	private final static String NAME_3 = "Krol Julian";
	private final static String QUALITY_3 = "doskonalosc";
	private final static String TYPE_3 = "Lemur";
	
	private final static String NAME_4 = "Szeregowy";
	private final static String QUALITY_4 = "uber-slodycz";
	private final static String TYPE_4 = "Pingwin";
	
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
		assertNotNull(characterManager.getConnection());
	}
	
	@Test
	public void checkAdd(){
		
		Movie movie = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		
		movieManager.deleteAllMovies();
		characterManager.deleteAllCharacters();
		
		assertEquals(1,movieManager.addMovie(movie));
		
		List<Movie> movies = movieManager.getAllMovies();
		int movieID = movies.get(0).getId();
		
		Character character = new Character(NAME_1, QUALITY_1, TYPE_1, movieID);
		
		assertEquals(1,characterManager.addCharacter(character));
		
		List<Character> characters = characterManager.getAllCharacters();
		Character characterRetrieved = characters.get(0);
		
		assertEquals(NAME_1, characterRetrieved.getName());
		assertEquals(QUALITY_1, characterRetrieved.getQuality());
		assertEquals(TYPE_1, characterRetrieved.getType());
		assertEquals(movieID, characterRetrieved.getMovieFK());
		
	}
	
	@Test
	public void checkDelete(){
		
		Movie movie = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		
		movieManager.deleteAllMovies();
		characterManager.deleteAllCharacters();
		
		assertEquals(1,movieManager.addMovie(movie));
		
		List<Movie> movies = movieManager.getAllMovies();
		int movieID = movies.get(0).getId();
		
		Character character1 = new Character(NAME_1, QUALITY_1, TYPE_1, movieID);
		Character character2 = new Character(NAME_2, QUALITY_2, TYPE_2, movieID);
		
		assertEquals(1,characterManager.addCharacter(character1));
		assertEquals(1,characterManager.addCharacter(character2));
		
		List<Character> characters = characterManager.getAllCharacters();
		int deletedID = characters.get(0).getId();
		int delete = characterManager.deleteCharacter(deletedID);
		assertEquals(1, delete);
		
		characters = characterManager.getAllCharacters();
		
		Character characterRetrieved = characters.get(0);		
		
		assertEquals(NAME_2, characterRetrieved.getName());
		assertEquals(QUALITY_2, characterRetrieved.getQuality());
		assertEquals(TYPE_2, characterRetrieved.getType());
		assertEquals(movieID, characterRetrieved.getMovieFK());
		
	}
	
	@Test
	public void checkEdit(){
		
		Movie movie1 = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		Movie movie2 = new Movie(TITLE_2, COUNTRY_2,PRODUCTION_2,YEAR_2);
		
		movieManager.deleteAllMovies();
		characterManager.deleteAllCharacters();
		
		assertEquals(1,movieManager.addMovie(movie1));
		assertEquals(1,movieManager.addMovie(movie2));
		
		List<Movie> movies = movieManager.getAllMovies();
		int movie1ID = movies.get(0).getId();
		int movie2ID = movies.get(1).getId();
		
		Character character = new Character(NAME_1, QUALITY_1, TYPE_1, movie1ID);
		Character character_edit = new Character(NAME_3, QUALITY_3, TYPE_3, movie2ID);
		
		assertEquals(1,characterManager.addCharacter(character));
		assertEquals(1,characterManager.editCharacter(character_edit));
		
		List<Character> characters = characterManager.getAllCharacters();
		Character characterRetrieved = characters.get(0);
		
		assertEquals(NAME_3, characterRetrieved.getName());
		assertEquals(QUALITY_3, characterRetrieved.getQuality());
		assertEquals(TYPE_3, characterRetrieved.getType());
		assertEquals(movie2ID, characterRetrieved.getMovieFK());
		
	}
	
	@Test
	public void checkGetAll(){
		

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
		Character character2 = new Character(NAME_2, QUALITY_2, TYPE_2, movie1ID);
		Character character3 = new Character(NAME_3, QUALITY_3, TYPE_3, movie2ID);
		Character character4 = new Character(NAME_4, QUALITY_4, TYPE_4, movie2ID);
		
		assertEquals(1,characterManager.addCharacter(character1));
		assertEquals(1,characterManager.addCharacter(character2));
		assertEquals(1,characterManager.addCharacter(character3));
		assertEquals(1,characterManager.addCharacter(character4));
		
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
		assertEquals(movie1ID, characterRetrieved.getMovieFK());
		
		characterRetrieved = characters.get(2);
		assertEquals(NAME_3, characterRetrieved.getName());
		assertEquals(QUALITY_3, characterRetrieved.getQuality());
		assertEquals(TYPE_3, characterRetrieved.getType());
		assertEquals(movie2ID, characterRetrieved.getMovieFK());
		
		characterRetrieved = characters.get(3);
		assertEquals(NAME_4, characterRetrieved.getName());
		assertEquals(QUALITY_4, characterRetrieved.getQuality());
		assertEquals(TYPE_4, characterRetrieved.getType());
		assertEquals(movie2ID, characterRetrieved.getMovieFK());
		
	}
	
	@Test
	public void checkGetOne(){
		
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
		Character character2 = new Character(NAME_2, QUALITY_2, TYPE_2, movie1ID);
		Character character3 = new Character(NAME_3, QUALITY_3, TYPE_3, movie2ID);
		Character character4 = new Character(NAME_4, QUALITY_4, TYPE_4, movie2ID);
		
		assertEquals(1,characterManager.addCharacter(character1));
		assertEquals(1,characterManager.addCharacter(character2));
		assertEquals(1,characterManager.addCharacter(character3));
		assertEquals(1,characterManager.addCharacter(character4));
		
		List<Character> characters = characterManager.getAllCharacters();
		characters = characterManager.getAllCharacters();
		
		Character characterRetrieved = characters.get(2);
		assertEquals(NAME_3, characterRetrieved.getName());
		assertEquals(QUALITY_3, characterRetrieved.getQuality());
		assertEquals(TYPE_3, characterRetrieved.getType());
		assertEquals(movie2ID, characterRetrieved.getMovieFK());
		
	}
	
	@Test
	public void checkNotExist(){

		Movie movie = new Movie(TITLE_1, COUNTRY_1,PRODUCTION_1,YEAR_1);
		
		movieManager.deleteAllMovies();
		characterManager.deleteAllCharacters();
		
		assertEquals(1,movieManager.addMovie(movie));
		
		List<Movie> movies = movieManager.getAllMovies();
		int movieID = movies.get(0).getId();

		Character character = new Character(NAME_1, QUALITY_1, TYPE_1, movieID);
		assertEquals(1,characterManager.addCharacter(character));
		
		Character getCharacter = characterManager.getCharacter(29);
		
		assertEquals(null, getCharacter.getName());
		assertEquals(null, getCharacter.getQuality());
		assertEquals(null, getCharacter.getType());
		assertEquals(0, getCharacter.getMovieFK());
		
	} 
	
	@Test
	public void getCharactersByMovie(){
		
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
		Character character2 = new Character(NAME_2, QUALITY_2, TYPE_2, movie1ID);
		Character character3 = new Character(NAME_3, QUALITY_3, TYPE_3, movie2ID);
		Character character4 = new Character(NAME_4, QUALITY_4, TYPE_4, movie2ID);
		
		assertEquals(1,characterManager.addCharacter(character1));
		assertEquals(1,characterManager.addCharacter(character3));
		assertEquals(1,characterManager.addCharacter(character4));
		assertEquals(1,characterManager.addCharacter(character2));
		
		List<Character> characters = characterManager.getAllCharacters();
		
		Character characterRetrieved = characters.get(0);
		assertEquals(NAME_1, characterRetrieved.getName());
		assertEquals(QUALITY_1, characterRetrieved.getQuality());
		assertEquals(TYPE_1, characterRetrieved.getType());
		assertEquals(movie1ID, characterRetrieved.getMovieFK());
		
		characterRetrieved = characters.get(1);
		assertEquals(NAME_3, characterRetrieved.getName());
		assertEquals(QUALITY_3, characterRetrieved.getQuality());
		assertEquals(TYPE_3, characterRetrieved.getType());
		assertEquals(movie2ID, characterRetrieved.getMovieFK());
		
		characterRetrieved = characters.get(2);
		assertEquals(NAME_4, characterRetrieved.getName());
		assertEquals(QUALITY_4, characterRetrieved.getQuality());
		assertEquals(TYPE_4, characterRetrieved.getType());
		assertEquals(movie2ID, characterRetrieved.getMovieFK());
		
		characterRetrieved = characters.get(3);
		assertEquals(NAME_2, characterRetrieved.getName());
		assertEquals(QUALITY_2, characterRetrieved.getQuality());
		assertEquals(TYPE_2, characterRetrieved.getType());
		assertEquals(movie1ID, characterRetrieved.getMovieFK());
		
		List<Character> charactersByMovie = characterManager.getAllCharactersByMovie(movie1ID);
		
		Character characterRetrieved2 = charactersByMovie.get(0);
		assertEquals(NAME_1, characterRetrieved2.getName());
		assertEquals(QUALITY_1, characterRetrieved2.getQuality());
		assertEquals(TYPE_1, characterRetrieved2.getType());
		assertEquals(movie1ID, characterRetrieved2.getMovieFK());
		
		characterRetrieved2 = charactersByMovie.get(1);
		assertEquals(NAME_2, characterRetrieved2.getName());
		assertEquals(QUALITY_2, characterRetrieved2.getQuality());
		assertEquals(TYPE_2, characterRetrieved2.getType());
		assertEquals(movie1ID, characterRetrieved2.getMovieFK());
		
	}
	
}
