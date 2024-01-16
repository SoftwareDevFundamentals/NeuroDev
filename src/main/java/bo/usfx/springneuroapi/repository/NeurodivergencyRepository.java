package bo.usfx.springneuroapi.repository;

import bo.usfx.springneuroapi.model.Neurodiversity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NeurodivergencyRepository extends MongoRepository<Neurodiversity, String>{
}
