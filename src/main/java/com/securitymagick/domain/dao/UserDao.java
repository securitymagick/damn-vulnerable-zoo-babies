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

import com.securitymagick.domain.RegistrationForm;
import com.securitymagick.domain.User;


@Component
@ComponentScan({"com.securitymagick"})
public class UserDao {

	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public UserDao() {
		EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.H2)
    		.build();
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<User> getUsers() {
		String sql = "SELECT * FROM users";
		Map<String, Object> params = new HashMap<String, Object>();
		List<User> result = namedParameterJdbcTemplate.query(sql, params, new UserMapper()  );
		return result;
		
	}
	
	public List<User> getUser(String username) {
		String sql = "SELECT * FROM users WHERE username='" + username + "'";
		Map<String, Object> params = new HashMap<String, Object>();
		List<User> result = namedParameterJdbcTemplate.query(sql, params, new UserMapper()  );
		return result;	
	}
	
	/*
	public List<Post> getPostsWithComments() {
		List<Post> posts = this.getPosts();
		List<PostComment> comments = this.getComments();
		for (PostComment c1 : comments) {
			for (Post p1: posts) {
				if (p1.getId().toString().equals(c1.getPostid())) {
					p1.addComment(c1.getUsername() + " said: " + c1.getComment());
				}
			}
		}
		return posts;
	}
	
	public List<PostComment> getComments() {
		String sql = "SELECT * FROM comments";
		Map<String, Object> params = new HashMap<String, Object>();
		List<PostComment> result = namedParameterJdbcTemplate.query(sql, params, new PostCommentMapper()  );
		return result;
	}

	
	public Integer getNextCommentId() {
		String sql = "SELECT MAX(id) FROM comments";
		Map<String, Object> params = new HashMap<String, Object>();
		Integer maxId = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
		return maxId + 1;
	}
	

	

	
	public void addComment(PostComment pc) {
		Integer commentId = this.getNextCommentId();
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "INSERT INTO comments VALUES (" +  commentId + ", " + pc.getPostid() + ", '" + pc.getComment() + "', '" + pc.getUsername() + "')";
		namedParameterJdbcTemplate.update(sql, params);
	}
	
	public void deletePost(Integer postId) {
		String sql = "DELETE FROM posts WHERE id = " + postId.toString() ;
		Map<String, Object> params = new HashMap<String, Object>();
		namedParameterJdbcTemplate.update(sql, params);
	}
	*/
	public Integer getNextUserId() {
		String sql = "SELECT MAX(id) FROM users";
		Map<String, Object> params = new HashMap<String, Object>();
		Integer maxId = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
		return maxId + 1;
	}	
	
	public void addUser(RegistrationForm u) {
		Integer userId = this.getNextUserId();
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "INSERT INTO users VALUES (" +  userId + ", '" + u.getUsername() + "', '" + u.getPassword() + "', '" + u.getFavorite() +  "', 1, 0)";
		namedParameterJdbcTemplate.update(sql, params);
	}	
	
	public void updateUser(User u) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "UPDATE users SET favorite='" + u.getFavorite() + "', password='" + u.getPassword() + "', isUser=" + u.getIsUser() + ", isAdmin=" + u.getIsAdmin() + " WHERE id =" +  u.getId().toString();
		namedParameterJdbcTemplate.update(sql, params);
	}
	
	public void updatePassword(String username, String password) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "UPDATE users SET password='" + password + "' WHERE username = '" + username + "'";
		namedParameterJdbcTemplate.update(sql, params);
	}
	
	public void deleteUser(Integer userId) {
		String sql = "DELETE FROM users WHERE id = " + userId.toString();
		Map<String, Object> params = new HashMap<String, Object>();
		namedParameterJdbcTemplate.update(sql, params);
	}
	
	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setFavorite(rs.getString("favorite"));
			user.setIsUser(rs.getInt("isUser"));
			user.setIsAdmin(rs.getInt("isAdmin"));
			return user;
		}
	}

}