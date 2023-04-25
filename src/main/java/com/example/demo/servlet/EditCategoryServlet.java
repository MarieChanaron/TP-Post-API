package com.example.demo.servlet;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "EditCategoryServlet", value = "/edit-category")
public class EditCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("categoryId", request.getParameter("id"));
        request.setAttribute("categoryName", request.getParameter("name"));
        request.getRequestDispatcher("WEB-INF/edit-category-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Category category = new Category(id, name);
        new CategoryService().updateCategory(category);
        response.sendRedirect("/categories");
    }
}
