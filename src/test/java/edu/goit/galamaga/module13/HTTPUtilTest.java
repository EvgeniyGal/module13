package edu.goit.galamaga.module13;

import com.google.gson.Gson;
import edu.goit.galamaga.module13.todos.Todos;
import edu.goit.galamaga.module13.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

class HTTPUtilTest {

    public final Gson gson = new Gson();
    public final String URI_USERS = "https://jsonplaceholder.typicode.com/users";
    public final String URI_POSTS = "https://jsonplaceholder.typicode.com/posts";

    @Test
    void createUserTest() throws FileNotFoundException {

        User user = gson.fromJson(new FileReader("src/test/resources/user.json"), User.class);

        HttpResponse<String> response = HTTPUtil.createUser(URI_USERS, user);

        Assertions.assertNotNull(response);

        Assertions.assertEquals(201, response.statusCode());

        Assertions.assertEquals(user.hashCode(), gson.fromJson(response.body(), User.class).hashCode());

    }


    @Test
    void updateUserTest() throws FileNotFoundException {

        User modUser = gson.fromJson(new FileReader("src/test/resources/user.json"), User.class);

        modUser.setUsername("Test username update");
        modUser.setName("Test name update");
        modUser.setEmail("Test email update");

        int userId = 9;

        HttpResponse<String> response = HTTPUtil.updateUser(URI_USERS, userId, modUser);

        Assertions.assertNotNull(response);

        Assertions.assertEquals(200, response.statusCode());

        User respUser = gson.fromJson(response.body(), User.class);

        Assertions.assertTrue(respUser.getUsername().equals("Test username update") &&
                respUser.getName().equals("Test name update") &&
                respUser.getEmail().equals("Test email update")
        );

    }

    @Test
    void deleteUserTest() {

        int userId = 5;

        HttpResponse<String> response = HTTPUtil.deleteUser(URI_USERS, userId);

        Assertions.assertNotNull(response);

        Assertions.assertEquals(200, response.statusCode());

    }

    @Test
    void getUsersTest() {

        List<User> users = HTTPUtil.getUsers(URI_USERS);

        Assertions.assertNotNull(users);

        System.out.println("\nCheck users from method getUsers:");

        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    void getUserByIDTest() {

        int userId = 7;

        User user = HTTPUtil.getUser(URI_USERS, userId);

        Assertions.assertNotNull(user);

        Assertions.assertEquals(userId, user.getId());

        System.out.println("\nCheck user from method getUser by userID = " + userId + ":\n" + user);

    }

    @Test
    void getUserByUserNameTest() {

        String userName = "Karianne";

        List<User> users = HTTPUtil.getUser(URI_USERS, userName);

        Assertions.assertNotNull(users);

        System.out.println("\nCheck user from method getUser by userName = " + userName + ":\n");
        for (User user : users) {
            Assertions.assertEquals(userName, user.getUsername());
            System.out.println(user);
        }

    }

    @Test
    void saveCommentsToJSONTest() {

        int userID = 8;

        Assertions.assertTrue(HTTPUtil.saveCommentsToJSON(URI_USERS, URI_POSTS, userID));

    }

    @Test
    void getUserOpenTaskTest() {

        int userID = 6;

        List<Todos> todos = HTTPUtil.getUserOpenTask(URI_USERS, userID);

        Assertions.assertNotNull(todos);

        todos = todos.stream().filter(todo -> !todo.isCompleted()).collect(Collectors.toList());

        System.out.printf("\nCheck not finished tasks %d from method getUserOpenTask:", userID);

        for (Todos todo : todos) {
            Assertions.assertFalse(todo.isCompleted());
            System.out.println(todo);
        }

    }


}