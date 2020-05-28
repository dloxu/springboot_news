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
 * Description:��־���������
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
     * Description:��־�б�ҳ��
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
     * Description:��ȡ��־�б�
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
    * Description:�����־
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
            ret.put("msg", "����д��ȷ����־��Ϣ��");
            return ret;
        }
        if (StringUtils.isEmpty(log.getContent())) {
            ret.put("type", "error");
            ret.put("msg", "����д��־���ݣ�");
            return ret;
        }
        log.setCreateTime(new Date());
        if (logService.add(log) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "��־���ʧ�ܣ�����ϵ����Ա��");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "��־��ӳɹ���");
        return ret;
    }


    /**
     * Description:����ɾ����־
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
            ret.put("msg", "ѡ��Ҫɾ�������ݣ�");
            return ret;
        }
        if (ids.contains(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        if (logService.delete(ids) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "��־ɾ��ʧ�ܣ�����ϵ����Ա��");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "��־ɾ���ɹ���");
        return ret;
    }


}
