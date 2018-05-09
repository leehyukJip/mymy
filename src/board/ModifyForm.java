package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BoardDAO;
import model.Action;
import model.ActionData;

public class ModifyForm implements Action{ 
	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ActionData data = new ActionData();
		BoardDAO dao = new BoardDAO();
		request.setAttribute("data", dao.detail(Integer.parseInt(request.getParameter("id"))));
		request.setAttribute("main", "modifyForm.jsp");
		dao.close();
		return data;
	}
}
