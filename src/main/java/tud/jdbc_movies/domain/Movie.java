package tud.jdbc_movies.domain;


public class Movie {

	private long id;
	private String title;
	private String country;
	private String production;
	private int year;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public long getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
}