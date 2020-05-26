package com.example.demo.entity;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chenrunzheng
 * @since 2020/3/24 16:02
 */
@Entity
@Table(name = "test_person_info", schema = "crz_test", catalog = "")
public class TestPersonInfoEntity {

    private int id;
    private String name;
    private int age;
    private String addr;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Basic
    @Column(name = "addr")
    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestPersonInfoEntity that = (TestPersonInfoEntity) o;
        return id == that.id &&
                age == that.age &&
                Objects.equals(name, that.name) &&
                Objects.equals(addr, that.addr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, addr);
    }
}
