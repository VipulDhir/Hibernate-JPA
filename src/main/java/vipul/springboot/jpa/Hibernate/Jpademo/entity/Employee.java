package vipul.springboot.jpa.Hibernate.Jpademo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;


@Entity
public class Employee {


    private String name;
    private double salary;

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="p_id")
    Passport emppass;

    public Employee(String name, double salary, Passport emppass) {
        this.name = name;
        this.salary = salary;
        this.emppass = emppass;
    }

    public Passport getEmppass() {
        return emppass;
    }

    public void setEmppass(Passport emppass) {
        this.emppass = emppass;
    }

    protected Employee() {

    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", id=" + id +
                ", emppass=" + emppass +
                '}';
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
