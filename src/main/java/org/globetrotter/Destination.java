package org.globetrotter;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "destination", schema = "globetrotter")
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String location;
    private String country;
    private String description;
    private int rating;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private ArrayList<String> images;


    public Destination() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public Destination(String location, String country, String description, int rating, LocalDate arrivalDate, LocalDate departureDate, List<String> images) {
        this.location = location;
        this.country = country;
        this.description = description;
        this.rating = rating;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.images = (ArrayList<String>) images;
    }


}
