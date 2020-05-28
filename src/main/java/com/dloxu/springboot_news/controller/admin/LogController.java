package com.dloxu.springboot_news.controller.admin;

import com.dloxu.springboot_news.model.Log;
import com.dloxu.springboot_news.model.Page;
import com.dloxu.springboot_news.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:日志管理控制器
 *
 * @param
 * @author dloxu
 * @return
 * @date 2020/2/29 14:16
 */
@RequestMapping("/admin/log")
@Controller
public class LogController {
    @Autowired
    private LogService logService;

    /**
     * Description:日志列表页面
     *
     * @param
     * @return
     * @author dloxu
     * @date 2020/2/29 14:16
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        model.setViewName("log/list");
        return model;
    }

    /**
     * Description:获取日志列表
     *
     * @param
     * @return
     * @author dloxu
     * @date 2020/2/29 14:16
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(Page page,
                                       @RequestParam(name = "content", required = false, defaultValue = "") String content
    ) {
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("content", content);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", logService.findList(queryMap));
        ret.put("total", logService.getTotal(queryMap));
        return ret;
    }

   /**
    * Description:添加日志
    * @author   dloxu
    * @param
    * @return
    * @date 2020/2/29 14:17
    */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Log log) {
        Map<String, String> ret = new HashMap<String, String>();
        if (log == null) {
            ret.put("type", "error");
            ret.put("msg", "请填写正确的日志信息！");
            return ret;
        }
        if (StringUtils.isEmpty(log.getContent())) {
            ret.put("type", "error");
            ret.put("msg", "请填写日志内容！");
            return ret;
        }
        log.setCreateTime(new Date());
        if (logService.add(log) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "日志添加失败，请联系管理员！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "日志添加成功！");
        return ret;
    }


    /**
     * Description:批量删除日志
     * @author   dloxu
     * @param
     * @return
     * @date 2020/2/29 14:17
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(String ids) {
        Map<String, String> ret = new HashMap<String, String>();
        if (StringUtils.isEmpty(ids)) {
            ret.put("type", "error");
            ret.put("msg", "选择要删除的数据！");
            return ret;
        }
        if (ids.contains(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        if (logService.delete(ids) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "日志删除失败，请联系管理员！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "日志删除成功！");
        return ret;
    }


}
