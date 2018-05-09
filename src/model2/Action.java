package model2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// 으흠?
	public ActionData execute(HttpServletRequest request, HttpServletResponse response);
}
