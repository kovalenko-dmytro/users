package stud.apach.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import stud.apach.users.model.Gender;
import stud.apach.users.model.User;
import stud.apach.users.services.UserServiceImpl;
import stud.apach.users.validate.UserForm;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/users")
    public ModelAndView index() {
        List<User> users = userService.selectAll();

        ModelAndView view = new ModelAndView();
        view.addObject("users", users);
        view.setViewName("index");

        return view;
    }

    @GetMapping("/users/add")
    public ModelAndView add(UserForm userForm) {
        ModelAndView view = new ModelAndView();
        view.addObject("genderList", userService.getGenderList());
        view.setViewName("add-user");

        return view;
    }

    @PostMapping("/users/create")
    public ModelAndView create(@Valid @ModelAttribute("articleForm") UserForm userForm, BindingResult bindingResult) {
        ModelAndView view = new ModelAndView();

        if (bindingResult.hasErrors()) {
            view.addObject("userForm", userForm);
            view.addObject("genderList", userService.getGenderList());
            view.setViewName("add-user");
        } else {
            userService.createUser(userForm);
            view.setViewName("redirect:/users");
        }

        return view;
    }

    @GetMapping("/users/{userId}")
    public ModelAndView show(@PathVariable("userId") long userId) {
        User user = userService.selectUserById(userId);

        ModelAndView view = new ModelAndView();
        view.addObject("user", user);
        view.setViewName("view-user");

        return view;
    }

    @GetMapping("/users/{userId}/edit")
    public ModelAndView edit(@PathVariable("userId") long userId) {
        List<Gender> genderList = userService.getGenderList();
        User currentUser = userService.selectUserById(userId);
        UserForm userForm = new UserForm();

        userForm.setName(currentUser.getName());
        userForm.setAge(currentUser.getAge());
        userForm.setGender(currentUser.getGender());

        ModelAndView view = new ModelAndView();
        view.addObject("genderList", genderList);
        view.addObject("userForm", userForm);
        view.addObject("userId", userId);
        view.setViewName("edit-user");

        return view;
    }

    @PostMapping("/users/{userId}/update")
    public ModelAndView update(@PathVariable("userId") long userId, @Valid @ModelAttribute("userForm") UserForm userForm, BindingResult bindingResult) {

        ModelAndView view = new ModelAndView();

        if (bindingResult.hasErrors()) {
            view.addObject("userForm", userForm);
            view.addObject("genderList", userService.getGenderList());
            view.setViewName("edit-user");
        } else {
            userService.updateUser(userForm, userId);
            view.setViewName("redirect:/users");
        }

        return view;
    }

    @GetMapping("/users/{userId}/delete")
    public ModelAndView delete(@PathVariable("userId") long userId) {
        ModelAndView view = new ModelAndView();
        userService.deleteUser(userId);
        view.setViewName("redirect:/users");

        return view;
    }
}
