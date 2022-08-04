package core.mvc;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonView implements View{
	Map<String, Object> o;
	
	public JsonView(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		o = createModel(req);
		render(req, resp);
	}
	
	private Map<String, Object> createModel(HttpServletRequest req){
		Enumeration<String> names = req.getAttributeNames();
		Map<String, Object> model = new HashMap<String, Object>();
		while(names.hasMoreElements()) {
			model.put(names.nextElement(), req.getAttribute(names.nextElement()));
		}
		return model;
	}

	@Override
	public void render(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print(mapper.writeValueAsString(o));
	}
}
