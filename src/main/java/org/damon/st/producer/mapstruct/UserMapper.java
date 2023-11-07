package org.damon.st.producer.mapstruct;

import org.damon.st.producer.dto.UserDto;
import org.damon.st.producer.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
