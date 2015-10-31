package io.github.thestigger.controller;

import com.mongodb.MongoClient;
import io.github.thestigger.dao.MongoDBContactDAO;
import io.github.thestigger.model.Contact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim on 10/14/15.
 */
@WebServlet("/contacts")
public class Contacts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
        MongoDBContactDAO contactDAO = new MongoDBContactDAO(mongo);
        List<Contact> contacts = contactDAO.readContacts();
        request.setAttribute("contacts", contacts);

        request.getRequestDispatcher("contacts.jsp").forward(request, response);
    }
}
