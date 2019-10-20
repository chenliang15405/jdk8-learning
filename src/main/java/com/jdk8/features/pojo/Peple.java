package com.jdk8.features.pojo;

import lombok.Data;

import java.util.Objects;

/**
 * @auther alan.chen
 * @time 2019/10/19 11:19 AM
 */
@Data
public class Peple {

    private Integer id;

    private String name;

    private Integer age;

    private Double number;

    private String status;


    public Peple(Integer id) {
        this.id = id;
    }

    public Peple() {
    }

    public Peple(Integer id, String name, Integer age, Double number, String status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.number = number;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Peple employee = (Peple) o;
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
