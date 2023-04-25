package com.example.demo.servlet;

import com.example.demo.service.CategoryService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "RemoveCategoryServlet", value = "/remove-category")
public class RemoveCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int catId = Integer.parseInt(request.getParameter("id"));
        new CategoryService().deleteCategory(catId);
        response.sendRedirect("/categories");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
