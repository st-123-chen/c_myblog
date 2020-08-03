package com.chen.myblog.service.impl;

import com.chen.myblog.Dao.Labledao;
import com.chen.myblog.NULLException;
import com.chen.myblog.pojo.lable;
import com.chen.myblog.service.Ilableservice;
//import com.sun.tools.javac.util.Convert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import static org.thymeleaf.util.ObjectUtils.convertToList;

@Service
public class Lableservice implements Ilableservice {
    @Autowired
    private Labledao labledao;

    @Override
    public lable savelable(lable tag) {
        return labledao.save(tag);
    }

    @Override
    public lable getlable(Long id) {
        return labledao.getOne(id);
    }

    @Override
    public lable getlableByName(String name) {
        return labledao.findByName(name);
    }

    @Override
    public Page<lable> listlable(Pageable pageable) {
        return labledao.findAll(pageable);
    }

    @Override
    public List<lable> listlable() {
        return labledao.findAll();
    }

    @Override
    public List<lable> listlableTop(Integer size) {
//        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
//        Pageable pageable=new PageRequest(0,size,sort);
        Sort sort = Sort.by(Sort.Order.desc("blogs.size"));
        Pageable pageable =PageRequest.of(0, size, sort);

        return labledao.findTop(pageable);
    }

    @Override
    public List<lable> listlable(String ids) {
        List<Long> tagIds = convertToList(ids);
        return labledao.findAllById(tagIds);

    }
    private List<Long> convertToList(String ids) { //将字符串转换为一个数组list
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new Long(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public lable updatelable(Long id, lable lable) {
        lable one = labledao.findById(id).orElse(null);
        if(one==null){
            throw new NULLException("不存在该lable");
        }
        BeanUtils.copyProperties(lable,one);
        return labledao.save(one);

    }

    @Override
    public void deletelable(Long id) {
        labledao.deleteById(id);

    }
}
