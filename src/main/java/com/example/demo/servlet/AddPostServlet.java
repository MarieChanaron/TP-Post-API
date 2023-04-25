package com.example.demo.servlet;

import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import com.example.demo.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/add-post")
public class AddPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get all categories (to fill the "select" HTML element)
        List<Category> categories = new CategoryService().fetchAllCategories();
        request.setAttribute("categories", categories);
        // Affiche la vue
        request
                .getRequestDispatcher("/WEB-INF/add-post-form.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Recupere les données provenant du formulaire
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String content = req.getParameter("content");
        int categoryId = Integer.parseInt(req.getParameter("category"));

        // Ajoute l'article via le service
        new PostService().createPost(title, author, content, categoryId);

        // Redirige vers la page /posts
        resp.sendRedirect("posts");
    }
}
