package vipul.springboot.jpa.Hibernate.Jpademo.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vipul.springboot.jpa.Hibernate.Jpademo.entity.Passport;
import vipul.springboot.jpa.Hibernate.Jpademo.entity.Student;
import vipul.springboot.jpa.Hibernate.Jpademo.jparepo.PassportSpringDataJpaRepository;
import vipul.springboot.jpa.Hibernate.Jpademo.jparepo.StudentSpringDataJpaRepository;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    @Autowired
    private StudentSpringDataJpaRepository srepo;

    @Autowired
    private PassportSpringDataJpaRepository passrepo;

    public List<Student> findAllStuds()
    {
        return srepo.findAll();
    }


    public Student getStudentById(Long id)
    {
        Optional<Student> s = srepo.findById(id);
        if(s.isPresent())
            return s.get();
        else
            return null;
    }

    @Transactional
    public Passport addPassport(Passport p) 
    {
        Passport p1 = passrepo.save(p);
        passrepo.flush();//commit the entity into database
        return p1;
    }

    @Transactional
    public Student addStudent(Student s){
        Student s1 = srepo.save(s);
        srepo.flush();
        return s1;

    }

    @Transactional
    public Student updateStudent(Long id, String name, int totalmarks) {
        Student s1 = null;
        Optional<Student> s = srepo.findById(id);

        if (s.isPresent()) {
            s1 = s.get();
            s1.setname(name);
            s1.setTotalMarks(totalmarks);
            s1 = srepo.save(s1);
            return s1;
        } else {
            return null;
        }
    }

    @Transactional
    public boolean deleteStudent(Long id) {
        Optional<Student> s = srepo.findById(id);

        if (s.isPresent()) {
            srepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


    public Passport getPassportById(Long id) {
        Optional<Passport> p = passrepo.findById(id);
        if (p.isPresent())
            return p.get();
        else
            return null;
    }
}
