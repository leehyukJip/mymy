package board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db1.BoardDAO;
import db1.BoardVO;
import model2.Action;
import model2.ActionData;

public class DeleteReg implements Action{
	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		
		int page = Integer.parseInt(request.getParameter("page"));
		System.out.println(page);
		String id = request.getParameter("id");
		BoardVO vo = new BoardVO();
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setPw(request.getParameter("pw"));
		
		BoardDAO dao = new BoardDAO();
		BoardVO res = dao.sch(vo);
		
		String msg ="암호 인증 실패";
		String url ="DeleteForm?id="+vo.getId()+"&page="+page;
		
		if(res!=null) {
			msg ="삭제 성공";
			url = "List?page="+page;
			if(!(res.getUpfile().equals(""))) {
				String path = request.getRealPath("upload");
				path = "E:\\newWorkSpace\\mvcJsp\\WebContent\\upload\\";
				new File(path+res.getUpfile()).delete();
			}
			dao.delete(vo.getId());
		}
		
		dao.close();
		
		request.setAttribute("main", "alert.jsp");
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return data;
	}
}
