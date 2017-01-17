package com.imooc.entities;

public class Board extends BaseDomain {

	private int board_id;
	private String board_name;
	private String board_desc;
	private int topic_num;
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getBoard_name() {
		return board_name;
	}
	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}
	public String getBoard_desc() {
		return board_desc;
	}
	public void setBoard_desc(String board_desc) {
		this.board_desc = board_desc;
	}
	public int getTopic_num() {
		return topic_num;
	}
	public void setTopic_num(int topic_num) {
		this.topic_num = topic_num;
	}
	
	
	
}
