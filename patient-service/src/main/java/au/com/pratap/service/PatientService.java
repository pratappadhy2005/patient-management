package au.com.pratap.service;

import au.com.pratap.dto.PatientRequestDTO;
import au.com.pratap.dto.PatientResponseDTO;
import au.com.pratap.exception.EmailAlreadyExistsException;
import au.com.pratap.mapper.PatientMapper;
import au.com.pratap.model.Patient;
import au.com.pratap.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    /**
     * Retrieves all patients from the repository and maps them to PatientResponseDTO.
     *
     * @return a list of PatientResponseDTO containing patient details.
     */
    public List<PatientResponseDTO> getPatients(){
       final List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(PatientMapper::toPatientResponseDTO)
                .collect(Collectors.toList());
    }

    /**
     * Saves a new patient to the repository.
     * This method converts the PatientRequestDTO to a Patient entity,
     * saves it to the database, and then converts it back to a PatientResponseDTO.
     *
     * @param patientRequestDTO
     * @return
     */
    public PatientResponseDTO savePatient(PatientRequestDTO patientRequestDTO) {
        Patient newPatient = patientRepository.save(PatientMapper.toPatientEntity(patientRequestDTO));

        // Check if the email already exists in the repository
        if(patientRepository.existsByEmail(newPatient.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + newPatient.getEmail());
        }

        return PatientMapper.toPatientResponseDTO(newPatient);
    }
}
