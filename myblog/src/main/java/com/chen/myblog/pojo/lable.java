package com.chen.myblog.pojo;

import com.sun.istack.NotNull;
import org.apache.logging.log4j.message.Message;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class lable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "lables")
    private List<blog> blogs = new ArrayList<>();

    public lable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "lable{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", blogs=" + blogs +
                '}';
    }
}
