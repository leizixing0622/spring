package com.imooc.service;

import java.util.List;

import com.imooc.entities.Board;
import com.imooc.entities.Page;
import com.imooc.entities.Post;
import com.imooc.entities.Topic;

public interface ForumService {
	
	public void addBoard(Board board);
	public List<Board> getAllBoards();
	public void addTopic(Topic topic);
	public void addPost(Post post);
	public Page getPagedTopics(int boardId,int pageNo,int pageSize);
}
