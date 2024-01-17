package bo.usfx.springneuroapi.controller;

import bo.usfx.springneuroapi.model.Neurodiversity;
import bo.usfx.springneuroapi.repository.NeurodivergencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public final class NeurodiversityController {

    private static final String NEURODIVERSITY_NOT_FOUND_MESSAGE = "Neurodiversity not found for this id: ";

    @Autowired
    private NeurodivergencyRepository neurodivergencyRepository;
    /**
     * Retrieve all neurodiversities from the database.
     *
     * @return a ResponseEntity containing the list of neurodiversities or a not found status
     */
    @GetMapping("api/v1/neurodiversities")
    public ResponseEntity<Object> getAll() {
        List<Neurodiversity> neurodiversities = neurodivergencyRepository.findAll();
        if (!neurodiversities.isEmpty()) {
            return new ResponseEntity<>(neurodiversities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No neurodiversities available", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("api/v1/neurodiversities")
    @ResponseStatus(HttpStatus.CREATED)
    public Neurodiversity createNeurodiversity(@RequestBody final Neurodiversity neurodiversity) {
        neurodiversity.setId(UUID.randomUUID().toString().split("-")[0]);
        return neurodivergencyRepository.save(neurodiversity);
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
            return new ResponseEntity<>(NEURODIVERSITY_NOT_FOUND_MESSAGE + id, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("api/v1/neurodiversities/id/{id}")
    public ResponseEntity<Neurodiversity> updateNeurodiversity(@PathVariable(value = "id")
        final String id, @RequestBody final Neurodiversity neurodiversityDetails) {
        Neurodiversity neurodiversity = neurodivergencyRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException(NEURODIVERSITY_NOT_FOUND_MESSAGE + id));

        neurodiversity.setName(neurodiversityDetails.getName());
        neurodiversity.setDescription(neurodiversityDetails.getDescription());
        neurodiversity.setWorldWidePercentage(neurodiversityDetails.getWorldWidePercentage());
        neurodiversity.setBasicExplanationLink(neurodiversity.getBasicExplanationLink());
        neurodiversity.setTestLink(neurodiversity.getTestLink());

        final Neurodiversity updatedNeurodiversity = neurodivergencyRepository.save(neurodiversity);
        return ResponseEntity.ok(updatedNeurodiversity);
    }

    @DeleteMapping("api/v1/neurodiversities/id/{id}")
    public ResponseEntity<Object> deleteNeurodiversity(@PathVariable(value = "id") final String id) {
        Neurodiversity neurodiversity = neurodivergencyRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException(NEURODIVERSITY_NOT_FOUND_MESSAGE + id));
        neurodivergencyRepository.delete(neurodiversity);
        return ResponseEntity.ok().build();
    }
}
