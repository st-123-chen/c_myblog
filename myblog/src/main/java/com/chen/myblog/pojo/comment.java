package com.chen.myblog.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class comment {

    @Id
    @GeneratedValue
    private Long id;
    private String Nickname;
    private String email;
    @Lob  //大型字段
    private String content;
    private String headimage;
    @Temporal(TemporalType.TIMESTAMP)
    private Date newdate;

    @OneToMany(mappedBy = "parantcomment")
    private List<comment> comments = new ArrayList<>();

    @ManyToOne
    private comment parantcomment;

    @ManyToOne
    private blog blog;

    public comment() {
    }


}
