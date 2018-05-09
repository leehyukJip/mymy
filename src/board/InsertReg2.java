package board;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import db.BoardDAO;
import db.BoardVO;
import model.Action;
import model.ActionData;


public class InsertReg2 implements Action{

	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ActionData data = new ActionData();
		
		BoardVO vo = new BoardVO();
		
		
		try {
			Collection<Part> parts = request.getParts();
			
			for (Part part : parts) {
				System.out.println(part.getName());
				if(!part.getName().equals("file")) {
					switch(part.getName()){
						case "title":
							vo.setTitle(request.getParameter("title"));
							break;
						case "pname":
							vo.setPname(request.getParameter("pname"));
							break;
						case "pw":
							vo.setPw(request.getParameter("pw"));
							break;
						case "content":
							vo.setContent(request.getParameter("content"));
							break;				
					}
		
				}else {
					vo.setUpfile(fileUpload(part,request));
					System.out.println(vo.getUpfile());
				}
	
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		int id = new BoardDAO().insert(vo);
		
		data.setRedirect(true);
		
		data.setPath("Detail?id="+id);
		
		return data;
	}
	
	String fileUpload(Part pp, HttpServletRequest request) {
		try {
			 
			
			if(pp.getContentType()!=null) {
			
				String fileName="";
				
				for(String hh: pp.getHeader("Content-Disposition").split(";")) {
					if(hh.trim().startsWith("filename")) {
						fileName=
					hh.substring(hh.indexOf("=")+1).trim().replaceAll("\"", "");
					}
				}
				
				if(!fileName.equals("")) {
					return fileSave(pp, fileName, request);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		
		return "";
	}
	
	
	String fileSave(Part pp, String fileName, HttpServletRequest request) {
		
		int pos = fileName.lastIndexOf(".");
		
		String fileDo = fileName.substring(0, pos);
		String exp = fileName.substring(pos);
		
		String path = request.getRealPath("upload")+"\\";
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return fileName;
	}
	
}
