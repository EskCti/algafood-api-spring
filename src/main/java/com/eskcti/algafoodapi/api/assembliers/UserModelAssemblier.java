package com.eskcti.algafoodapi.api.assembliers;

import com.eskcti.algafoodapi.api.model.GroupModel;
import com.eskcti.algafoodapi.api.model.UserModel;
import com.eskcti.algafoodapi.domain.models.Group;
import com.eskcti.algafoodapi.domain.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserModelAssemblier {
    @Autowired
    private ModelMapper modelMapper;
    public UserModel toModel(User user) {
        return modelMapper.map(user, UserModel.class);
    }

    public List<UserModel> toCollectionModel(List<User> users) {
        return users.stream()
                .map(user -> toModel(user))
                .collect(Collectors.toList());
    }
}
