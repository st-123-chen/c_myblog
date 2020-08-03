package com.chen.myblog.pojo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
//@Table("my_blog")
public class blog {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Basic(fetch = FetchType.LAZY)
    @Lob    //@Lob注解声明大字段类型 第一次初始化时才有效，一般和@Basic懒加载一起使用，只有需要获取的时候才去查询；也可以直接去数据库内将该字段改为longtext类型
    private String content; //内容
    private String image;
    private String sign;
    private Long viewnumbers;
    private boolean agreement;      //赞赏
    private boolean copyright;      //转载
    private boolean comment;        //评论
    private boolean published;    //草稿
    private boolean recommend; //是否推荐

    @Temporal(TemporalType.TIMESTAMP)
    private Date createtime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date newtime;

    @ManyToOne
    private Type type;

    @ManyToMany(cascade = {CascadeType.ALL})
    private List<lable> lables = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<comment> comments = new ArrayList<>();

    @Transient
    private  String tagIds; //博客存在多个标签，定义一个属性存放；@Transient注解声明该属性不入数据库字段

    public blog() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Long getViewnumbers() {
        return viewnumbers;
    }

    public void setViewnumbers(Long viewnumbers) {
        this.viewnumbers = viewnumbers;
    }

    public boolean isAgreement() {
        return agreement;
    }

    public void setAgreement(boolean agreement) {
        this.agreement = agreement;
    }

    public boolean isCopyright() {
        return copyright;
    }

    public void setCopyright(boolean copyright) {
        this.copyright = copyright;
    }

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getNewtime() {
        return newtime;
    }

    public void setNewtime(Date newtime) {
        this.newtime = newtime;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<lable> getLables() {
        return lables;
    }

    public void setLables(List<lable> lables) {
        this.lables = lables;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<com.chen.myblog.pojo.comment> getComments() {
        return comments;
    }

    public void setComments(List<com.chen.myblog.pojo.comment> comments) {
        this.comments = comments;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public void init(){
        this.tagIds=tagsToIds(this.getLables());
    }
    //将数组转换为字符串分隔
    private String tagsToIds(List<lable> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (lable tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }
    @Override
    public String toString() {
        return "blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", sign='" + sign + '\'' +
                ", viewnumbers=" + viewnumbers +
                ", agreement=" + agreement +
                ", copyright=" + copyright +
                ", comment=" + comment +
                ", published=" + published +
                ", recommend=" + recommend +
                ", createtime=" + createtime +
                ", newtime=" + newtime +
                ", type=" + type +
                ", lables=" + lables +
                ", user=" + user +
                ", comments=" + comments +
                ", tagIds='" + tagIds + '\'' +
                '}';
    }
}
