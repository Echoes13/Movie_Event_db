package wad.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.springframework.data.jpa.domain.AbstractPersistable;

//Luokka ryhmän käyttäjätilille. Käyttäjätiliin liittyy nollasta useampaan
//tapahtumaa.

@Entity
public class GroupAccount extends AbstractPersistable<Long> {

    @Column(unique=true)
    private String name;
    
    @Column(unique=true)
    private String username;
    
    private String password;
    
    @OneToMany(mappedBy="group", fetch = FetchType.EAGER)
    private List<Event> events;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Event> getEvents() {
        return events;
    }
    
    public void setEvents(List<Event> events) {
        this.events = events;
    }
    
    public void addEvent(Event event) {
        if (this.events == null) {
            this.events = new ArrayList<Event>();
        }
        this.events.add(event);
    }
}