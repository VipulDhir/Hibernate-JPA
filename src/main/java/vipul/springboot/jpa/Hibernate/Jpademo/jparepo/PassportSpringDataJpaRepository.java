package vipul.springboot.jpa.Hibernate.Jpademo.jparepo;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vipul.springboot.jpa.Hibernate.Jpademo.entity.Passport;

@Repository
public interface PassportSpringDataJpaRepository
        extends JpaRepository<Passport, Long> {

}
