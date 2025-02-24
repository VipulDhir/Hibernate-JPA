package vipul.springboot.jpa.Hibernate.Jpademo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;


@Entity
@Table(name="stud")
public class Student {

    @Column(name="studname")
    String name;

    int totalMarks;

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="p_id")
    Passport studpass;

    protected Student() {}

    public Student(String name, int totalMarks) {
        this.name = name;
        this.totalMarks = totalMarks;
    }

    public Student(String name, int totalMarks, Passport studpass) {
        this.name = name;
        this.totalMarks = totalMarks;
        this.studpass = studpass;
    }

    public Passport getstudpass() {
        return studpass;
    }

    public void setstudpass(Passport studpass) {
        this.studpass = studpass;
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", totalMarks=" + totalMarks +
                ", id=" + id +
                ", studpass=" + studpass +
                '}';
    }


    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public int getTotalMarks() {
        return totalMarks;
    }

    public void setTotalMarks(int totalMarks) {
        this.totalMarks = totalMarks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}

