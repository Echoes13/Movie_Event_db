package eventdb.service;

import java.util.List;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import eventdb.domain.Event;
import eventdb.domain.Movie;
import eventdb.domain.MovieChoice;
import eventdb.repository.EventRepository;
import eventdb.repository.MovieRepository;
import eventdb.repository.MovieChoiceRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private MovieChoiceRepository movieChoiceRepository;
    

    public void createEvent(String name, Date date) {
        Event event = new Event();
        event.setName(name);
        event.setDate(date);
        
        eventRepository.save(event);
    }
    
    public void deleteEvent(Long eventId) {
        Event event = eventRepository.findOne(eventId);
        if(event == null) {
            return;
        }
        
        List<MovieChoice> movies = movieChoiceRepository.findByEvent(event);
        for(MovieChoice movie : movies) {
            movieChoiceRepository.delete(movie.getId());
        }
        
        eventRepository.delete(eventId);
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
}
