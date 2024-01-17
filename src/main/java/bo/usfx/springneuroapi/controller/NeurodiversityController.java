package bo.usfx.springneuroapi.controller;

import bo.usfx.springneuroapi.model.Neurodiversity;
import bo.usfx.springneuroapi.repository.NeurodivergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


public final class NeurodiversityController {
    @Autowired
    private NeurodivergencyRepository neurodivergencyRepository;

    //get all entities
    @GetMapping("/api/v1/neurodiversities")
    public List<Neurodiversity> getAll() {
        return neurodivergencyRepository.findAll();
    }


    //get entity by method getId
    @GetMapping("/api/v1/neurodiversities/{id}")
    public ResponseEntity<?> getById(@PathVariable final String id) {
        var neuro = neurodivergencyRepository.findById(id);
        if (neuro != null) {
            return ResponseEntity.ok(neuro);
        }
        return null;
    }

    //get entity by method getName
    @GetMapping("/api/v1/neurodiversities/{name}")
    public ResponseEntity<?> getByName(@PathVariable final String name) {
        var neuro = neurodivergencyRepository.findByName(name);
        if (neuro != null) {
            return ResponseEntity.ok(neuro);
        }
        return null;
    }

    @PostMapping("/api/v1/neurodiversity/")
    public ResponseEntity<?> createEntity(@RequestBody final Neurodiversity neurodiversity) {
        neurodivergencyRepository.save(neurodiversity);
        return new ResponseEntity<>(neurodiversity, HttpStatus.OK);
    }


}
