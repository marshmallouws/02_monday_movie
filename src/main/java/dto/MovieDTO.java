/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Movie;

/**
 *
 * @author Annika
 */
public class MovieDTO {
    private int id;
    private String name;
    private String[] actors;
    private int year;
    private String length;
    
    public MovieDTO(){}
    
    public MovieDTO(Movie movie) {
        id = movie.getId().intValue();
        name = movie.getName();
        actors = movie.getActors();
        year = movie.getYear();
        length = movie.getLength();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getActors() {
        return actors;
    }

    public int getYear() {
        return year;
    }

    public String getLength() {
        return length;
    }
}
