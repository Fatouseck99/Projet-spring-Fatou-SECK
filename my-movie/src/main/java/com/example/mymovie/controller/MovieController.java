package com.example.mymovie.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.mymovie.model.Acteur;
import com.example.mymovie.model.Film;
import com.example.mymovie.model.Date;
@Api(value = "MovieController", description = "REST Apis related to Movies Entity!!!!")
@RestController
public class MovieController {

	private static Map<String, Film> films = new HashMap<String, Film>();
	private static Map<String, Acteur> Acteurs = new HashMap<String, Acteur>();

	static {
		Acteur a1 = new Acteur("Christian", "Bale", new Date(30, 1, 1974));
		Acteur a2 = new Acteur("Harry", "Melling", new Date(9, 8, 1968));
		Acteur a3 = new Acteur("Sylvester", "Stallone", new Date(6, 7, 1946));
		Acteur a4 = new Acteur("Pilou", "Asbaek", new Date(2, 3, 1982));

		Film f1 = new Film("The Pale Blue Eye", "Scott Cooper", a1, new Date(1, 5, 2022));
		Film f2 = new Film("Le Samaritain", "Julius Avery", a3, new Date(31, 1, 2022));

		a2.addFilm(f1);
		a4.addFilm(f2);

		films.put(f1.gettitre(), f1);
		films.put(f1.gettitre(), f2);

		Acteurs.put(a1.getFirstName() + " " + a1.getLastName(), a1);
		Acteurs.put(a2.getFirstName() + " " + a2.getLastName(), a1);
		Acteurs.put(a3.getFirstName() + " " + a3.getLastName(), a1);
		Acteurs.put(a4.getFirstName() + " " + a4.getLastName(), a1);
	}
	@ApiOperation(value = "Get list of actors by film ", response = Iterable.class, tags = "getActorsByFilm")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })

	@RequestMapping(value = "/getAllActeurs", method = RequestMethod.GET)
	public List<Acteur> getAllActeurs() {
		System.out.println("Getting All Acteurs");

		List<Acteur> allActeurs = new ArrayList<Acteur>(Acteurs.values());
		return allActeurs;
	}

	@RequestMapping(value = "/getActeurByName/{name}", method = RequestMethod.GET)
	public Acteur getActeurByName(@PathVariable String name) {
		System.out.println("Getting Acteur " + name);
		return Acteurs.get(name);
	}

	@RequestMapping(value = "/getActeursByFilm", method = RequestMethod.GET)
	public List<Acteur> getActeursByFilm(@PathVariable String title) {
		System.out.println("Getting Acteurs by film title " + title);
		Collection<Acteur> c1 = Acteurs.values();
		List<Acteur> result = new ArrayList<Acteur>();
		for (Acteur a : c1) {
			if (a.inFilmography(title)) result.add(a);
		}
		return result;
	}

	@RequestMapping(value = "/getAllFilms", method = RequestMethod.GET)
	public List<Film> getAllFilms() {
		System.out.println("Getting All Films");

		List<Film> allFilms = new ArrayList<Film>(films.values());
		return allFilms;
	}

	@RequestMapping(value = "/getFilmByTitle/{name}", method = RequestMethod.GET)
	public Film getFilmByTitle(@PathVariable String title) {
		System.out.println("Getting Film " + title);
		return films.get(title);
	}

	@RequestMapping(value = "/getFilmsByReleaseYear", method = RequestMethod.GET)
	public List<Film> getFilmsByReleaseYear(@PathVariable int year) {
		System.out.println("Getting Films by release year " + year);
		Collection<Film> c1 = films.values();
		List<Film> result = new ArrayList<Film>();
		for (Film f : c1) {
			if (f.getdateDeSortie().getYear() == year) result.add(f);
		}
		return result;
	}
}