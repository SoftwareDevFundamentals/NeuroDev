package bo.usfx.springneuroapi.controller;

import bo.usfx.springneuroapi.model.Neurodiversity;
import bo.usfx.springneuroapi.repository.NeurodivergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NeurodiversityController {
    @Autowired
    private NeurodivergencyRepository neurodivergencyRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/neurodiversities")
    public final List<Neurodiversity> getAll() {
        return neurodivergencyRepository.findAll();
    }

    //    GET request By Name
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/neurodiversity/{name}")
    public final ResponseEntity<?> getByName(@PathVariable final String name) {
        var neuroDiversity = neurodivergencyRepository.findByName(name);
        if (neuroDiversity != null) {
            return ResponseEntity.ok(neuroDiversity);
        }
        return null;
    }

    //    Post Request
    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/neurodiversity")
    public final ResponseEntity<Neurodiversity> create(@RequestBody final Neurodiversity neurodiversity) {
        neurodivergencyRepository.save(neurodiversity);
        return new ResponseEntity<>(neurodiversity, HttpStatus.OK);
    }

}
