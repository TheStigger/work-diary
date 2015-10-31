package io.github.thestigger.servlets;

import com.mongodb.MongoClient;
import io.github.thestigger.dao.MongoDBContactDAO;
import io.github.thestigger.model.Contact;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by maxim on 10/14/15.
 */
@WebServlet("/addContact")
public class AddContactServlet extends HttpServlet {

    private static final long serialVersionUID = -7060758261496829905L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            request.getRequestDispatcher("contacts.jsp").forward(request, response);
        } else {
            Contact c = new Contact();
            c.setName(name);
            c.setSurname(surname);
            c.setPhoneNumber(phoneNumber);
            c.setEmail(email);

            System.out.println("Getting Mongo Client");
            MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
            System.out.println(mongo);
            MongoDBContactDAO contactDAO = new MongoDBContactDAO(mongo);
            contactDAO.createContact(c);
            System.out.println("Contact Added Successfully with id = " + c.getId());
            request.setAttribute("success", "Person Added Successfully");
            List<Contact> contacts = contactDAO.readContacts();
            request.setAttribute("contacts", contacts);

            request.getRequestDispatcher("contacts.jsp").forward(request, response);
        }
    }
}
