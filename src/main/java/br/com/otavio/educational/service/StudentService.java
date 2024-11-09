package br.com.otavio.educational.service;

import br.com.otavio.educational.dto.StudentDto;
import br.com.otavio.educational.model.StudentModel;
import br.com.otavio.educational.repository.StudentRepository;
import br.com.otavio.educational.service.exception.ResourceEmailAlreadyExistsException;
import br.com.otavio.educational.service.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void createStudent(StudentDto studentDto) {
        verifyEmail(studentDto);

        StudentModel studentModel = convertDtoToModel(studentDto);

        studentRepository.save(studentModel);
    }

    public void updateStudent(StudentDto studentDto, Integer id) {
        StudentDto verifyStudentDto = findById(id);

        if(!verifyStudentDto.getEmail().equals(studentDto.getEmail())){
            verifyEmail(studentDto);
        }

        StudentModel studentModel = convertDtoToModel(studentDto);
        studentModel.setId(id);
        studentRepository.save(studentModel);
    }

    public StudentDto findById (Integer id) {
        StudentModel studentModel = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id não foi encontrado"));

        return convertModelToDto(studentModel);
    }

    public List<StudentDto> findAll () {
        List<StudentModel> lstStudentModel = studentRepository.findAll();
        List<StudentDto> lstStudentDto = new ArrayList<>();

        for(StudentModel studentModel : lstStudentModel) {
            lstStudentDto.add(convertModelToDto(studentModel));
        }

        return lstStudentDto;
    }

    public void deleteStudent(Integer id) {
        StudentDto verifyStudentDto = findById(id);
        StudentModel studentModel = convertDtoToModel(verifyStudentDto);
        studentModel.setId(id);
        studentRepository.delete(studentModel);
    }

    private void verifyEmail (StudentDto studentDto) {
        Optional<StudentModel> optionalStudentModel = studentRepository.findByEmail(studentDto.getEmail());

        if(optionalStudentModel.isPresent()){
            throw new ResourceEmailAlreadyExistsException("Já existe um aluno com teste email");
        }
    }

    private StudentModel convertDtoToModel(StudentDto studentDto) {
        StudentModel studentModel = new StudentModel();
        studentModel.setName(studentDto.getName());
        studentModel.setEmail(studentDto.getEmail());
        studentModel.setRegistration(studentDto.getRegistration());
        studentModel.setDateBirth(studentDto.getDateBirth());

        return studentModel;
    }

    private StudentDto convertModelToDto(StudentModel studentModel) {
        StudentDto studentDto = new StudentDto();
        studentDto.setName(studentModel.getName());
        studentDto.setEmail(studentModel.getEmail());
        studentDto.setRegistration(studentModel.getRegistration());
        studentDto.setDateBirth(studentModel.getDateBirth());

        return studentDto;
    }

}
