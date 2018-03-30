package stud.apach.users.services;

import stud.apach.users.model.Gender;
import stud.apach.users.model.User;
import stud.apach.users.validate.UserForm;

import java.util.List;

public interface UserService {

    List<User> selectAll();
    User createUser(UserForm userForm);
    User selectUserById(Long id);
    User updateUser(UserForm userForm, long userId);
    void deleteUser(long userId);
    List<Gender> getGenderList();
}
