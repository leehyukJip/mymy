package board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.BoardDAO;
import db.BoardVO;
import model.Action;
import model.ActionData;

public class FileDelete implements Action{
	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionData data = new ActionData();
		
		BoardVO vo = new BoardVO();
		
		vo.setId(Integer.parseInt(request.getParameter("id")));
		vo.setPw(request.getParameter("pw"));
		vo.setPname(request.getParameter("pname"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		vo.setUpfile(request.getParameter("file"));
		vo.setSeq(Integer.parseInt(request.getParameter("seq")));
		String msg = "인증실패";
		
		BoardDAO dao = new BoardDAO();
		
		if(dao.sch(vo) != null) {
			msg = "인증성공";	
			vo.setUpfile("");
			String path = request.getRealPath("upload");
			path = "E:\\newWorkSpace\\mvcJsp\\WebContent\\upload\\";
			
			System.out.println();
			new File(path+request.getParameter("file")).delete();
			dao.fileDelete(vo.getId());
			
		}
		dao.close();
		
		request.setAttribute("msg", msg);
		request.setAttribute("main", "modifyForm.jsp");
		request.setAttribute("data", vo);
		
		return data;
	}
}
