package com.chen.myblog.vo;

public class blogquery {

    private String title;
    private Long typid;
    private boolean recommend;

    public blogquery() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTypid() {
        return typid;
    }

    public void setTypid(Long typid) {
        this.typid = typid;
    }

    public boolean isRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    @Override
    public String toString() {
        return "blogquery{" +
                "title='" + title + '\'' +
                ", typid=" + typid +
                ", recommend=" + recommend +
                '}';
    }
}
