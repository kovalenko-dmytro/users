package stud.apach.users.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stud.apach.users.model.Gender;
import stud.apach.users.model.User;
import stud.apach.users.repositories.UserRepository;
import stud.apach.users.validate.UserForm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
    UserRepository userRepo;


    @Override
    public List<User> selectAll() {
        return (List<User>) userRepo.findAll();
    }

    @Override
    @Transactional
    public User createUser(UserForm userForm) {
        User newUser = new User();

        newUser.setName(userForm.getName());
        newUser.setAge(userForm.getAge());
        newUser.setGender(userForm.getGender());

        return userRepo.save(newUser);
    }

    @Override
    public User selectUserById(Long id) {
        return userRepo.findOne(id);
    }

    @Override
    @Transactional
    public User updateUser(UserForm userForm, long userId) {
        User updateUser = userRepo.findOne(userId);

        updateUser.setName(userForm.getName());
        updateUser.setAge(userForm.getAge());
        updateUser.setGender(userForm.getGender());

        return userRepo.save(updateUser);
    }

    @Override
    public void deleteUser(long userId) {
        userRepo.delete(userId);
    }

    @Override
    public List<Gender> getGenderList() {
        List<Gender> gender = new ArrayList<>();
        gender.addAll(Arrays.asList(Gender.values()));

        return gender;
    }
}
