package com.example.controller;

import com.example.view.AppView;
import com.example.view.Menu;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import com.example.model.App;
import com.example.model.User;
import com.example.model.IO.errors.Errors;
import com.example.controller.LoginMenuController;
import com.example.model.IO.Steps.LoginMenuStep;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import javafx.application.Platform;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import com.example.model.App;
import com.example.view.AppView;
import com.example.controller.LoginMenuController;
import com.example.controller.Controller;
import com.example.view.Menu;

class LoginMenuControllerTest {

    private LoginMenuController controller;
    private static Stage primaryStage;
    private static CountDownLatch latch;
    private AppView mockAppView;

    @BeforeAll
    public static void setupJavaFX() throws InterruptedException {
        latch = new CountDownLatch(1);
        Platform.startup(() -> {
            new Stage(); // Dummy stage to initialize JavaFX
            latch.countDown();
        });

        if (!latch.await(5, TimeUnit.SECONDS)) {
            throw new ExceptionInInitializerError("JavaFX initialization timed out.");
        }
    }

    @BeforeEach
    void setUp() throws Exception {
        mockAppView = Mockito.mock(AppView.class);
        App.setAppView(mockAppView);
        controller = (LoginMenuController) Controller.LOGIN_MENU_CONTROLLER.getController();
        App.setCurrentController(Controller.LOGIN_MENU_CONTROLLER);
    }

    @Test
    void testRun() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Platform.runLater(() -> {
                assertDoesNotThrow(() -> controller.run());
            });
        });
    }

    @Test
    void testLoginUser() throws IOException {
        App.addNewUser(new User("existinguser", "Le@23#Re", "nickname", "email@example.com"));

        Errors result = controller.loginUser("nonexistentuser", "Le@23#Re", false);
        assertEquals(Errors.USER_DOESNT_EXIST, result);
    }

    @Test
    void testRegisterUser() {
        Errors result = controller.registerUser("newuser", "Le@23#Re", "Le@23#Re", "nickname", "email@example.com", false);
        assertEquals(Errors.REGISTER_FIRST_STEP_SUCCESSFUL, result);

        result = controller.registerUser("invalid username", "Le@23#Re", "Le@23#Re", "nickname", "email@example.com", false);
        assertEquals(Errors.INVALID_USERNAME_FORMAT, result);

        result = controller.registerUser("newuser", "weak", "weak", "nickname", "email@example.com", false);
        assertEquals(Errors.INVALID_PASSWORD, result);

        result = controller.registerUser("newuser", "Le@23#Re", "DifferentLe@23#Re", "nickname", "email@example.com", false);
        assertEquals(Errors.PASSWORD_DOESNT_MATCH, result);

        result = controller.registerUser("newuser", "Le@23#Re", "Le@23#Re", "nickname", "invalidemail", false);
        assertEquals(Errors.INVALID_EMAIL, result);

        App.addNewUser(new User("newuser", "Le@23#Re", "nickname", "email@example.com"));
        result = controller.registerUser("newuser", "Le@23#Re", "Le@23#Re", "nickname", "email@example.com", false);
        assertEquals(Errors.REGISTER_FIRST_STEP_SUCCESSFUL, result);
    }

    @Test
    void testGenerateRandomPassword() {
        String password = controller.generateRandomPassword();
        assertTrue(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{10,}$"));
    }

    @Test
    void testInvalidEmail() {
        Errors result = controller.registerUser("validuser", "Le@23#Re", "Le@23#Re", "nickname", "invalid.email", false);
        assertEquals(Errors.INVALID_EMAIL, result);
    }

    @Test
    void testInvalidUsername() {
        Errors result = controller.registerUser("username@", "Le@23#Re", "Le@23#Re", "nickname", "email@example.com", false);
        assertEquals(Errors.INVALID_USERNAME_FORMAT, result);
    }

    @AfterEach
    void tearDown() {
        Platform.runLater(() -> {
            if (primaryStage != null) {
                primaryStage.close();
            }
        });
    }
}