package xyz.tuny.project.system.user.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import xyz.tuny.framework.web.controller.BaseController;
import xyz.tuny.project.system.menu.domain.Menu;
import xyz.tuny.project.system.menu.service.IMenuService;
import xyz.tuny.project.system.user.domain.User;
import xyz.tuny.framework.web.controller.BaseController;
import xyz.tuny.project.system.user.domain.User;

/**
 * 首页 业务处理
 * 
 * @author tuny
 */
@Controller
public class IndexController extends BaseController
{
    @Autowired
    private IMenuService menuService;

    // 系统首页
    @GetMapping("/index")
    public String index(Model model)
    {
        // 取身份信息
        User user = getUser();
        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUserId(user.getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("user", user);
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main()
    {
        return "main";
    }

}
