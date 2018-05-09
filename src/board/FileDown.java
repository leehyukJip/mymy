package board;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.Action;
import model2.ActionData;

public class FileDown implements Action{
	@Override
	public ActionData execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String fileName = request.getParameter("file");
		String path = request.getRealPath("upload");
		path = "E:\\newWorkSpace\\mvcJsp\\WebContent\\upload\\";
		
		ActionData data = new ActionData();
		
		try {
			
			String en = URLEncoder.encode(fileName,"utf-8");	
			response.setHeader("Content-Disposition", "attachment;filename="+en);
			ServletOutputStream sos = response.getOutputStream();
			
			FileInputStream fis = new FileInputStream(path+fileName);
			
			byte[] buf = new byte[1024];
			
			while(fis.available()>0) {
				int len = fis.read(buf);
				sos.write(buf,0,len);
			}
			
			fis.close();
			sos.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}
