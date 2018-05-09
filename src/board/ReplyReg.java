package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db1.BoardDAO;
import db1.BoardVO;
import model2.Action;
import model2.ActionData;

public class ReplyReg implements Action{
	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		BoardVO vo = new BoardVO();
		String page = request.getParameter("page");
		
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setTitle(request.getParameter("title"));
		vo.setPname(request.getParameter("pname"));
		vo.setPw(request.getParameter("pw"));
		vo.setContent(request.getParameter("content"));
		
		int id = new BoardDAO().reply(vo);
		System.out.println(id);
		ActionData data = new ActionData();
		
		data.setRedirect(true);
		data.setPath("Detail?id="+id+"&page="+page);
		return data;
	}
}
