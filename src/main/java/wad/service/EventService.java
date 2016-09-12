package wad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wad.domain.Event;
import wad.domain.Movie;
import wad.domain.MovieChoice;
import wad.repository.EventRepository;
import wad.repository.MovieRepository;
import wad.repository.MovieChoiceRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private MovieChoiceRepository movieChoiceRepository;
    

    public Event createEvent(String name, Date date) {
        Event event = new Event();
        event.setName(name);
        event.setDate(date);
        
        return eventRepository.save(event);
    }
    
    @Transactional
    public void deleteEvent(Long eventId) {
        Event event = eventRepository.findOne(eventId);
        if(event == null) {
            return;
        }
        
        for(MovieChoice movie : event.getMovies()) {
            movie.getMovie().removeChoice(movie);
        }
        
        List<MovieChoice> movies = movieChoiceRepository.findByEvent(event);
        for(MovieChoice movie : movies) {
            movieChoiceRepository.delete(movie.getId());
        }
        
        eventRepository.delete(eventId);
    }
    
    public Movie createMovie(String name, Integer length, String imdb) {
        Movie movie = new Movie();
        movie.setName(name);
        movie.setLengthInMinutes(length);
        movie.setImdbCode(imdb);
        
        return movieRepository.save(movie);
    }
    
    @Transactional
    public void deleteMovie(Long movieId) {
        Movie movie = movieRepository.findOne(movieId);
        if(movie == null) {
            return;
        }
        
        for(MovieChoice choice : movie.getChoices()) {
            choice.getEvent().removeMovie(choice);
        }
        
        List<MovieChoice> choices = movieChoiceRepository.findByMovie(movie);
        for(MovieChoice choice : choices) {
            movieChoiceRepository.delete(choice.getId());
        }
        
        movieRepository.delete(movieId);
    }
    
    
    @Transactional
    public void addMovieToEvent(Long eventId, Long movieId, String chosenBy) {
        Movie movie = movieRepository.findOne(movieId);
        Event event = eventRepository.findOne(eventId);
        
        MovieChoice addedMovie = new MovieChoice();
        addedMovie.setChosenBy(chosenBy);
        addedMovie.setMovie(movie);
        addedMovie.setEvent(event);
        
        movieChoiceRepository.save(addedMovie);
        event.addMovie(addedMovie);
        movie.addChoice(addedMovie);
    }
    
    
    
    
    @Transactional
    public void clearAll() {
        List<Long> events = getEventIds(eventRepository.findAll());
        List<Long> movies = getMovieIds(movieRepository.findAll());
        
        for (Event event : eventRepository.findAll()) {
            deleteEvent(event.getId());
        }
        
        for (Movie movie : movieRepository.findAll()) {
            deleteMovie(movie.getId());
        }
    }
    
    public List<Long> getEventIds(List<Event> events) {
        List<Long> ids = new ArrayList<>();
        for(Event event : events) {
            ids.add(event.getId());
        }
        return ids;
    }
    
    public List<Long> getMovieIds(List<Movie> movies) {
        List<Long> ids = new ArrayList<>();
        for(Movie movie : movies) {
            ids.add(movie.getId());
        }
        return ids;
    }
}
