package org.globetrotter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    // READ operations
    public List<Destination> getAllDestinations() {
        return destinationRepository.findAll();
    }

    public Optional<Destination> getDestinationById(long id) {
        return destinationRepository.findById(id);
    }

    public List<Destination> getDestinationsByCountry(String country) {
        return destinationRepository.findByCountry(country);
    }

    public List<Destination> getDestinationsByRatingGreaterThan(int rating) {
        return destinationRepository.findByRatingGreaterThan(rating);
    }

    public List<Destination> getDestinationsByArrivalDateBetween(LocalDate startDate, LocalDate endDate) {
        return destinationRepository.findByArrivalDateBetween(startDate, endDate);
    }

    public List<Destination> getDestinationsByDepartureDateBefore(LocalDate date) {
        return destinationRepository.findByDepartureDateBefore(date);
    }

    public List<Destination> getDestinationsByArrivalDateAfter(LocalDate date) {
        return destinationRepository.findByArrivalDateAfter(date);
    }

    public List<Destination> getDestinationsByCountryOrderByRatingAsc(String country) {
        return destinationRepository.findByCountryOrderByRatingAsc(country);
    }

    public List<Destination> getDestinationsByRatingGreaterThanEqual(int rating) {
        return destinationRepository.findByRatingGreaterThanEqual(rating);
    }

    public List<Destination> getDestinationsByLocationContainingIgnoreCase(String searchString) {
        return destinationRepository.findByLocationContainingIgnoreCase(searchString);
    }

    public List<String> getDistinctCountries() {
        return destinationRepository.findDistinctCountries();
    }

    // CREATE and UPDATE operations
    public Destination save(Destination destination) {
        destinationRepository.save(destination);
        return destination;
    }

    // DELETE operations
    public void deleteDestinationById(long id) {
        destinationRepository.deleteById(id);
    }

    public void deleteAllDestinations() {
        destinationRepository.deleteAll();
    }


}
