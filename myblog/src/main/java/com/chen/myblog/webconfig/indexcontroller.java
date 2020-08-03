package com.chen.myblog.webconfig;

import com.chen.myblog.NULLException;
import com.chen.myblog.service.Iblogservice;
import com.chen.myblog.service.impl.Lableservice;
import com.chen.myblog.service.impl.Typeservice;
import com.chen.myblog.service.impl.blogservice;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class indexcontroller {
    @Autowired
    private blogservice blogservice;
    @Autowired
    private Typeservice typeservice;
    @Autowired
    private Lableservice lableservice;
    @GetMapping("/index/{id}/{name}")
    public String index(@PathVariable int id, @PathVariable String name){
//        int i = 9/0;
//        String blog = null;
//        if(blog==null){
//            throw  new NULLException("博客不存在");
//        }
        System.out.println("就这？"+id+name);
        return "index";
    }

    @GetMapping("/")
    public String index(@PageableDefault(size = 8,sort = {"newtime"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model,
                        @RequestHeader(value = "User-Agent",required=true) String userAgent){
        model.addAttribute("page",blogservice.listblog(pageable));
        model.addAttribute("types",typeservice.listtype(6));
        model.addAttribute("tags",lableservice.listlableTop(10));
        model.addAttribute("recommendBlogs",blogservice.listblogrecommend(10));
        /*使用定义好的正则表达式获取请求头信息的设备信息*/
 /*       Matcher matcher = pattern.matcher(userAgent);
        String deviceMessage = null;
        if (matcher.find()) {
            deviceMessage = matcher.group(1).trim();
            logger.info("通过userAgent解析出访问者机型：" + deviceMessage);
        }*/

        //todo
        //防止进网页未获取到ip地址卡顿
/*        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
         HttpServletRequest request=attributes.getRequest();
        String ip=request.getRemoteAddr();
        String address = GetAddressByIpUtils.ipToAddress(ip);
        logger.info("访问者设备信息：" + userAgent);
        logger.info("访问者ip地址：" + ip);
        logger.info("访问者实际地址：" + address);*/

        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 8,sort = {"newtime"},direction = Sort.Direction.DESC) Pageable pageable,
                        Model model,@RequestParam String query,
                        @RequestHeader(value = "User-Agent",required=true) String userAgent){
        model.addAttribute("page",blogservice.listblog("%"+query+"%",pageable));
        model.addAttribute("query",query);

        return "search";
    }
}
