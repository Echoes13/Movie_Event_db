package wad.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.repository.EventRepository;
import wad.repository.MovieRepository;
import wad.service.EventService;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    public EventRepository eventRepository;
    
    @Autowired
    public MovieRepository movieRepository;
    
    @Autowired
    public EventService eventService;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String listEvents(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "events";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showEvent(Model model, @PathVariable Long id) {
        model.addAttribute("event", eventRepository.findOne(id));
        model.addAttribute("movies", movieRepository.findAll());
        return "event";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String addEvent(@RequestParam String name,
            @RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date date) {
        eventService.createEvent(name, date);
        return "redirect:/events";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return "redirect:/events";
    }
    
    @RequestMapping(value = "/{eventId}/movies", method = RequestMethod.POST)
    public String addMovieToEvent(Model model, 
            @RequestParam(value = "chosenBy") String chosenBy,
            @RequestParam(value = "movieId") Long movieId, 
            @PathVariable(value = "actorId") Long eventId) {
        eventService.addMovieToEvent(eventId, movieId, chosenBy);
        return "redirect:/events/{eventId}";
    }
    
}
