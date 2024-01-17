package bo.usfx.springneuroapi.repository;

import bo.usfx.springneuroapi.model.Neurodiversity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NeurodivergencyRepository extends MongoRepository<Neurodiversity, String>{
    Optional<Neurodiversity> findByName(String name);
}
