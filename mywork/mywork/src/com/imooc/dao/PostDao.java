package com.imooc.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.imooc.entities.Emp;
import com.imooc.entities.Post;
import com.imooc.entities.Topic;

public interface PostDao {

	@Insert("insert into post (post_id,board_id,topic_id,empno,post_type,post_title,post_text,create_time) "
			+ "values (#{post_id},#{board_id},#{topic_id},#{empno},#{post_type},#{post_title},#{post_text},#{create_time})")
	public void insert(Post post);
	@Delete("delete from post where post_id=#{post_id}")
	public void delete(int post_id);
	@Update("update post set "
		+ "post_id = ${post_id},board_id = ${board_id},topic_id = ${topic_id},"
		+ "empno = ${empno},post_type = ${post_type},post_title = ${post_title},"
		+ "post_text = ${post_text},create_time = ${create_time},"
		+ " where post_id = ${post_id}")
	public void update(Post post);
	@Select("select * from post where post_id=#{post_id}")
	public Post findById(int post_id);
	
}
