package com.jdk8.features.pojo;

import lombok.Data;

import java.util.Objects;

/**
 * @auther alan.chen
 * @time 2019/10/19 11:19 AM
 */
@Data
public class Employee {

    private Integer id;

    private String name;

    private Integer age;

    private Double number;


    public Employee(Integer id) {
        this.id = id;
    }

    public Employee() {
    }

    public Employee(Integer id, String name, Integer age, Double number) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(age, employee.age) &&
                Objects.equals(number, employee.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, number);
    }

}
