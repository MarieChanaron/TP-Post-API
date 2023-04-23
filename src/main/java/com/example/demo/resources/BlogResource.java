package com.example.demo.resources;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("/posts")
public class BlogResource {

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response fetchAllPosts() {
        List<Post> allPosts;
        allPosts = new PostService().fetchAllPosts();
        return Response
                .status(Response.Status.FOUND)
                .entity(allPosts)
                .build();
    }

    /*
    @Path("/{id}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response fetchPost() {
        Post post;
        Post = new PostService().fetchAllPosts();
        return Response
                .status(Response.Status.FOUND)
                .entity(allPosts)
                .build();
    }
    */


}
