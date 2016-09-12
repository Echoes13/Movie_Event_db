package wad.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wad.repository.MovieRepository;
import wad.repository.EventRepository;
import wad.service.EventService;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    public MovieRepository movieRepository;
    
    @Autowired
    public EventRepository eventRepository;
    
    @Autowired
    public EventService eventService;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String listMovies(Model model) {
        model.addAttribute("movies", movieRepository.findAll());
        return "movies";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showMovie(Model model, @PathVariable Long id) {
        model.addAttribute("movie", movieRepository.findOne(id));
        model.addAttribute("events", eventRepository.findAll());
        return "movie";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String addMovie(@RequestParam String name,
            @RequestParam Integer length, @RequestParam String imdb) {
        eventService.createMovie(name, length, imdb);
        return "redirect:/movies";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteMovie(@PathVariable Long id) {
        eventService.deleteMovie(id);
        return "redirect:/movies";
    }
    
}
