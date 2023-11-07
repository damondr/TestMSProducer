package org.damon.st.producer.service;

import org.damon.st.producer.model.User;

public interface UsersService {
    void createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
