package ru.job4j.dreamjob.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.service.UserService;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class UserControllerTest {
    private UserService userService;

    private UserController userController;

    @BeforeEach
    public void initServices() throws Exception {
        this.userService = mock(UserService.class);
        this.userController = new UserController(userService);
    }

    @Test
    public void whenRegisterThenRedirectToVacanciesPage() {
        User user = new User(1, "user@mail.ru", "Test user", "12345");
        when(userService.save(any(User.class))).thenReturn(Optional.of(user));
        var model = new ConcurrentModel();
        var view = userController.register(user, model);

        assertThat(view).isEqualTo("redirect:/vacancies");
    }

    @Test
    public void whenLoginThenRedirectToVacanciesPage() {
        User user = new User(1, "user@mail.ru", "Test user", "12345");
        when(userService.findByEmailAndPassword(any(String.class), any(String.class))).thenReturn(Optional.of(user));

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpSession session = mock(HttpSession.class);
        when(request.getMethod()).thenReturn("POST");
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("email")).thenReturn(user.getEmail());
        when(request.getParameter("password")).thenReturn(user.getPassword());

        var model = new ConcurrentModel();
        var view = userController.loginUser(user, model, request);

        assertThat(view).isEqualTo("redirect:/vacancies");
    }
}