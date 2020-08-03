package com.chen.myblog.service.impl;

import com.chen.myblog.Dao.Iblogdao;
import com.chen.myblog.NULLException;
import com.chen.myblog.pojo.Type;
import com.chen.myblog.pojo.blog;
import com.chen.myblog.service.Iblogservice;
import com.chen.myblog.vo.blogquery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class blogservice implements Iblogservice {

    @Autowired
    private Iblogdao iblogdao;

    @Override
    public Page<blog> listblog(Pageable pageable) {
        return iblogdao.findAll(pageable);
    }

    @Override
    public blog getblog(Long id) {
        return iblogdao.getOne(id);
    }

    @Override
    public Page<blog> listblog(Pageable pageable, blogquery blog) {

        return iblogdao.findAll(new Specification<blog>() {
            @Override
            public Predicate toPredicate(Root<blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
               List<Predicate> predicates = new ArrayList<>();
                if (!"".equals(blog.getTitle()) && blog.getTitle() != null) {

                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));

                }
                if(blog.getTypid() !=null) {
                    System.out.println("2356");
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getTypid()));

                }
                if(blog.isRecommend()) {
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
                }
//                if(blog.isPublished()){   //判断查询条件之四:根据博客是否已发布查询，过滤掉状态为草稿的博客
//                    predicates.add(cb.equal(root.<Boolean>get("published"),blog.isPublished()));
//                }
//                if(blog.isDraft()){   //判断查询条件之五:根据博客是否是草稿，实现后台查询草稿博客功能
//                    predicates.add(cb.equal(root.<Boolean>get("published"),!blog.isDraft()));
//                }
                //criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                criteriaQuery.where(predicates.toArray(new Predicate[0]));
                    return null;
                }

        },pageable);

    }


    @Override
    public blog saveblog(blog blog) {
        if(blog.getId()!=null) {
            int a = 0;
            long b = (long) a;
            blog.setNewtime(new Date());
            blog.setViewnumbers(b);
            blog.setCreatetime(new Date());
        }else {
            blog.setNewtime(new Date());
        }
        return iblogdao.save(blog);
    }

    @Override
    public blog updateblog(Long id, blog blog) {
        blog blog1 = iblogdao.findById(id).orElse(null);
//        Optional<com.chen.myblog.pojo.blog> byId = iblogdao.findById(id);
        if(blog1==null){
            throw new NULLException("该类型不存在");
        }
        BeanUtils.copyProperties(blog,blog1);
        return iblogdao.save(blog1);
    }

    @Override
    public void deleteblog(Long id) {
        iblogdao.deleteById(id);
    }

    @Override
    public List<blog> listblogrecommend(Integer size) {
        Sort sort = Sort.by(Sort.Order.desc("blogs.size"));
        Pageable pageable = PageRequest.of(0, size, sort);

        return iblogdao.findTop(pageable);
    }

    @Override
    public Page<blog> listblog(String query, Pageable pageable) {
        return iblogdao.findByQuery(query,pageable);
    }
}
