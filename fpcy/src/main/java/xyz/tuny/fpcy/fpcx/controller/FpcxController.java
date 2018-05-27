package xyz.tuny.fpcy.fpcx.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.tuny.fpcy.fpcx.service.IFpmxService;
import xyz.tuny.fpcy.fpcx.service.IFpzbService;
import xyz.tuny.framework.web.controller.BaseController;
import xyz.tuny.framework.web.page.TableDataInfo;
import xyz.tuny.fpcy.fpcx.domain.Fpmx;
import xyz.tuny.fpcy.fpcx.domain.Fpzb;
import xyz.tuny.fpcy.fpcx.service.IFpmxService;
import xyz.tuny.fpcy.fpcx.service.IFpzbService;

import java.util.List;

/**
 * 操作日志记录
 * 
 * @author tuny
 */
@Controller
@RequestMapping("/fpgl/fpcx")
public class FpcxController extends BaseController
{
    private String prefix = "fpgl/fpcx";

    @Autowired
    private IFpzbService fpzbService;

    @Autowired
    private IFpmxService fpmxService;

    @RequiresPermissions("fpgl:fpcx:view")
    @GetMapping()
    public String fpcx()
    {
        return prefix + "/fpcx";
    }

    @RequiresPermissions("fpgl:fpcx:view")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Fpzb fpzb)
    {
        return fpzbService.selectFpcxList(getPageRequest(fpzb),fpzb);
    }

    @RequiresPermissions("fpgl:fpcx:detail")
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

}
