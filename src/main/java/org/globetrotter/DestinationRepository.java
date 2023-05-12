package org.globetrotter;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {

    // READ operations
    List<Destination> findByCountry(String country);
    List<Destination> findByRatingGreaterThan(int rating);
    List<Destination> findByArrivalDateBetween(LocalDate startDate, LocalDate endDate);
    List<Destination> findByDepartureDateBefore(LocalDate date);
    List<Destination> findByArrivalDateAfter(LocalDate date);
    List<Destination> findByCountryOrderByRatingAsc(String country);
    List<Destination> findByRatingGreaterThanEqual(int rating);
    List<Destination> findByLocationContainingIgnoreCase(String searchString);
    @Query(value = "SELECT DISTINCT country FROM destination ORDER BY country ASC", nativeQuery = true)
    List<String> findDistinctCountries();

    // CREATE and UPDATE operations
    Destination save(Destination destination); // for adding or updating a record

    // DELETE operations
    void deleteById(long id); // for deleting a record by ID
    void deleteAll(); // for deleting all records in the table

    // additional custom operation
    @Query(value = "SELECT * FROM globetrotter ORDER BY RAND() LIMIT 1", nativeQuery = true)
    DestinationRepository getRandomDestination();
}
