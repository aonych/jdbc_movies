package tud.jdbc_movies.domain;


public class Movie {

	private int id;
	private String title;
	private String country;
	private String production;
	private int year;
	
	public Movie() {
	}
	
	public Movie(String title, String country, String production, int year) {
		super();
		this.title = title;
		this.country = country;
		this.production = production;
		this.year = year;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProduction() {
		return production;
	}
	public void setProduction(String production) {
		this.production = production;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
}