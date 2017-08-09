package com.pangpang.domain.optimisticlock;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by leewake on 2017/8/9 0009.
 */

@Entity
@Table(name = "t_student")
public class Student {

    @Id
    @GenericGenerator(name = "PKUUID", strategy = "uuid2")
    @GeneratedValue(generator = "PKUUID")
    @Column(length = 36)
    private String id;

    @Version
    private int version;

    @Column
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", version=" + version +
                ", name='" + name + '\'' +
                '}';
    }
}
