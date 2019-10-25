package com.javagists.jerseyfilms.service;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

import com.javagists.jerseyfilms.model.Film;

@Service
public class FilmService {
	private final ConcurrentMap<String, Film> db;
	
	public FilmService() {
		this.db = new ConcurrentHashMap<>();
	}
	
	public Collection<Film> getAllFilms() {
		Collection<Film> all = this.db.values();
		if(all.isEmpty()) {
			return Collections.emptyList();
		} else {
			return all;
		}
	}
	
	public void addFilm(Film f) {
		if(f.getId()==null) {
			f.setId(String.valueOf(this.db.size()+1));
		}
		this.db.put(f.getId(), f);
	}
	
	public Film getFilm(String id) {
		return this.db.get(id);
	}
	
	public Film updateFilm(String id, Film f) {
		if(!this.db.containsKey(id)) {
			throw new IllegalArgumentException("Invalid Film or Fild does not exist!");
		}
		if(f.getId()==null|| id!=f.getId()) {
			f.setId(id);
		}
		return this.db.put(id, f);
	}
	
	public void removeFile(String id) {
		if(!this.db.containsKey(id)) {
			throw new IllegalArgumentException("Invalid Film or Film does not exist!");
		}
		this.db.remove(id);
	}
}
