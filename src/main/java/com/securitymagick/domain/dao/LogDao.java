package com.securitymagick.domain.dao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import com.securitymagick.domain.dao.H2DataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.securitymagick.domain.LogMessage;


@Component
@ComponentScan({"com.securitymagick"})
public class LogDao {

	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public LogDao() {
		EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.H2)
    		.build();
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<LogMessage> getLogMessages() {
		String sql = "SELECT * FROM logtable";
		Map<String, Object> params = new HashMap<String, Object>();
		List<LogMessage> result = namedParameterJdbcTemplate.query(sql, params, new LogMessageMapper()  );
		return result;
		
	}
	
	
	public Integer getNextLogId() {
		String sql = "SELECT MAX(id) FROM logtable";
		Map<String, Object> params = new HashMap<String, Object>();
		Integer maxId = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
		if (maxId == null ) {
			return 1;
		}
		return maxId + 1;
	}	
	
	public void addLog(LogMessage lm) {
		Integer logId = this.getNextLogId();
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "INSERT INTO logtable VALUES (" +  logId + ", '" + lm.getUseragent() + "', '" + lm.getUsername() + "', '" + lm.getMessage() +  "')";
		namedParameterJdbcTemplate.update(sql, params);
	}	
	
	
	private static final class LogMessageMapper implements RowMapper<LogMessage> {

		public LogMessage mapRow(ResultSet rs, int rowNum) throws SQLException {
			LogMessage logMessage = new LogMessage();
			logMessage.setId(rs.getInt("id"));
			logMessage.setUsername(rs.getString("username"));
			logMessage.setUseragent(rs.getString("useragent"));
			logMessage.setMessage(rs.getString("logmessage"));
			return logMessage;
		}
	}

}