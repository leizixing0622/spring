package com.imooc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.cons.CommonConstant;
import com.imooc.entities.Board;
import com.imooc.entities.Page;
import com.imooc.service.ForumService;

@Controller
public class BroadManageController extends BaseController {

	@Autowired
	private ForumService forumService;
	
	@RequestMapping(value = "/board/listBoardTopics-{boardId}", method = RequestMethod.GET)
	public ModelAndView listBoardTopics(@PathVariable Integer boardId,@RequestParam(value = "pageNo", required = false) Integer pageNo){
		
		ModelAndView mv = new ModelAndView();
		//加入board
		Board b = forumService.getBoardById(boardId);
		mv.addObject("board",b);
		//加入分页后的topics的Page
		pageNo = pageNo == null ? 1 : pageNo;
		Page p = forumService.getPagedTopics(boardId, pageNo, CommonConstant.PAGE_SIZE);
		mv.addObject("topics", p);
		mv.setViewName("/listBoardTopics");
		return mv;
		
	}
}
