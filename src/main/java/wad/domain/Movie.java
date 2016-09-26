package wad.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Movie extends AbstractPersistable<Long> {

    private String name;
    
    private Integer lengthInMinutes;
    
    private String imdbCode;
    
    @OneToMany(mappedBy="movie", fetch = FetchType.EAGER)
    private List<MovieChoice> choices;

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(Integer lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }
    
    public String getImdbCode() {
        return imdbCode;
    }

    public void setImdbCode(String imdbCode) {
        this.imdbCode = imdbCode;
    }
    
    public void addChoice(MovieChoice choice) {
        if (this.choices == null) {
            this.choices = new ArrayList<MovieChoice>();
        }
 
        this.choices.add(choice);
    }
    
    public void removeChoice(MovieChoice choice) {
        if (!this.choices.contains(choice)) {
            return;            
        }
        this.choices.remove(choice);
    }
 
    public List<MovieChoice> getChoices() {
        return choices;
    }
 
    public void setChoices(List<MovieChoice> choices) {
        this.choices = choices;
    }
    
}