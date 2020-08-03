package com.chen.myblog.service;

import com.chen.myblog.pojo.Type;
import com.chen.myblog.pojo.lable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface Ilableservice {
    lable savelable(lable tag);

    lable getlable(Long id);

    lable getlableByName(String name);

    Page<lable> listlable(Pageable pageable);

    List<lable> listlable();

    List<lable> listlableTop(Integer size);

    List<lable> listlable(String ids);

    lable updatelable(Long id,lable lable);

    void deletelable(Long id);



}
