package com.imooc.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.imooc.entities.Board;
import com.imooc.entities.Emp;
import com.imooc.entities.Loginlog;
import com.imooc.entities.Topic;

public interface TopicDao {

	@Insert("insert into topic (topic_id,board_id,topic_title,empno,create_time,last_post,topic_views,topic_replies,digest) "
					+ "values (#{topic_id},#{board_id},#{topic_title},#{empno},#{create_time},#{last_post},#{topic_views},#{topic_replies},#{digest})")
	public void insert(Topic topic);
	@Delete("delete from topic where topic_id=#{topic_id}")
	public void delete(int topic_id);
	@Update("update topic set "
			+ "topic_id = ${topic_id},board_id = ${board_id},topic_title = ${topic_title},"
			+ "empno = ${empno},create_time = ${create_time},last_post = ${last_post},"
			+ "topic_views = ${topic_views},topic_replies = ${topic_replies},digest = ${digest}"
			+ " where topic_id = ${topic_id}")
	public void update(Topic topic);
	@Select("select * from topic where topic_id=#{topic_id}")
	public Topic findById(int topic_id);
	@Select("select count(*) from topic where board_id = #{board_id} order by last_post desc")
	public int getCount(int board_id);
	public List<Topic> selectPage(Topic topic); 
}
