package bo.usfx.springneuroapi.controller;

import bo.usfx.springneuroapi.model.Neurodiversity;
import bo.usfx.springneuroapi.repository.NeurodivergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


@RestController
public final class NeurodiversityController {
    @Autowired
    private NeurodivergencyRepository neurodivergencyRepository;

    // Get all entities
    @GetMapping("/api/v1/neurodiversities")
    public List<Neurodiversity> getAll() {
        return neurodivergencyRepository.findAll();
    }

    // Get request by method getId
    @GetMapping("/api/v1/neurodiversities/id/{id}")
    public ResponseEntity<?> getById(@PathVariable final String id) {
        var neuro = neurodivergencyRepository.findById(id);
        if (neuro != null) {
            return ResponseEntity.ok(neuro);
        }
        return null;
    }

    // Get request by method getName
    @GetMapping("/api/v1/neurodiversities/name/{name}")
    public ResponseEntity<?> getByName(@PathVariable final String name) {
        var neuro = neurodivergencyRepository.findByName(name);
        if (neuro != null) {
            return ResponseEntity.ok(neuro);
        }
        return null;
    }

    // Post Request
    @PostMapping("/api/v1/neurodiversities")
    public ResponseEntity<?> createEntity(@RequestBody final Neurodiversity neurodiversity) {
        neurodivergencyRepository.save(neurodiversity);
        return new ResponseEntity<>(neurodiversity, HttpStatus.OK);
    }

    // Put Request
    @PutMapping("/api/v1/neurodiversities/{id}")
    public ResponseEntity<Neurodiversity> updateN(@PathVariable(value = "id") final String id,
                                                  @RequestBody final Map<String, Object> fields) {
        Neurodiversity updatedNeurodiversity = updateNeurodiversityFields(id, fields);
        neurodivergencyRepository.save(updatedNeurodiversity);
        return ResponseEntity.ok(updatedNeurodiversity);
    }

    public Neurodiversity updateNeurodiversityFields(final String id, final Map<String, Object> fields) {
        Neurodiversity existing = neurodivergencyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found id: " + id));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Neurodiversity.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, existing, value);
            }
        });
        return neurodivergencyRepository.save(existing);
    }

    // Delete Request
    @DeleteMapping("/api/v1/neurodiversities/{id}")
    public ResponseEntity<?> deleteById(@PathVariable final String id) {
        try {
            neurodivergencyRepository.deleteById(id);
            return new ResponseEntity<>("Entity " + id + " deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
