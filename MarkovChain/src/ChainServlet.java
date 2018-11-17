

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChainServlet
 */
@WebServlet("/Chain")
public class ChainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String numWordsStr = request.getParameter("numWords");
		if(idStr == null || numWordsStr == null) {
			response.getWriter().print("Need id and numWords parameter");
			response.setStatus(400);
			return;
		}
		
		try {
			int id = Integer.parseInt(idStr);
			int numWords = Integer.parseInt(numWordsStr);
			String s = Chain.generate(id, numWords);
			if(s != null) {
				response.getWriter().print(s);
			} else {
				response.getWriter().print("You must first insert words with this id!");
				response.setStatus(400);
				return;
			}
			
			
		} catch (NumberFormatException e) {
			response.getWriter().print("id and numWords must be numbers");
			response.setStatus(400);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(e.getMessage());
			response.setStatus(400);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String str = request.getParameter("str");
		if(idStr == null || str == null) {
			response.getWriter().print("Need id and str parameter");
			response.setStatus(400);
			return;
		}
		
		try {
			int id = Integer.parseInt(idStr);
			Chain.insert(id, str);
			response.getWriter().print("Words inserted!");
			response.setStatus(200);
			return;
		} catch (NumberFormatException e) {
			response.getWriter().print("id must be number");
			response.setStatus(400);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(e.getMessage());
			response.setStatus(400);
			return;
		}
	}

}
