package com.chen.myblog.webconfig.admin;

import com.chen.myblog.pojo.lable;
import com.chen.myblog.service.impl.Lableservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

public class lablecontroller {
    @Autowired
    private Lableservice tagService;

    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
                               Pageable pageable, Model model) {
        model.addAttribute("page",tagService.listlable(pageable));
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new lable());
        return "admin/tags-input";
    }

    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("tag", tagService.getlable(id));
        return "admin/tags-input";
    }


    @PostMapping("/tags")
    public String post(@Valid lable tag, BindingResult result, RedirectAttributes attributes) {
        lable tag1 = tagService.getlableByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        lable t = tagService.savelable(tag);
        if (t == null ) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }


    @PostMapping("/tags/{id}")
    public String editPost(@Valid lable tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes) {
        lable tag1 = tagService.getlableByName(tag.getName());
        if (tag1 != null) {
            result.rejectValue("name","nameError","不能添加重复的标签");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        lable t = tagService.updatelable(id,tag);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        tagService.deletelable(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
