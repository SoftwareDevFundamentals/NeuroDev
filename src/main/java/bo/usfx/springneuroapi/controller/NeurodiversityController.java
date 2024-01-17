package bo.usfx.springneuroapi.controller;

import bo.usfx.springneuroapi.model.Neurodiversity;
import bo.usfx.springneuroapi.repository.NeurodivergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public final class NeurodiversityController {
    @Autowired
    private NeurodivergencyRepository neurodivergencyRepository;

    @GetMapping("/api/v1/neurodiversities")
    public List<Neurodiversity> getAll() {
        return neurodivergencyRepository.findAll();
    }

    @GetMapping("/api/v1/neurodiversities/{name}")
    public ResponseEntity<?> getByName(@PathVariable final String name) {
        Optional<Neurodiversity> neurodiversity = neurodivergencyRepository.findByName(name);
        if (neurodiversity.isPresent()) {
            return new ResponseEntity<>(neurodiversity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Neurodiversity '" + name + "' was not found.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/v1/neurodiversities")
    public ResponseEntity<?> post(@RequestBody final Neurodiversity neurodiversity) {
        try {
            neurodivergencyRepository.save(neurodiversity);
            return new ResponseEntity<>(neurodiversity, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
