package com.chen.myblog.service;

import com.chen.myblog.pojo.Type;
import org.hibernate.type.ListType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Itypeservice {
    boolean saveType(Type type);

    void deleteType(Long id);

    Type updateType(Long id,Type type);

    Type getType(Long id);

    Page<Type> getall(Pageable pageable);

    List<Type>  findall();

    Type findType(String name);

    List<Type> listtype();

    List<Type> listtype(Integer size);
}
