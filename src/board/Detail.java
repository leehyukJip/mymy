package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.NEW;

import db1.BoardDAO;
import model2.Action;
import model2.ActionData;

public class Detail implements Action{

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		int id =Integer.parseInt(request.getParameter("id"));
		
		BoardDAO dao = new BoardDAO();
		
		dao.addcnt(id);
		
		request.setAttribute("data",dao.detail(id));
		request.setAttribute("main", "detail.jsp");
		dao.close();
		return data;
	}

}
