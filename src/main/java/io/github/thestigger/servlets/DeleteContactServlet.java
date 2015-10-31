package io.github.thestigger.servlets;

import com.mongodb.MongoClient;
import io.github.thestigger.dao.MongoDBContactDAO;
import io.github.thestigger.model.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by maxim on 10/15/15.
 */
@WebServlet("/deleteContact")
public class DeleteContactServlet extends HttpServlet {

    private static final long serialVersionUID = 6798036766148281767L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for delete operation");
        }
        MongoClient mongo = (MongoClient) request.getServletContext()
                .getAttribute("MONGO_CLIENT");
        MongoDBContactDAO contactDAO = new MongoDBContactDAO(mongo);
        Contact c = new Contact();
        c.setId(id);
        contactDAO.deleteContact(c);
        System.out.println("Contact deleted successfully with id = " + id);
        request.setAttribute("success", "Contact deleted successfully");
        List<Contact> contacts = contactDAO.readContacts();
        request.setAttribute("contacts", contacts);

        request.getRequestDispatcher("contacts.jsp").forward(request, response);
    }
}
