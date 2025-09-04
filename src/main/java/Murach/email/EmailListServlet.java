package Murach.email;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import Murach.bussiness.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/emailList")
public class EmailListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "/index.html";
		GregorianCalendar curData = new GregorianCalendar();
		int curYear = curData.get(Calendar.YEAR);
		req.setAttribute("curYear", curYear);

		String action = req.getParameter("action");

		if (action == null) {
			action = "join"; // default action
		}

		if (action.equals("join")) {
			url = "/index.jsp"; // the "join" page
		} else if (action.equals("add")) {
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String email = req.getParameter("email");

			User user = new User(firstName, lastName, email);

			String message;
			if (firstName == null || lastName == null || email == null || firstName.isEmpty() || lastName.isEmpty()
					|| email.isEmpty()) {
				message = "Please fill out all three text boxes";
				url = "/index.jsp";
			}else {
				message = null;
				url = "/thanks.jsp";
			}
			
			req.setAttribute("user", user);
			req.setAttribute("message", message);
			req.setAttribute("curYear", curYear);
			System.out.println(user);
		}
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
