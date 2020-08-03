package com.chen.myblog.webconfig.admin;

import com.chen.myblog.pojo.User;
import com.chen.myblog.service.impl.Userservice;

//import com.sun.tools.javac.code.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.*;

@Controller
@RequestMapping("/admin")
public class admincontroller {

    @Autowired
    private Userservice userservice;

    @GetMapping
//    @RequestMapping("/login")
    public String loginpage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String logincommit(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) throws ClassNotFoundException, SQLException {

//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_blog", "root", "1416741959xo");
//        Statement s = conn.createStatement();
//        String sql;
//        sql = "SELECT username, password FROM user";
//        ResultSet rs = s.executeQuery(sql);
//        System.out.println("*****");

        User user = userservice.logincheck(username, password);
//        User user = new User();

//        String username1 = null;
//        while(rs.next()) {
//            username1 = rs.getString("username");
//        }
//        System.out.println("index成功");

        if(user!=null){
//        if(username.equals(username1)){
//            System.out.println("下次一定");
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {;
            redirectAttributes.addFlashAttribute("messages","用户名和密码错误");
//            System.out.println("下次一定")
            return "redirect:/admin";
        }


    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/admin";


    }
}
