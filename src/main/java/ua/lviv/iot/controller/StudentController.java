package ua.lviv.iot.controller;

import lombok.RequiredArgsConstructor;
import ua.lviv.iot.dao.StudentRepository;
import ua.lviv.iot.entiry.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping("/student")
    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id){
        Optional<Student> studentOptional = studentRepository.findById(id);
        return studentOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/student")
    public ResponseEntity<Student> create(@RequestBody Student student){
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student){
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (studentOptional.isPresent()) {
            Student persisted = studentOptional.get();
            persisted.setFirstName(student.getFirstName());
            persisted.setLastName(student.getLastName());
            return ResponseEntity.ok(studentRepository.save(persisted));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("student/{id}")
    public boolean delete(@PathVariable Long id){
        studentRepository.deleteById(id);
        return true;
    }


}