package com.example.demo.resources;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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

    @Path("/{id}")
    @DELETE
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response deletePost(@PathParam("id") String id) {

        boolean success = postService.deletePost(Integer.parseInt(id));

        Response.Status status;
        if (success) {
            status = Response.Status.OK;
        } else {
            status = Response.Status.BAD_REQUEST;
        }

        return Response
                .status(status)
                .build();
    }


    @Path("/{id}")
    @PUT
    @Produces(value = MediaType.APPLICATION_JSON)
    @Consumes(value = MediaType.APPLICATION_JSON)
    public Response amendPost(@PathParam("id") String id, String body) throws ParseException {

        int postId = Integer.parseInt(id);
        System.out.println(postId);

        // Get values from the request body
        Object postObj = new JSONParser().parse(body);
        JSONObject JsonPostObj = (JSONObject) postObj;
        String title = (String) JsonPostObj.get("title");
        String author = (String) JsonPostObj.get("author");
        String content = (String) JsonPostObj.get("content");

        Post postFound = postService.fetchPostById(postId);
        System.out.println(postFound);

        Response.Status status;
        Post responseBody;

        // If post found : modify the post and send 200 OK
        // If post not found = create the post and send 201 CREATED
        if (postFound.getId() != 0) {
            boolean success = postService.updatePost(postId, title, author, content);
            if (success) {
                status = Response.Status.OK;
                responseBody = postFound;
            } else {
                status = Response.Status.NOT_MODIFIED;
                responseBody = postFound;
            }
        } else {
            responseBody = postService.createPost(title, author, content);
            if (responseBody.getId() != 0) {
                status = Response.Status.CREATED;
            } else {
                status = Response.Status.BAD_REQUEST;
            }
        }

        return Response
                .status(status)
                .entity(responseBody)
                .build();
    }

}
