package org.phonebook.phonebook.servlet;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.phonebook.phonebook.database.MySQLController;

@WebServlet("/contacts")
public class ContactsServlet extends HttpServlet {
    private final MySQLController mySQLController = new MySQLController();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");

        PrintWriter printWriter = response.getWriter();
        printWriter.println(new ObjectMapper().writeValueAsString(mySQLController.getContacts()));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/json");

        String name = request.getParameter("name");
        String number = request.getParameter("number");
        mySQLController.addNewContact(name, number);

        PrintWriter printWriter = response.getWriter();
        printWriter.println(new ObjectMapper().writeValueAsString(mySQLController.getContacts()));
    }
}