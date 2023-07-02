package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.input.UserInput;
import com.eskcti.algafoodapi.domain.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInputDisassembler {
    @Autowired
    private ModelMapper modelMapper;

    public User toDomainObject(UserInput userInput) {
        return modelMapper.map(userInput, User.class);
    }

    public void copyToDomainObject(UserInput userInput, User user) {
        modelMapper.map(userInput, user);
    }
}
