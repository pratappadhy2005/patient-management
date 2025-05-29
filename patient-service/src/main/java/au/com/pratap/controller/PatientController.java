package au.com.pratap.controller;

import au.com.pratap.dto.PatientRequestDTO;
import au.com.pratap.dto.PatientResponseDTO;
import au.com.pratap.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * Endpoint to retrieve all patients.
     *
     * @return a list of PatientResponseDTO containing patient details.
     */
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getPatients();
        return ResponseEntity.ok().body(patients);
    }

    /**
     * Endpoint to save a new patient.
     * This method is not implemented yet.
     *
     * @return a response indicating that the method is not implemented.
     */
    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO) {
        final PatientResponseDTO patientResponseDTO = patientService.savePatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

}
