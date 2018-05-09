package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db1.BoardDAO;
import db1.BoardVO;
import model2.Action;
import model2.ActionData;

public class ReplyForm implements Action{
	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		BoardDAO dao = new BoardDAO();
		
		BoardVO vo = dao.detail(Integer.parseInt(request.getParameter("id")));
		System.out.println(vo.getTitle());
		vo.setTitle("[답변]"+vo.getTitle());
		System.out.println(vo.getTitle());
		vo.setPname("");
		vo.setContent("[답변]"+vo.getContent());
		
		request.setAttribute("data", vo);
		request.setAttribute("main", "replyForm.jsp");
		dao.close();
		return new ActionData();
	}
}
