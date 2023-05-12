package org.globetrotter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destinations")
public class DestinationController {

    private final DestinationService destinationService;
    private final com.example.services.ImageStorageService imageStorageService;

    @Autowired
    public DestinationController(DestinationService destinationService, com.example.services.ImageStorageService imageStorageService) {
        this.destinationService = destinationService;
        this.imageStorageService = imageStorageService;
    }

    @PostMapping("/add")
    public ResponseEntity<Destination> addDestinationFromForm(@RequestParam("location") String location,
                                                              @RequestParam("description") String description,
                                                              @RequestParam("country") String country,
                                                              @RequestParam("rating") int rating,
                                                              @RequestParam("arrivalDate") String arrivalDateString,
                                                              @RequestParam("departureDate") String departureDateString,
                                                              @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        LocalDate arrivalDate = LocalDate.parse(arrivalDateString);
        LocalDate departureDate = LocalDate.parse(departureDateString);

        String filename = imageStorageService.storeImage(imageFile);

        ArrayList<String> images = new ArrayList<>();
        images.add(filename);

        Destination destination = new Destination(location, country, description, rating, arrivalDate, departureDate, images);

        Destination newDestination = destinationService.save(destination);

        return new ResponseEntity<>(newDestination, HttpStatus.CREATED);
    }




    @PostMapping
    public ResponseEntity<Destination> addDestination(@RequestBody Destination destination) {
        Destination newDestination = destinationService.save(destination);
        return new ResponseEntity<>(newDestination, HttpStatus.CREATED);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Destination>> getAllDestinations(){
        List<Destination> destinations = destinationService.getAllDestinations();
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable("id") long id) {
        Optional<Destination> destinationOptional = destinationService.getDestinationById(id);
        Destination destination = destinationOptional.orElse(null);
        return new ResponseEntity<>(destination, HttpStatus.OK);
    }


    @GetMapping("/country/{country}")
    public ResponseEntity<List<Destination>> getDestinationsByCountry(@PathVariable("country") String country) {
        List<Destination> destinations = destinationService.getDestinationsByCountry(country);
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<Destination>> getDestinationsByRatingGreaterThan(@PathVariable("rating") int rating) {
        List<Destination> destinations = destinationService.getDestinationsByRatingGreaterThan(rating);
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @GetMapping("/between/{startDate}/{endDate}")
    public ResponseEntity<List<Destination>> getDestinationsByArrivalDateBetween(@PathVariable("startDate") String startDateString,
                                                                                 @PathVariable("endDate") String endDateString) {
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate endDate = LocalDate.parse(endDateString);
        List<Destination> destinations = destinationService.getDestinationsByArrivalDateBetween(startDate, endDate);
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @GetMapping("/before/{date}")
    public ResponseEntity<List<Destination>> getDestinationsByDepartureDateBefore(@PathVariable("date") String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        List<Destination> destinations = destinationService.getDestinationsByDepartureDateBefore(date);
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @GetMapping("/after/{date}")
    public ResponseEntity<List<Destination>> getDestinationsByArrivalDateAfter(@PathVariable("date") String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        List<Destination> destinations = destinationService.getDestinationsByArrivalDateAfter(date);
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @GetMapping("/asc/{country}")
    public ResponseEntity<List<Destination>> getDestinationsByCountryOrderByRatingAsc(@PathVariable("country") String country) {
        List<Destination> destinations = destinationService.getDestinationsByCountryOrderByRatingAsc(country);
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @GetMapping("/gte/{rating}")
    public ResponseEntity<List<Destination>> getDestinationsByRatingGreaterThanEqual(@PathVariable("rating") int rating) {
        List<Destination> destinations = destinationService.getDestinationsByRatingGreaterThanEqual(rating);
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @GetMapping("/search/{searchString}")
    public ResponseEntity<List<Destination>> getDestinationsByLocationContainingIgnoreCase(@PathVariable("searchString") String searchString) {
        List<Destination> destinations = destinationService.getDestinationsByLocationContainingIgnoreCase(searchString);
        return new ResponseEntity<>(destinations, HttpStatus.OK);
    }

    @GetMapping("/distinct/countries")
    public ResponseEntity<List<String>> getDistinctCountries() {
        List<String> countries = destinationService.getDistinctCountries();
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }



    @PutMapping("/destination/edit/{id}")
    public ResponseEntity<Destination> updateDestinationById(@PathVariable("id") long id, @RequestBody Destination newDestination) {
        Optional<Destination> optionalDestination = destinationService.getDestinationById(id);

        if (optionalDestination.isPresent()) {
            Destination destination = optionalDestination.get();
            destination.setLocation(newDestination.getLocation());
            destination.setDescription(newDestination.getDescription());
            destination.setCountry(newDestination.getCountry());
            destination.setRating(newDestination.getRating());
            destination.setArrivalDate(newDestination.getArrivalDate());
            destination.setDepartureDate(newDestination.getDepartureDate());
            destination.setImages(newDestination.getImages());
            destinationService.save(destination);
            return new ResponseEntity<>(destination, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/destination/{id}")
    public ResponseEntity<String> deleteDestinationById(@PathVariable long id){
        destinationService.deleteDestinationById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Destination");

    }






}
