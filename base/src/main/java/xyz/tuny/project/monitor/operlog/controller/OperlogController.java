package xyz.tuny.project.monitor.operlog.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.tuny.framework.aspectj.lang.annotation.Log;
import xyz.tuny.framework.web.controller.BaseController;
import xyz.tuny.framework.web.domain.JSON;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.monitor.operlog.domain.OperLog;
import xyz.tuny.project.monitor.operlog.service.IOperLogService;
import xyz.tuny.framework.web.controller.BaseController;
import xyz.tuny.framework.web.domain.JSON;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.project.monitor.operlog.domain.OperLog;

/**
 * 操作日志记录
 * 
 * @author tuny
 */
@Controller
@RequestMapping("/monitor/operlog")
public class OperlogController extends BaseController
{
    private String prefix = "monitor/operlog";

    @Autowired
    private IOperLogService operLogService;

    @RequiresPermissions("monitor:operlog:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/operlog";
    }

    @RequiresPermissions("monitor:operlog:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(OperLog operLog)
    {
        return operLogService.selectOperLogList(getPageRequest(operLog),operLog);
    }

    @RequiresPermissions("monitor:operlog:batchRemove")
    @Log(title = "监控管理", action = "操作日志-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        if (operLogService.batchDeleteOperLog(ids))
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("monitor:operlog:detail")
    @GetMapping("/detail/{operId}")
    public String detail(@PathVariable("operId") Long deptId, Model model)
    {
        OperLog operLog = operLogService.selectOperLogById(deptId);
        model.addAttribute("operLog", operLog);
        return prefix + "/detail";
    }
}
