package bo.usfx.springneuroapi.controller;

import bo.usfx.springneuroapi.model.Neurodiversity;
import bo.usfx.springneuroapi.repository.NeurodivergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NeurodiversityController {
    @Autowired
    private NeurodivergencyRepository neurodivergencyRepository;

    //get all entities
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/neurodiversities")
    public List<Neurodiversity> getAll() {
        return neurodivergencyRepository.findAll();
    }


    //get entity by method getId
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/neurodiversities/id/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        var neuro = neurodivergencyRepository.findById(id);
        if (neuro != null) {
            return ResponseEntity.ok(neuro);
        }
        return null;
    }

    //get entity by method getName
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/neurodiversities/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable String name) {
        var neuro = neurodivergencyRepository.findByName(name);
        if (neuro != null) {
            return ResponseEntity.ok(neuro);
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/neurodiversity/create")
    public ResponseEntity<?> createEntity(@RequestBody Neurodiversity neurodiversity) {
        neurodivergencyRepository.save(neurodiversity);
        return new ResponseEntity<>(neurodiversity, HttpStatus.OK);
    }


}
