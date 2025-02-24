package vipul.springboot.jpa.Hibernate.Jpademo.jparepo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vipul.springboot.jpa.Hibernate.Jpademo.entity.Employee;

import java.util.Optional;


@Transactional
@Repository
public interface EmployeeSpringDataJpaRepository
        extends JpaRepository<Employee,Long> {

    @EntityGraph(attributePaths = "emppass")
    Optional<Employee> findById(Long id);

    @EntityGraph(attributePaths = "emppass")
    Optional<Employee> findByName(String name);

    @EntityGraph(attributePaths = "emppass")
    Optional<Employee> findByNameAndSalary(String name, double salary);

}
