package co.com.mdt.infrastructure.servlets;

import javax.ejb.EJB;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import co.com.mdt.application.ejb.GuestDao;
import co.com.mdt.domain.model.Guest;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class GuestServlet
 */
@WebServlet(name="GuestServlet", urlPatterns={"/guest"})
public class GuestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	 // Injected DAO EJB:
    @EJB GuestDao guestDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
    	// Display the list of guests:
            request.setAttribute("guests", guestDao.getAllGuests());
            request.getRequestDispatcher("/guest.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	// Handle a new guest:
        String name = request.getParameter("name");
        if (name != null)
            guestDao.persist(new Guest(name));
		doGet(request, response);
	}

}
