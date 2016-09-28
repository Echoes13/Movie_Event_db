package wad.service;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wad.domain.Event;
import wad.domain.GroupAccount;
import wad.domain.Movie;
import wad.repository.EventRepository;
import wad.repository.GroupRepository;
import wad.repository.MovieChoiceRepository;
import wad.repository.MovieRepository;
import static wad.util.DateTestUtils.getRandomDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventServiceTest {
        
    @Autowired
    private EventService eventService;
    
    @Autowired
    private GroupRepository groupRepository;
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private MovieChoiceRepository movieChoiceRepository;
    
    
    @Before
    public void init() {
    }
    
    @Test
    public void testEventCreatedAndDeleted() {
        Event event = createEvent();
        assertNotNull(event);
        
        eventService.deleteEvent(event.getId());
        
        assertNull(eventRepository.findByName(event.getName()));
        
    }
    
    @Test
    public void testMovieCreatedAndDeleted() {
        Movie movie = createMovie();
        assertNotNull(movie);
        
        eventService.deleteMovie(movie.getId());
        
        assertNull(movieRepository.findByName(movie.getName()));
        
    }
    
    @Test
    public void testMovieAddedToEvent() {
        Event event = createEvent();
        Movie movie = createMovie();
        
        assertNotNull(eventRepository.findByName("Firmans kurinpalautus"));
        assertNotNull(movieRepository.findByName("The Room"));
        assertEquals(0, movieChoiceRepository.findAll().size());
        
        eventService.addMovieToEvent(event.getId(), movie.getId(), "AF");
                
        assertEquals(1, eventRepository.findByName("Firmans kurinpalautus").getMovies().size());
        assertEquals(1, movieRepository.findByName("The Room").getChoices().size());
        assertEquals(1, movieChoiceRepository.findAll().size());
        
        eventService.deleteEvent(event.getId());
        eventService.deleteMovie(movie.getId());
        
        assertNull(eventRepository.findByName("Firmans kurinpalautus"));
        assertNull(movieRepository.findByName("The Room"));
        
    }
    
    public Event createEvent() {
        String name = "Firmans kurinpalautus";
        Date date = getRandomDate();
        GroupAccount group = groupRepository.save(new GroupAccount());
        return eventService.createEvent(name, date, group);
    }
    
    public Movie createMovie() {
        String name = "The Room";
        Integer length = 99;
        String imdb = "tt0368226";
        return eventService.createMovie(name, length, imdb);
    }
}

