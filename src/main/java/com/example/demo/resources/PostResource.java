package com.example.demo.resources;

import com.example.demo.model.Post;
import com.example.demo.service.PostService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;


@Path("/posts")
public class PostResource {

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
    public Response createPost(PostDto dto) {

        Post newPost = postService.createPost(dto.getTitle(), dto.getAuthor(), dto.getContent());

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
    public Response amendPost(@PathParam("id") int postId, PostDto dto) {

        Post postFound = postService.fetchPostById(postId);

        Response.Status status;
        Post responseBody;

        // If post found : modify the post and send 200 OK
        // If post not found = create the post and send 201 CREATED
        if (postFound.getId() == 0) {
            responseBody = postService.createPost(dto.getTitle(), dto.getAuthor(), dto.getContent());
            if (responseBody.getId() != 0) {
                status = Response.Status.CREATED;
            } else {
                status = Response.Status.BAD_REQUEST;
            }
        } else {
            boolean success = postService.updatePost(postId, dto.getTitle(), dto.getAuthor(), dto.getContent());
            if (success) {
                status = Response.Status.OK;
            } else {
                status = Response.Status.NOT_MODIFIED;
            }
            responseBody = postFound;
        }

        return Response
                .status(status)
                .entity(responseBody)
                .build();
    }

}
