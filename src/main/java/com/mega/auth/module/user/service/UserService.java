package com.mega.auth.module.user.service;

import com.mega.auth.module.user.dto.ListUserDto;
import com.mega.auth.module.user.dto.SingleUserDto;
import com.mega.auth.module.user.entity.User;

import java.util.List;

public interface UserService {
    List<ListUserDto> findAllActiveUsers();
     SingleUserDto findUserById(Long id);
}
