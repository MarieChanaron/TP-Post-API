package com.example.demo.servlet;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keywords = request.getParameter("keywords");
        List<Post> postsFound = new PostService().searchByKeywords(keywords);
        request.setAttribute("posts", postsFound);
        request.getRequestDispatcher("/WEB-INF/post-list.jsp").forward(request, response);
    }

}
