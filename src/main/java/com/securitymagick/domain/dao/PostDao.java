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

import com.securitymagick.domain.Post;
import com.securitymagick.domain.PostComment;


@Component
@ComponentScan({"com.securitymagick"})
public class PostDao {

	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public PostDao() {
		EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder()
    		.setType(EmbeddedDatabaseType.H2)
    		.build();
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public List<Post> getPosts() {
		String sql = "SELECT * FROM posts";
		Map<String, Object> params = new HashMap<String, Object>();
		List<Post> result = namedParameterJdbcTemplate.query(sql, params, new PostMapper()  );
		return result;
		
	}
	
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
	
	public Integer getNextPostId() {
		String sql = "SELECT MAX(id) FROM posts";
		Map<String, Object> params = new HashMap<String, Object>();
		Integer maxId = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
		return maxId + 1;
	}
	
	public Integer getNextCommentId() {
		String sql = "SELECT MAX(id) FROM comments";
		Map<String, Object> params = new HashMap<String, Object>();
		Integer maxId = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
		return maxId + 1;
	}
	
	public void addPost(Post p) {
		Integer postId = this.getNextPostId();
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "INSERT INTO posts VALUES (" +  postId + ", '" + p.getTitle() + "', '" + p.getImageName() + "', '" + p.getAuthor() + "')";
		namedParameterJdbcTemplate.update(sql, params);
	}
	
		public void updatePost(Post p) {
		Integer postId = this.getNextPostId();
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "UPDATE posts SET title='" + p.getTitle() + "' WHERE id ="+  p.getId().toString();
		namedParameterJdbcTemplate.update(sql, params);
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
	
	public void deleteComment(String commentId) {
		String sql = "DELETE FROM comments WHERE id = " + commentId ;
		Map<String, Object> params = new HashMap<String, Object>();
		namedParameterJdbcTemplate.update(sql, params);
	}
	
	private static final class PostMapper implements RowMapper<Post> {

		public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
			Post post = new Post();
			post.setId(rs.getInt("id"));
			post.setTitle(rs.getString("title"));
			post.setImageName(rs.getString("imageName"));
			post.setAuthor(rs.getString("author"));
			return post;
		}
	}
	
	private static final class PostCommentMapper implements RowMapper<PostComment> {

		public PostComment mapRow(ResultSet rs, int rowNum) throws SQLException {
			PostComment postComment = new PostComment();
			postComment.setId(rs.getInt("id"));
			postComment.setPostid(rs.getString("postid"));
			postComment.setComment(rs.getString("theComment"));
			postComment.setUsername(rs.getString("author"));
			return postComment;
		}
	}
}