package vipul.springboot.jpa.Hibernate.Jpademo.exceptions;

public class studentException extends RuntimeException {
    public studentException(Long id) {
        super("Student ID not found: " + id);
    }
}
