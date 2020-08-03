package com.chen.myblog.webconfig.admin;


import com.chen.myblog.pojo.User;
import com.chen.myblog.pojo.blog;
import com.chen.myblog.service.impl.Lableservice;
import com.chen.myblog.service.impl.Typeservice;
import com.chen.myblog.service.impl.blogservice;
import com.chen.myblog.vo.blogquery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class Blogcontroller {

    @Autowired
    private com.chen.myblog.service.impl.blogservice blogservice;

    @Autowired
    private Typeservice typeservice;
    @Autowired
    private Lableservice lableservice;

//    @GetMapping("/blogs")
//    public String blogs(){
//        return "admin/blogs";
//    }

    @GetMapping("/blogs")
    public String types(@PageableDefault(size = 5,sort = {"newtime"},direction = Sort.Direction.DESC) Pageable pageable, Model model, HttpServletRequest request, blogquery blog){   //设置分页参数
//        System.out.println("错误");
//        System.out.println("oneerror");
        model.addAttribute("page",blogservice.listblog(pageable,blog));
//        System.out.println("twoerror");

        model.addAttribute("types",typeservice.findall());
//        model.addAttribute("page",typeservice.findall());
        return "admin/blogs";
    }


    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 5,sort = {"newtime"},direction = Sort.Direction.DESC) Pageable pageable, Model model, HttpServletRequest request, blogquery blog){   //设置分页参数
//        System.out.println("错误");

        model.addAttribute("page",blogservice.listblog(pageable,blog));

//        model.addAttribute("page",typeservice.findall());
        return "admin/blogs :: bloglist";
    }

    @GetMapping("/blogs/input")
    public String inoutblog(Model model){
        model.addAttribute("blog",new blog());
        model.addAttribute("tags",lableservice.listlable());
        model.addAttribute("types",typeservice.findall());

//        model.addAttribute("blog",getblog);
        return "admin/blogs-input";

    }
    @GetMapping("/blogs/{id}/input")
    public String addblog(@PathVariable Long id, Model model){
//        model.addAttribute("blog",blogservice.getblog(id));
//        model.addAttribute("tags",lableservice.listlable());
//        model.addAttribute("types",typeservice.findall());
        blog getblog = blogservice.getblog(id);
        getblog.init();
        model.addAttribute("blog",getblog);
        return "admin/blogs-input";

    }

    @PostMapping("/blogs/input")
    public String post(Model model, blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User)session.getAttribute("user"));
        blog.setType(typeservice.getType(blog.getType().getId()));
        blog.setLables(lableservice.listlable(blog.getTagIds()));
        blog saveblog = blogservice.saveblog(blog);
        if(saveblog==null){
            attributes.addFlashAttribute("message","新增失败");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }

        return "redirect:/admin/blogs";
    }


    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        blogservice.deleteblog(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blogs";
    }

}
