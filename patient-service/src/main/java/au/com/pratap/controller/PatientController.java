package au.com.pratap.controller;

import au.com.pratap.dto.PatientRequestDTO;
import au.com.pratap.dto.PatientResponseDTO;
import au.com.pratap.dto.validators.CreatePatientValidationGroup;
import au.com.pratap.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient Management", description = "APIs for managing patients")
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
    @Operation(summary = "Get all patients", description = "Retrieves a list of all patients in the system.")
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
    @Operation(summary = "Create a new patient", description = "Saves a new patient in the system.")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class,
            CreatePatientValidationGroup.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        final PatientResponseDTO patientResponseDTO = patientService.savePatient(patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    /**
     * Endpoint to update an existing patient.
     * This method is not implemented yet.
     *
     * @param id                the ID of the patient to update
     * @param patientRequestDTO the updated patient details
     * @return a response indicating that the method is not implemented.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing patient", description = "Updates the details of an existing patient.")
    public ResponseEntity<PatientResponseDTO> updatePatient(
            @PathVariable("id") UUID id,
            @Validated({Default.class}) @RequestBody PatientRequestDTO patientRequestDTO) {
        final PatientResponseDTO patientResponseDTO = patientService.updatePatient(id, patientRequestDTO);
        return ResponseEntity.ok().body(patientResponseDTO);
    }

    /**
     * Endpoint to delete a patient by ID.
     *
     * @param id the ID of the patient to delete
     * @return a response indicating that the patient was deleted successfully.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient", description = "Deletes a patient by ID.")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") UUID id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

}
