package com.imooc.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.imooc.entities.Board;
import com.imooc.entities.Emp;
import com.imooc.entities.Loginlog;

public interface BoardDao {

		@Insert("insert into board (board_id,board_name,board_desc,topic_num) values (#{board_id},#{board_name},#{board_desc},#{topic_num})")
		public void insert(Board board);
		@Delete("delete from board where board_id=#{board_id}")
		public void delete(int board_id);
		@Update("update board set board_id = ${board_id},board_name = ${board_name},board_desc = ${board_desc},topic_num = ${topic_num} where board_id = ${board_id}")
		public void update(Board board);
		@Select("select * from board where board_id=#{board_id}")
		public Board findById(int board_id);

}
