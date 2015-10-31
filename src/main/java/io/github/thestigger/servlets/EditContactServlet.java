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
@WebServlet("/editContact")
public class EditContactServlet extends HttpServlet {

    private static final long serialVersionUID = -6554920927964049383L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || "".equals(id)) {
            throw new ServletException("id missing for edit operation");
        }
        System.out.println("Contact edit request with id = " + id);
        MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
        MongoDBContactDAO contactDAO = new MongoDBContactDAO(mongo);
        Contact c = new Contact();
        c.setId(id);
        c = contactDAO.readContact(c);
        request.setAttribute("contact", c);
        List<Contact> contacts = contactDAO.readContacts();
        request.setAttribute("contacts", contacts);

        request.getRequestDispatcher("contacts.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        // TODO add other fields

        if ((name == null || "".equals(name))
                || (surname == null || "".equals(surname))
                || (phoneNumber == null || "".equals(phoneNumber))
                || (email == null || "".equals(email))) {
            request.setAttribute("error", "Mandatory Parameters Missing");
            MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
            MongoDBContactDAO contactDAO = new MongoDBContactDAO(mongo);
            Contact c = new Contact();
            c.setId(id);
            c.setName(name);
            c.setSurname(surname);
            c.setPhoneNumber(phoneNumber);
            c.setEmail(email);
            request.setAttribute("contact", c);
            List<Contact> contacts = contactDAO.readContacts();
            request.setAttribute("contacts", contacts);

            request.getRequestDispatcher("contacts.jsp").forward(request, response);
        } else {
            MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
            MongoDBContactDAO contactDAO = new MongoDBContactDAO(mongo);

            Contact c = new Contact();
            c.setId(id);
            c.setName(name);
            c.setSurname(surname);
            c.setPhoneNumber(phoneNumber);
            c.setEmail(email);

            contactDAO.updateContact(c);
            System.out.println("Contact edited Successfully with id = " + c.getId());
            request.setAttribute("success", "Person edited Successfully");
            List<Contact> contacts = contactDAO.readContacts();
            request.setAttribute("contacts", contacts);

            request.getRequestDispatcher("contacts.jsp").forward(request, response);
        }
    }
}
