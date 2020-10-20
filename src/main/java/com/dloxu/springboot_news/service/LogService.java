package com.dloxu.springboot_news.service;

import com.dloxu.springboot_news.model.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description:日志接口
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:20
 */
@Service
public interface LogService {
	 int add(Log log);
	 int add(String content);
	 List<Log> findList(Map<String, Object> queryMap);
	 int getTotal(Map<String, Object> queryMap);
	 int delete(String ids);
}
