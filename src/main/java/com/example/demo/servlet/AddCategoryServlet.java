package com.example.demo.servlet;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddCategoryServlet", value = "/add-category")
public class AddCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request
                .getRequestDispatcher("/WEB-INF/add-category-form.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryName = request.getParameter("name");
        Category newCategory = new Category(categoryName);
        new CategoryService().insertCategory(newCategory);

        response.sendRedirect("/categories");
    }
}
