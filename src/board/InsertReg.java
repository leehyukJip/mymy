package board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sun.xml.internal.ws.api.pipe.Fiber;

import db.BoardDAO;
import db.BoardVO;
import model.Action;
import model.ActionData;

public class InsertReg implements Action{

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ActionData data = new ActionData();
		
		BoardVO vo = new BoardVO();
		
		vo.setTitle(request.getParameter("title"));
		vo.setPname(request.getParameter("pname"));
		vo.setPw(request.getParameter("pw"));
		vo.setContent(request.getParameter("content"));
		vo.setUpfile(fileUpload(request));
		
		System.out.println(vo.getUpfile());
		
		int id = new BoardDAO().insert(vo);
		data.setRedirect(true);
		data.setPath("Detail?id="+id);
		return data;
	}
	public String fileUpload(HttpServletRequest request) {
		String fileName="";
		try {
			Part pp = request.getPart("file");
			if(pp.getContentType()!=null) {
				for(String hh: pp.getHeader("Content-Disposition").split(";")) {
					if(hh.trim().startsWith("filename")) {
						fileName = hh.substring(hh.indexOf("=")+1)
								.trim().replace("\"", "");
					}
				}
				if(!(fileName.equals(""))) {
					return fileSave(pp, fileName, request);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return "";
	}

	
	String fileSave(Part pp, String fileName, HttpServletRequest request) {
		int pos = fileName.lastIndexOf(".");
		
		String fileDo = fileName.substring(0, pos);
		String exp = fileName.substring(pos);
		//주석주석
		String path = request.getRealPath("up")+"\\";
		path = "E:\\newWorkSpace\\mvcJsp\\WebContent\\upload\\";
		
		int cnt =0;
		
		File ff = new File(path+fileName);
		
		while(ff.exists()) {
			fileName = fileDo+"_"+(cnt++)+exp;
			ff = new File(path+fileName);
		}
		try {
			pp.write(path+fileName);
			pp.delete();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return fileName;
	}
}
