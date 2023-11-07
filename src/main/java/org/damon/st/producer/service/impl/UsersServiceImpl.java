package org.damon.st.producer.service.impl;

import lombok.RequiredArgsConstructor;
import org.damon.st.producer.dto.UserDto;
import org.damon.st.producer.dto.UserOperationDto;
import org.damon.st.producer.model.User;
import org.damon.st.producer.service.UsersService;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final RabbitTemplate rabbitTemplate;
    private final ModelMapper modelMapper;
    private final Jackson2JsonMessageConverter messageConverter;

    public void createUser(User user) {
        sendUserOperationToQueue("create", user);
    }

    public void updateUser(User user) {
        sendUserOperationToQueue("update", user);
    }

    public void deleteUser(User user) {
        sendUserOperationToQueue("delete", user);
    }

    private void sendUserOperationToQueue(String operation, User user) {
        UserDto userDto = convertToUserDTO(user);
        UserOperationDto userOperationDTO = new UserOperationDto();
        userOperationDTO.setOperation(operation);
        userOperationDTO.setUser(userDto);
        rabbitTemplate.setMessageConverter(messageConverter);
        rabbitTemplate.convertAndSend("user-info-exchange", "user-info", userOperationDTO);
    }

    private UserDto convertToUserDTO(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}