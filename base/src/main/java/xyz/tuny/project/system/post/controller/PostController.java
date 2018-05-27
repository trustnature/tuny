package xyz.tuny.project.system.post.controller;

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
import xyz.tuny.project.system.post.domain.Post;
import xyz.tuny.project.system.post.service.IPostService;
import xyz.tuny.framework.web.controller.BaseController;
import xyz.tuny.framework.web.domain.JSON;
import xyz.tuny.framework.web.page.TableDataInfo;

/**
 * 岗位信息操作处理
 * 
 * @author tuny
 */
@Controller
@RequestMapping("/system/post")
public class PostController extends BaseController
{
    private String prefix = "system/post";

    @Autowired
    private IPostService postService;

    @RequiresPermissions("system:post:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/post";
    }

    @RequiresPermissions("system:post:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Post post)
    {
        return postService.selectPostList(getPageRequest(post),post);
    }

    /**
     * 删除
     */
    @Log(title = "系统管理", action = "岗位管理-删除岗位")
    @RequiresPermissions("system:post:remove")
    @RequestMapping("/remove/{postId}")
    @ResponseBody
    public JSON remove(@PathVariable("postId") Long postId)
    {
        Post post = postService.selectPostById(postId);
        if (post == null)
        {
            return JSON.error("岗位不存在");
        }
        if (postService.deletePostById(postId))
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @RequiresPermissions("system:post:batchRemove")
    @Log(title = "系统管理", action = "岗位管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        if (postService.batchDeletePost(ids))
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 新增岗位
     */
    @Log(title = "系统管理", action = "岗位管理-新增岗位")
    @RequiresPermissions("system:post:add")
    @GetMapping("/add")
    public String add(Model model)
    {
        return prefix + "/add";
    }

    /**
     * 修改岗位
     */
    @Log(title = "系统管理", action = "岗位管理-修改岗位")
    @RequiresPermissions("system:post:edit")
    @GetMapping("/edit/{postId}")
    public String edit(@PathVariable("postId") Long postId, Model model)
    {
        Post post = postService.selectPostById(postId);
        model.addAttribute("post", post);
        return prefix + "/edit";
    }

    /**
     * 保存岗位
     */
    @Log(title = "系统管理", action = "岗位管理-保存岗位")
    @RequiresPermissions("system:post:save")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(Post post)
    {
        if (postService.savePost(post))
        {
            return JSON.ok();
        }
        return JSON.error();
    }

}
