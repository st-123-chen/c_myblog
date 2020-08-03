package com.chen.myblog.service.impl;

import com.chen.myblog.Dao.ITypedao;
import com.chen.myblog.NULLException;
import com.chen.myblog.pojo.Type;
import com.chen.myblog.service.Itypeservice;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class Typeservice implements Itypeservice {

    @Autowired
    private ITypedao iTypedao;

    @Transactional
    @Override
    public boolean saveType(Type type) {
        Type save = iTypedao.save(type);
        return false;
    }
    @Transactional
    @Override
    public void deleteType(Long id) {
        iTypedao.deleteById(id);


    }
    @Transactional
    @Override
    public Type updateType(Long id,Type type) {
        Type type1 = iTypedao.findById(id).orElse(null);
        if(type1==null){
            throw new NULLException("该类型不存在");
        }
        BeanUtils.copyProperties(type,type1);
        return iTypedao.save(type1);
    }
    @Transactional
    @Override
    public Type getType(Long id) {

        return iTypedao.findById(id).orElse(null);
    }

//    @Transactional
    @Override
    public Page<Type> getall(Pageable pageable) {
        return iTypedao.findAll(pageable);
    }

    @Override
    public List<Type> findall() {
        return iTypedao.findAll();
    }


    @Override
    public Type findType(String name) {
        return iTypedao.findByName(name);
    }

    @Override
    public List<Type> listtype() {
        return iTypedao.findAll();
    }


    @Override
    public List<Type> listtype(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return iTypedao.findTop(pageable);
    }
}
