package xyz.tuny.project.monitor.logininfor.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.tuny.framework.aspectj.lang.annotation.Log;
import xyz.tuny.framework.web.controller.BaseController;
import xyz.tuny.framework.web.domain.JSON;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.monitor.logininfor.domain.Logininfor;
import xyz.tuny.project.monitor.logininfor.service.ILogininforService;
import xyz.tuny.framework.web.controller.BaseController;
import xyz.tuny.framework.web.domain.JSON;
import xyz.tuny.framework.web.page.TableDataInfo;

/**
 * 系统访问记录
 * 
 * @author tuny
 */
@Controller
@RequestMapping("/monitor/logininfor")
public class LogininforController extends BaseController
{
    private String prefix = "monitor/logininfor";

    @Autowired
    private ILogininforService logininforService;

    @RequiresPermissions("monitor:logininfor:view")
    @GetMapping()
    public String logininfor()
    {
        return prefix + "/logininfor";
    }

    @RequiresPermissions("monitor:logininfor:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Logininfor logininfor)
    {
        return logininforService.selectLogininforList(getPageRequest(logininfor),logininfor);
    }

    @RequiresPermissions("monitor:logininfor:batchRemove")
    @Log(title = "监控管理", action = "登录日志-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        if (logininforService.batchDeleteLogininfor(ids))
        {
            return JSON.ok();
        }
        return JSON.error();
    }
}
