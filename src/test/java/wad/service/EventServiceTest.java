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
        eventService.clearAll();
    }
    
    @Test
    public void testEventCreatedAndDeleted() {
        String name = "Firmans kurinpalautus";
        Date date = getRandomDate();
        GroupAccount group = groupRepository.save(new GroupAccount());
        eventService.createEvent(name, date, group);
        
        Event event = eventRepository.findByName(name);
        assertNotNull(event);
        
        eventService.deleteEvent(event.getId());
        
        assertNull(eventRepository.findByName(name));
        
    }
    
    @Test
    public void testMovieCreatedAndDeleted() {
        String name = "The Room";
        Integer length = 99;
        String imdb = "tt0368226";
        eventService.createMovie(name, length, imdb);
        
        Movie movie = movieRepository.findByName(name);
        assertNotNull(movie);
        
        eventService.deleteMovie(movie.getId());
        
        assertNull(movieRepository.findByName(name));
        
    }
    
    @Test
    public void testMovieAddedToEvent() {
        Event event = eventService.createEvent("Firmans kurinpalautus",
                getRandomDate(), groupRepository.save(new GroupAccount()));
        Movie movie = eventService.createMovie("The Room", 99, "tt0368226");
        
        assertNotNull(eventRepository.findByName("Firmans kurinpalautus"));
        assertNotNull(movieRepository.findByName("The Room"));
        assertEquals(0, movieChoiceRepository.findAll().size());
        
        eventService.addMovieToEvent(event.getId(), movie.getId(), "AF");
                
        assertEquals(1, eventRepository.findByName("Firmans kurinpalautus").getMovies().size());
        assertEquals(1, movieRepository.findByName("The Room").getChoices().size());
        assertEquals(1, movieChoiceRepository.findAll().size());
        
    }
    
}

