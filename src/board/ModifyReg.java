package board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BoardDAO;
import db.BoardVO;
import model.Action;
import model.ActionData;

public class ModifyReg implements Action{
	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String page = request.getParameter("page");
		System.out.println(page);
		ActionData data = new ActionData();
		
		BoardVO vo = new BoardVO();
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setPw(request.getParameter("pw"));
		vo.setPname(request.getParameter("pname"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		BoardDAO dao = new BoardDAO();
		BoardVO res = dao.sch(vo);
		
		String msg ="실패";
		String url ="ModifyForm?id="+vo.getId();
		
		if(res != null) {
			if(request.getParameter("file")!=null) {
				vo.setUpfile(request.getParameter("file"));
			}else {
				vo.setUpfile(new InsertReg().fileUpload(request));
			}
			msg = "성공";
			url = "Detail?id="+vo.getId()+"&page="+page;
			System.out.println(url);
			dao.modify(vo);
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("main", "alert.jsp");
		
		dao.close();
		return data;
	}
}
