package com.example.demo.servlet;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoriesServlet", value = "/categories")
public class CategoriesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = new CategoryService().fetchAllCategories();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/categories-list.jsp").forward(request, response);
    }
}
