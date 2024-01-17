package bo.usfx.springneuroapi.controller;

import bo.usfx.springneuroapi.model.Neurodiversity;
import bo.usfx.springneuroapi.repository.NeurodivergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public final class NeurodiversityController {
    @Autowired
    private NeurodivergencyRepository neurodivergencyRepository;
    /**
     * Retrieve all neurodiversities from the database.
     *
     * @return a ResponseEntity containing the list of neurodiversities or a not found status
     */
    // getAll
    @GetMapping("api/v1/neurodiversities")
    public ResponseEntity<Object> getAll() {
        List<Neurodiversity> neurodiversities = neurodivergencyRepository.findAll();
        if (!neurodiversities.isEmpty()) {
            return new ResponseEntity<>(neurodiversities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No neurodiversities available", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/v1/neurodiversities/name/{name}")
    public ResponseEntity<List<Neurodiversity>> getNeurodiversityByName(@PathVariable("name") final String name) {
        List<Neurodiversity> neurodiversities = neurodivergencyRepository.findByName(name);
        return !neurodiversities.isEmpty()
                ? new ResponseEntity<>(neurodiversities, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/api/v1/neurodiversities/id/{id}")
    public ResponseEntity<Object> getNeurodiversityById(@PathVariable("id") final String id) {
        Optional<Neurodiversity> neurodiversityOptional = neurodivergencyRepository.findById(id);
        if (neurodiversityOptional.isPresent()) {
            return new ResponseEntity<>(neurodiversityOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Neurodiversity not found for this id: " + id, HttpStatus.NOT_FOUND);
        }
    }
}
