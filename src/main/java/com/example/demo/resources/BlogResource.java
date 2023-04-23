package com.example.demo.resources;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("/posts")
public class BlogResource {

    public final PostService postService = new PostService();

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response fetchAllPosts() {
        List<Post> allPosts;
        allPosts = postService.fetchAllPosts();
        return Response
                .status(Response.Status.OK)
                .entity(allPosts)
                .build();
    }

    @Path("/{id}")
    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response fetchPost(@PathParam("id") String id) {
        int postId = Integer.parseInt(id);
        Post post = postService.fetchPostById(postId);
        System.out.println(post);
        Response.Status status;
        if (post.getId() != 0) {
            status = Response.Status.FOUND;
        } else {
            status = Response.Status.NOT_FOUND;
        }
        return Response
                .status(status)
                .entity(post)
                .build();
    }

    /* RESTE A FAIRE
    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response createPost(@PathParam("id") String id) {
        int postId = Integer.parseInt(id);
        Post post = postService.fetchPostById(postId);
        System.out.println(post);
        Response.Status status;
        if (post.getId() != 0) {
            status = Response.Status.FOUND;
        } else {
            status = Response.Status.NOT_FOUND;
        }
        return Response
                .status(status)
                .entity(post)
                .build();
    }
     */

}
