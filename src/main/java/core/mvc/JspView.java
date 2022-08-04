package core.mvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JspView implements View{
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
    
	private static final Logger log = LoggerFactory.getLogger(JspView.class);
	private String viewName;
	
	public JspView(String url, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.viewName = url;
		render(request, response);
	}
	
	@Override
	public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
			response.sendRedirect(viewName.substring(DEFAULT_REDIRECT_PREFIX.length()));
            log.debug("Redirect");
            return;
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(viewName);
        rd.forward(request, response);
	}
}
