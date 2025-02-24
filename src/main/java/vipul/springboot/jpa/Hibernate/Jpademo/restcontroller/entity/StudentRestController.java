package vipul.springboot.jpa.Hibernate.Jpademo.restcontroller.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vipul.springboot.jpa.Hibernate.Jpademo.entity.Passport;
import vipul.springboot.jpa.Hibernate.Jpademo.entity.Student;
import vipul.springboot.jpa.Hibernate.Jpademo.jparepo.PassportSpringDataJpaRepository;
import vipul.springboot.jpa.Hibernate.Jpademo.jparepo.StudentSpringDataJpaRepository;
import vipul.springboot.jpa.Hibernate.Jpademo.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")

public class StudentRestController {

    public StudentRestController() {
        System.out.println("StudentRestController created");
    }

    @Autowired
    StudentService studser;

    @Autowired
    private StudentSpringDataJpaRepository srepo;

    @Autowired
    private PassportSpringDataJpaRepository prepo;


    @GetMapping("/all/studs")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(srepo.findAll(), HttpStatus.OK);
    }


    @GetMapping("/stud/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Optional<Student> s = srepo.findById(id);
        if (s.isPresent())
            return new ResponseEntity<>(s.get(),HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> addStudent(@RequestBody Student s)
    {
        if(s==null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        else {
            Student s1 = studser.addStudent(s);
            if (s1 != null)
                return new ResponseEntity<>(s1, HttpStatus.CREATED);
            else
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping("/passport")
    public ResponseEntity<Passport> addPassport(@RequestBody Passport p)
    {
        if(p==null)
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        else {
            Passport p1 =  studser.addPassport(p);

            if (p1!=null)
                return new ResponseEntity<>(p1,HttpStatus.CREATED);
            else
                return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/student/update/{studid}/{studname}/{studmarks}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long studid,
            @PathVariable String studname,
            @PathVariable int studmarks) {

        Student s = studser.updateStudent(studid, studname, studmarks);

        if (s != null) {
            return new ResponseEntity<>(s, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(s, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/student/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        boolean deleted = studser.deleteStudent(id);

        if (deleted) {
            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/stud/passport/{id}")
    public ResponseEntity<Passport> getStudentsPassport (@PathVariable Long id)
    {
        Student s = studser.getStudentById(id);
        if (s!=null){
            Passport p = s.getstudpass();
            if (p!=null)
                return new ResponseEntity<>(p,HttpStatus.OK);
            else
                return new ResponseEntity<>(p,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

    }


    @GetMapping("/passport/stud/{passportid}")
    public ResponseEntity <Student> getPassportOfStudent(@PathVariable Long passportid)
    {
        Passport p = studser.getPassportById(passportid);
        if (p!=null){
            Student s  = p.getStudent();
            if (s!=null)
                return new ResponseEntity<>(s,HttpStatus.OK);
            else
                return new ResponseEntity<>(s ,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }




}
