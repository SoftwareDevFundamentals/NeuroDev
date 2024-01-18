package bo.usfx.springneuroapi.controller;

import bo.usfx.springneuroapi.model.Neurodiversity;
import bo.usfx.springneuroapi.repository.NeurodivergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


@RestController
public final class NeurodiversityController {
    @Autowired
    private NeurodivergencyRepository neurodivergencyRepository;

    //get all entities
    @GetMapping("/api/v1/neurodiversities")
    public List<Neurodiversity> getAll() {
        return neurodivergencyRepository.findAll();
    }


    //get entity by method getId
    @GetMapping("/api/v1/neurodiversity/{id}")
    public ResponseEntity<?> getById(@PathVariable final String id) {
        var neuro = neurodivergencyRepository.findById(id);
        if (neuro != null) {
            return ResponseEntity.ok(neuro);
        }
        return null;
    }

    //get entity by method getName
    @GetMapping("/api/v1/neurodiversity-name/{name}")
    public ResponseEntity<?> getByName(@PathVariable final String name) {
        var neuro = neurodivergencyRepository.findByName(name);
        if (neuro != null) {
            return ResponseEntity.ok(neuro);
        }
        return null;
    }

    @PostMapping("/api/v1/neurodiversity")
    public ResponseEntity<?> createEntity(@RequestBody final Neurodiversity neurodiversity) {
        neurodivergencyRepository.save(neurodiversity);
        return new ResponseEntity<>(neurodiversity, HttpStatus.OK);
    }

    // Put Request //
    @PutMapping("/api/v1/neurodiversity/{id}")
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

    //Delete Request
    @DeleteMapping("/api/v1/neurodiversity/{id}")
    public ResponseEntity<?> deleteNeuro(@PathVariable("id") final String id) {
        try {
            neurodivergencyRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    // key word
    @GetMapping("/api/v1/neurodiversities/description")
    public ResponseEntity<List<Neurodiversity>> getByDescription(@RequestParam("keyword") final String keyword) {
        List<Neurodiversity> neuro = neurodivergencyRepository.findByDescriptionContaining(keyword);
        if (!neuro.isEmpty()) {
            return ResponseEntity.ok(neuro);
        }
        return ResponseEntity.notFound().build();
    }
}
