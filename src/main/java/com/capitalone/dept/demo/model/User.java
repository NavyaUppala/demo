package com.capitalone.dept.demo.model;

public class User {

	public String name;
	public String favorite_number;
	public String favorite_color;

	public User() {
	}

	public User(String name, String favorite_number, String favorite_color) {
		this.name = name;
		this.favorite_number = favorite_number;
		this.favorite_color = favorite_color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFavorite_number() {
		return favorite_number;
	}

	public void setFavorite_number(String favorite_number) {
		this.favorite_number = favorite_number;
	}

	public String getFavorite_color() {
		return favorite_color;
	}

	public void setFavorite_color(String favorite_color) {
		this.favorite_color = favorite_color;
	}

}