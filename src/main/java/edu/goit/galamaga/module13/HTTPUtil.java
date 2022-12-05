package edu.goit.galamaga.module13;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.goit.galamaga.module13.post.Post;
import edu.goit.galamaga.module13.comment.Comment;
import edu.goit.galamaga.module13.todos.Todos;
import edu.goit.galamaga.module13.user.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.NoSuchElementException;

public class HTTPUtil {

    private static final Gson gson = new Gson();

    public static HttpResponse<String> createUser(String uri, User user) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();

        return sendRequest(request);

    }

    private static HttpResponse<String> sendRequest(HttpRequest request) {

        try {
            return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;

    }

    public static HttpResponse<String> updateUser(String uri, int userID, User user) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + userID))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(user)))
                .build();

        return sendRequest(request);

    }

    public static HttpResponse<String> deleteUser(String uri, int userID) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + userID))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        return sendRequest(request);

    }

    public static List<User> getUsers(String uri) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = sendRequest(request);

        if (response != null) {
            Type listType = new TypeToken<List<User>>() {
            }.getType();
            return gson.fromJson(response.body(), listType);
        } else {
            return null;
        }

    }

    public static User getUser(String uri, int userId) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + userId))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = sendRequest(request);

        if (response != null) {
            return gson.fromJson(response.body(), User.class);
        } else {
            return null;
        }

    }

    public static List<User> getUser(String uri, String userName) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "?username=" + userName))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = sendRequest(request);

        if (response != null) {
            Type listType = new TypeToken<List<User>>() {
            }.getType();
            return gson.fromJson(response.body(), listType);
        } else {
            return null;
        }

    }

    public static boolean saveCommentsToJSON(String uriUsers, String uriPosts, int userId) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriUsers + "/" + userId + "/posts"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        int lastPostID = 0;

        HttpResponse<String> response = sendRequest(request);

        Type listType = new TypeToken<List<Post>>() {
        }.getType();

        List<Post> posts = gson.fromJson(response.body(), listType);

        lastPostID = posts.stream().mapToInt(Post::getId).max().orElseThrow(NoSuchElementException::new);

        request = HttpRequest.newBuilder()
                .uri(URI.create(uriPosts + "/" + lastPostID + "/comments"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        File theDir = new File("src/main/resources");
        if (!theDir.exists()){
            theDir.mkdirs();
        }

        try (FileWriter fileWriter = new FileWriter("src/main/resources/user-" + userId +
                "-post-" + lastPostID + ".json")
        ) {

            response = sendRequest(request);

            listType = new TypeToken<List<Comment>>() {
            }.getType();

            List<Comment> comments = gson.fromJson(response.body(), listType);

            fileWriter.write(gson.toJson(comments));

            fileWriter.flush();

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static List<Todos> getUserOpenTask(String uri, int userID) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/" + userID + "/todos"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response = sendRequest(request);

        if (response != null) {
            Type listType = new TypeToken<List<Todos>>() {
            }.getType();

            return gson.fromJson(response.body(), listType);
        } else {
            return null;
        }

    }

}
