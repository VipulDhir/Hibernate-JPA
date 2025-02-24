package vipul.springboot.jpa.Hibernate.Jpademo.jparepo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vipul.springboot.jpa.Hibernate.Jpademo.entity.Student;

import java.util.List;

@Repository
public interface StudentSpringDataJpaRepository
        extends JpaRepository<Student, Long> {

    @Transactional
    void deleteByName(String name);

    Long countByName(String name);

    @Query("select s from Student s where s.name like '%o%' ")
    List<Student> studentWithPatternInName();

    @Query(value = "select * from Stud s where studname like 'R%' " , nativeQuery = true)
    List<Student> studentWithPatternInNameUsingNativeQuery();

}
