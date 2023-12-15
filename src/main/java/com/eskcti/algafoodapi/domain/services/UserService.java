package com.eskcti.algafoodapi.domain.services;

import com.eskcti.algafoodapi.domain.exceptions.BusinessException;
import com.eskcti.algafoodapi.domain.exceptions.UserNotFoundException;
import com.eskcti.algafoodapi.domain.models.Group;
import com.eskcti.algafoodapi.domain.models.User;
import com.eskcti.algafoodapi.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupService groupService;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        if (user.isNew()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.detach(user);
        Optional<User> userExisting = userRepository.findByEmail(user.getEmail());
        if (userExisting.isPresent() && !userExisting.get().equals(user)) {
            throw new BusinessException(
                    String.format("There is already a registered user with email %s", user.getEmail())
            );
        }
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

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new BusinessException("Current password entered does not match the user's password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    @Transactional
    public void associateGroup(Long userId, Long groupId) {
        User user = find(userId);
        Group group = groupService.find(groupId);

        user.associateGroup(group);
    }

    @Transactional
    public void disassociateGroup(Long userId, Long groupId) {
        User user = find(userId);
        Group group = groupService.find(groupId);

        user.disassociateGroup(group);
    }
}
