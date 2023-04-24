package com.example.demo.resources;

import com.example.demo.model.Category;
import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FilterReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
            status = Response.Status.OK;
        } else {
            status = Response.Status.NOT_FOUND;
        }
        return Response
                .status(status)
                .entity(post)
                .build();
    }


    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response createPost(String body) throws ParseException {

        Object postObj = new JSONParser().parse(body);
        JSONObject JsonPostObj = (JSONObject) postObj;
        String title = (String) JsonPostObj.get("title");
        String author = (String) JsonPostObj.get("author");
        String content = (String) JsonPostObj.get("content");

        Post newPost = postService.createPost(title, author, content);

        Response.Status status;
        if (newPost.getId() != 0) {
            status = Response.Status.CREATED;
        } else {
            status = Response.Status.BAD_REQUEST;
        }

        return Response
                .status(status)
                .entity(newPost)
                .build();
    }

}
