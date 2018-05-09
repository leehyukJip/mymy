package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db1.BoardDAO;
import model2.Action;
import model2.ActionData;

public class List implements Action{

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ActionData res = new ActionData();
		
		int page = 1;
		int limit = 3;
		int pagelimit =4;
		
		
		if(request.getParameter("page")!=null && !request.getParameter("page").equals("")) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int start = (page-1)*limit+1;
		int end = page*limit;
		
		int startPage = (page-1)/pagelimit*pagelimit+1;
		int endPage = startPage+pagelimit-1;
		BoardDAO dao = new BoardDAO();
		int cnt = dao.totalCnt();
		
		int totalPage= cnt/limit;
		if(cnt%limit!=0) {
			totalPage+=1;
		}
		if(endPage>totalPage) {
			endPage=totalPage;
		}
		
		request.setAttribute("total", totalPage);
		request.setAttribute("start", start);
		request.setAttribute("page", page);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("data", dao.list(start,end));
		request.setAttribute("main", "list.jsp");
		return res;
	}
	
}
