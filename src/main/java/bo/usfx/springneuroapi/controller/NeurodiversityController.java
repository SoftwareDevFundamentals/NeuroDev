package bo.usfx.springneuroapi.controller;

import bo.usfx.springneuroapi.model.Neurodiversity;
import bo.usfx.springneuroapi.repository.NeurodivergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class NeurodiversityController {
    @Autowired
    private NeurodivergencyRepository neurodivergencyRepository;
    @GetMapping("/api/v1/neurodiversities")
    public List<Neurodiversity> getAll() {
        return neurodivergencyRepository.findAll();
    }
    @GetMapping("/api/v1/neurodiversities/{name}")
    public ResponseEntity<?> getSingle(@PathVariable("name") String name){
        Optional<Neurodiversity> neuroOptional = neurodivergencyRepository.findByName(name);
        if(neuroOptional.isPresent()){
            return new ResponseEntity<>(neuroOptional.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Neurodivergency not found with name "+name, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/v1/neurodiversities")
    public ResponseEntity<?> createSingle(@RequestBody Neurodiversity neurodiversity){
        try {
            neurodivergencyRepository.save(neurodiversity);
            return new ResponseEntity<Neurodiversity>(neurodiversity,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
