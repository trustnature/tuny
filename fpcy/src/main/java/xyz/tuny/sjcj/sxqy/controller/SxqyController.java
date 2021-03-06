package xyz.tuny.sjcj.sxqy.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.tuny.fpcy.fpcx.domain.Fpmx;
import xyz.tuny.fpcy.fpcx.domain.Fpzb;
import xyz.tuny.fpcy.fpcx.service.IFpmxService;
import xyz.tuny.fpcy.fpcx.service.IFpzbService;
import xyz.tuny.framework.aspectj.lang.annotation.Log;
import xyz.tuny.framework.web.controller.BaseController;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.system.post.domain.Post;
import xyz.tuny.project.system.role.domain.Role;

import java.util.List;

/**
 * 操作日志记录
 * 
 * @author tuny
 */
@Controller
@RequestMapping("/sjcj/sxqy")
public class SxqyController extends BaseController
{
    private String prefix = "sjcj/sxqy";

    @Autowired
    private IFpzbService fpzbService;

    @Autowired
    private IFpmxService fpmxService;

    @RequiresPermissions("sjcj:sxqy:view")
    @GetMapping()
    public String qycx()
    {
        return prefix + "/sxqy";
    }

    @RequiresPermissions("sjcj:sxqy:view")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Fpzb fpzb)
    {
        return fpzbService.selectFpcxList(getPageRequest(fpzb),fpzb);
    }

    @RequiresPermissions("sjcj:sxqy:detail")
    @GetMapping("/detail/{fpzbId}")
    public String detail(@PathVariable("fpzbId") String fpzbId, Model model)
    {
        Fpzb fpzb = fpzbService.selectFpzbById(fpzbId);
        List<Fpmx> fpmxs = fpmxService.selectFpmxDetailByZbid(fpzbId);
        model.addAttribute("fpzb", fpzb);
        model.addAttribute("fpmxs", fpmxs);
        String fplx = fpzb.getFplx();
        return prefix + "/cyjg" + fplx;
    }

    /**
     * 新增用户
     */
    @RequiresPermissions("sjcj:sxqy:add")
    @Log(title = "数据采集", action = "失信企业-新增企业")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

}
