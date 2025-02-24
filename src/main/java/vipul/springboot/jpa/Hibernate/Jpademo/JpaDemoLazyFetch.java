package vipul.springboot.jpa.Hibernate.Jpademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import vipul.springboot.jpa.Hibernate.Jpademo.entity.Employee;
import vipul.springboot.jpa.Hibernate.Jpademo.entity.Passport;
import vipul.springboot.jpa.Hibernate.Jpademo.entity.Student;
import vipul.springboot.jpa.Hibernate.Jpademo.jparepo.EmployeeSpringDataJpaRepository;
import vipul.springboot.jpa.Hibernate.Jpademo.jparepo.PassportSpringDataJpaRepository;
import vipul.springboot.jpa.Hibernate.Jpademo.jparepo.StudentSpringDataJpaRepository;

import java.util.List;
import java.util.Optional;


@SpringBootApplication
public class JpaDemoLazyFetch implements CommandLineRunner {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeSpringDataJpaRepository repo;

	@Autowired
	private PassportSpringDataJpaRepository passrepo;

	@Autowired
	private StudentSpringDataJpaRepository srepo;


	public static void main(String[] args) {
		SpringApplication.run(JpaDemoLazyFetch.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Passport p = new Passport("Rohit","Indian");
//		passrepo.save(p);
//		Employee e = new Employee("Rohit",6000.0,p);
//		repo.save(e);
//		Student s = new Student("Rohit",400);
//		srepo.save(s);
//
//
//		Passport p1 = new Passport("Mohit","Canadian");
//		passrepo.save(p1);
//		Employee e1 = new Employee("Mohit",9000.0,p1);
//		repo.save(e1);
//		Student s1 = new Student("Mohit",500);
//		srepo.save(s1);


		Passport p1 = new Passport("Rohit","Australian");
//		passrepo.save(p1); if we are using cascade.all then we dont need to explicitly save passport object

		Passport p2 = new Passport("Sheetal", "Indian");
//		passrepo.save(p2);

		Passport p3 = new Passport("Rohit1", "American");
//		passrepo.save(p3);

		Passport p4 = new Passport("Sheetal1","Canadian");
//		passrepo.save(p4);
		Passport p5 = new Passport("Rohit2", "Asian");
//		passrepo.save(p5);

		Passport p6 = new Passport("Sheetal2","German");
//		passrepo.save(p6);

		Student s1 = new Student("Rohit", 500, p1);
		srepo.save(s1);

		Student s2 = new Student("Sheetal", 150, p2);
		srepo.save(s2);

		Student s3 = new Student("Rohit1", 500, p3);
		srepo.save(s3);

		Student s4 = new Student("Sheetal1", 150, p4);
		srepo.save(s4);

		Student s5 = new Student("Rohit2", 500, p5);
		srepo.save(s5);

		Student s6 = new Student("Sheetal2", 150, p6);
		srepo.save(s6);

//		getEmpById(1l);
//		getEmpByName("Rohit");
//		getEmpByNameAndSalary("Rohit",6000.0);

		List<Student> lst  = srepo.studentWithPatternInName();
		System.out.println(lst);

		List<Student> lst1 = srepo.studentWithPatternInNameUsingNativeQuery();
		System.out.println(lst1);

		Sort sort = Sort.by(Sort.Direction.ASC, "name");
		System.out.println("sorted employees based on names in ASC order : " + srepo.findAll(sort));

		Sort sort1 = Sort.by(Sort.Direction.DESC, "name");
		System.out.println("sorted employees based on names in DESC order : " + srepo.findAll(sort1));

		Sort sort2 = Sort.by(Sort.Direction.ASC, "totalMarks");
		System.out.println("sorted marks in ASC order : " + srepo.findAll(sort2));


		long ct = srepo.count();


		//Pageable secondPageWithFiveElements = PageRequest.of(1, 5);
		for(int i=0;i<ct/2;i++) {
			System.out.println("Batch:"+(i+1));
			Pageable firstPageWithTwoElements = PageRequest.of(i, 2);
			Page<Student> p = srepo.findAll(firstPageWithTwoElements);
			p.get().forEach(s -> System.out.println(s2));
		}

	}


	public void getEmpById(Long id){

		Optional<Employee> x = repo.findById(1l);
		if (x.isPresent()) {
			Employee ee = x.get();
			Passport p1 = ee.getEmppass();
			System.out.println(p1);
		}
	}

	public void getEmpByName(String name){
		Optional<Employee> x = repo.findByName(name);
		if (x.isPresent()) {
			System.out.println(x.get());
		}
	}

	public void getEmpByNameAndSalary(String name,Double salary){
		Optional<Employee> x = repo.findByNameAndSalary(name, salary);
		if (x.isPresent()) {
			System.out.println(x.get());
		}
	}


}
