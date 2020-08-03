package com.chen.myblog.webconfig.admin;

import com.chen.myblog.pojo.Type;
import com.chen.myblog.service.impl.Typeservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class Typecontroller {

    @Autowired
    private Typeservice typeservice;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model, HttpServletRequest request){   //设置分页参数
//        System.out.println("错误");
        model.addAttribute("page",typeservice.getall(pageable));

//        model.addAttribute("page",typeservice.findall());
        return "admin/types";
    }


    @GetMapping("/types/input")
    public String input(Model model){
//        System.out.println("第0步");
        model.addAttribute("type",new Type());

        return "admin/types-input";

    }

    @GetMapping("/types/{id}/input")
    public String edit(@PathVariable Long id, Model model){
        System.out.println("test1");
        model.addAttribute("type",typeservice.getType(id));

        System.out.println("test2");
        return "admin/types-input";


    }



    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){   //

        if(typeservice.findType(type.getName())!=null){
//            attributes.addFlashAttribute("message","数据已经存在");
            result.rejectValue("name","nameError","数据已经存在");
        }
        if(result.hasErrors()){
            return "admin/types-input";
        }
        boolean b = typeservice.saveType(type);
        if(b){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }

        return "redirect:/admin/types";
    }


    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,
                           @PathVariable Long id,RedirectAttributes attributes){
        Type type1 = typeservice.findType(type.getName());
        if(type1!=null){
            /*rejectValue自定义验证错误结果,第一个参数s为校验的数据，第二个参数s1为自定义错误字符串，第三个参数s2为自定义错误信息*/
            result.rejectValue("name","nameError","不能添加重复分类");
        }
        if(result.hasErrors()){ //如果校验结果存在错误
            return "admin/types-input";
        }
        Type t = typeservice.updateType(id,type);
        if(t == null){
            attributes.addFlashAttribute("message","更新失败");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String deletepost(@PathVariable Long id,RedirectAttributes attributes){
        typeservice.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";


    }

}
