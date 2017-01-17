package com.imooc.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.dao.BoardDao;
import com.imooc.dao.EmpDao;
import com.imooc.dao.PostDao;
import com.imooc.dao.TopicDao;
import com.imooc.entities.Board;
import com.imooc.entities.Emp;
import com.imooc.entities.Page;
import com.imooc.entities.Post;
import com.imooc.entities.Topic;

@Service
public class ForumServiceImpl implements ForumService{
	
	@Autowired
	private EmpDao empDao;
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private PostDao postDao;
	
	@Override
	public void addBoard(Board board) {

		boardDao.insert(board);
		
	}
	
	@Override
	public void addTopic(Topic topic) {
		//�����������1
		Board board = (Board)boardDao.findById(topic.getBoard_id());
		board.setTopic_num(board.getTopic_num()+1);
		boardDao.update(board);
		//�û����ּ�10
		Emp emp = empDao.findById(topic.getEmpno());
		emp.setCredit(emp.getCredit()+10);
		empDao.updateEmp(emp);
		//���浱ǰ����
		topicDao.insert(topic);
		//���浱ǰ������
		Post post = new Post();
		post.setBoard_id(topic.getBoard_id());
		post.setTopic_id(topic.getTopic_id());
		post.setEmpno(topic.getEmpno());
		post.setPost_type(2);
		post.setPost_title(topic.getTopic_title());
		post.setCreate_time(new Date());
		postDao.insert(post);
	}

	@Override
	public void addPost(Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Board> getAllBoards() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page getPagedTopics(int boardId, int pageNo, int pageSize) {
		long totalCount = (long)topicDao.getCount(boardId);
		if (totalCount < 1)
		return new Page();
		int startIndex = Page.getStartOfPage(pageNo, pageSize);
		Topic t=new Topic();
		t.setPage_begin(startIndex);
		t.setPage_size(pageSize);
		List l=topicDao.selectPage(t);
		return new Page(startIndex, totalCount, pageSize, l);
	}

	
	
}
