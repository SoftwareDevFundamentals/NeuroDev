package bo.usfx.springneuroapi.repository;

import bo.usfx.springneuroapi.model.Neurodiversity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NeurodivergencyRepository extends MongoRepository<Neurodiversity, String> {

    List<Neurodiversity> findByName(String name);
    @Query("{'description': {$regex : ?0, $options: 'i'}}")
    List<Neurodiversity> findByDescriptionContaining(String keyword);
    
    List<Neurodiversity> findByNameStartingWithIgnoreCase(String name);
}

