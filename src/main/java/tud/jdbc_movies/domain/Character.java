package tud.jdbc_movies.domain;


public class Character {

	private long id;
	private String name;
	private String quality;
	private String type;
	private long movieFK;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getMovieFK() {
		return movieFK;
	}
	public void setMovieFK(long movieFK) {
		this.movieFK = movieFK;
	}
	
}