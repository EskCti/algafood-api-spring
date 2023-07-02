package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.UserNotFoundException;
import com.eskcti.algafoodapi.domain.models.User;
import com.eskcti.algafoodapi.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> list() {
        return userRepository.findAll();
    }

    public User find(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return user;
    }

    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        User user = find(id);
        userRepository.delete(user);
    }

    @Transactional
    public User updatePassword(Long id, String currentPassword, String newPassword) {
        User user = find(id);
        System.out.println(user.getPassword());
        System.out.println(currentPassword);
        if (user.passwordNotMatchWith(currentPassword)) {
            throw new BusinessException("Current password entered does not match the user's password");
        }
        user.setPassword(newPassword);
        return userRepository.save(user);
    }
}
