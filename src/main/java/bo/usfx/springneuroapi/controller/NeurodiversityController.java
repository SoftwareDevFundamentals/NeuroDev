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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public final class NeurodiversityController {
    private final NeurodivergencyRepository neurodivergencyRepository;

    @Autowired
    public NeurodiversityController(NeurodivergencyRepository neurodivergencyRepository) {
        this.neurodivergencyRepository = neurodivergencyRepository;
    }

    // get all entities
    @GetMapping("/api/v1/neurodiversities")
    public List<Neurodiversity> getAll() {
        return neurodivergencyRepository.findAll();
    }

    // get entity by method getId
    @GetMapping("/api/v1/neurodiversity/{id}")
    public ResponseEntity<Neurodiversity> getById(@PathVariable final String id) {
        Optional<Neurodiversity> neuroOptional = neurodivergencyRepository.findById(id);

        return neuroOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // get entity by method getName
    @GetMapping("/api/v1/neurodiversity-name/{name}")
    public ResponseEntity<Neurodiversity> getByName(@PathVariable final String name) {
        List<Neurodiversity> neuroList = neurodivergencyRepository.findByName(name);

        if (!neuroList.isEmpty()) {
            Neurodiversity neuro = neuroList.getFirst();
            return ResponseEntity.ok(neuro);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/api/v1/neurodiversity")
    public ResponseEntity<Neurodiversity> createEntity(@RequestBody final Neurodiversity neurodiversity) {
        neurodivergencyRepository.save(neurodiversity);
        return new ResponseEntity<>(neurodiversity, HttpStatus.CREATED);
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

                ReflectionUtils.setField(field, existing, value);
            }
        });
        return neurodivergencyRepository.save(existing);
    }

    // Delete Request
    @DeleteMapping("/api/v1/neurodiversity/{id}")
    public ResponseEntity<String> deleteNeuro(@PathVariable("id") final String id) {
        try {
            neurodivergencyRepository.deleteById(id);
            return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // @RequestParams //

    @GetMapping("/api/v1/neurodiversity")
    public ResponseEntity<List<Neurodiversity>> getNeurodiversitiesBySubstring(
            @RequestParam(required = false) final String name) {

        List<Neurodiversity> neurodiversities;
        if (name != null && !name.isEmpty()) {
            neurodiversities = neurodivergencyRepository.findByNameStartingWithIgnoreCase(name);
        } else {
            neurodiversities = neurodivergencyRepository.findAll();
        }
        return new ResponseEntity<>(neurodiversities, HttpStatus.OK);
    }

    // key word
    @GetMapping("/api/v1/neurodiversities/{key}")
    public ResponseEntity<List<Neurodiversity>> getByDescription(@PathVariable("key") final String keyword) {
        List<Neurodiversity> neuro = neurodivergencyRepository.findByDescriptionContaining(keyword);
        if (!neuro.isEmpty()) {
            return ResponseEntity.ok(neuro);
        }
        return ResponseEntity.notFound().build();
    }

    // @RequestParams //
}