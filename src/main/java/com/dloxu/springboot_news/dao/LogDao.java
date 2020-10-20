package com.dloxu.springboot_news.dao;


import com.dloxu.springboot_news.model.Log;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description:系统日志类dao
 * @author   dloxu
 * @param
 * @return
 * @date 2020/2/29 14:34
 */
@Repository
public interface LogDao {
	 int add(Log log);
	 List<Log> findList(Map<String, Object> queryMap);
	 int getTotal(Map<String, Object> queryMap);
	 int delete(String ids);
}
