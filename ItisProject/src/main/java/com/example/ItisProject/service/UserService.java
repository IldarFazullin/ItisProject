package com.example.ItisProject.service;

import com.example.ItisProject.entity.Role;
import com.example.ItisProject.entity.User;
import com.example.ItisProject.exception.ClientException;
import com.example.ItisProject.exception.ErrorCode;
import com.example.ItisProject.repository.UserRepository;
import com.example.ItisProject.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) { // зачем создавать конструктор???
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    public void saveUserInDatabase(User user) throws ClientException {

        validateUserEmail(user.getEmail());

        User createdUser = new User();
        createdUser.setUserName(user.getUserName());
        createdUser.setEmail(user.getEmail());
        createdUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        createdUser.setUserRole(Role.USER);
        userRepository.save(createdUser);
    }

    public void deleteUserFromDatabase(long id){
        userRepository.deleteById(id);
    }

    public List<User> findAllUsersInDatabase() {
        return userRepository.findAll();
    }

    public Optional<User> findOneUserInDatabase(long id){
        return userRepository.findById(id);
    }

    public void updateUserInDatabase(User user) throws ClientException{

        Optional<User> optionalUser = findOneUserInDatabase(user.getId());
        if (optionalUser.isPresent()) {
            User editUser = optionalUser.get();
            editUser.setUserName(user.getUserName());
            editUser.setEmail(user.getEmail());
            userRepository.save(editUser);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("can't find user"));
        return new SecurityUser(user);
    }

    private void validateUserEmail(String email) throws ClientException {
        Optional<User> existingUser = findByEmail(email);
        if (existingUser.isPresent()) {
            throw new ClientException(ErrorCode.USER_EXISTS_ALREADY);
        }
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
