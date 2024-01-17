package bo.usfx.springneuroapi.controller;

import bo.usfx.springneuroapi.model.Neurodiversity;
import bo.usfx.springneuroapi.repository.NeurodivergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NeurodiversityController {
    @Autowired
    private NeurodivergencyRepository neurodivergencyRepository;
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/neurodiversities")
    public List<Neurodiversity> getAll() {
        return neurodivergencyRepository.findAll();
    }


    @GetMapping("/api/v1/neurodiversities/id/{id}")
    private ResponseEntity<?> getById(@PathVariable String id){
       var  noe=neurodivergencyRepository.finById(id);
        return ResponseEntity.ok(noe);
    }
    @GetMapping("/api/v1/neurodiversities/name/{name}")
    private ResponseEntity<?> getByName(@PathVariable String name){
        var noe=neurodivergencyRepository.findByName(name);
        return ResponseEntity.ok(noe);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody Neurodiversity disease){
        Neurodiversity Create=neurodivergencyRepository.save(disease);
        return ResponseEntity.ok(Create);
    }

}
