package com.mega.auth.module.user.service;

import com.mega.auth.configuration.ModelMapperConfiguration;
import com.mega.auth.module.user.dto.ListUserDto;
import com.mega.auth.module.user.dto.SingleUserDto;
import com.mega.auth.module.user.entity.User;
import com.mega.auth.module.user.repository.UserRepository;
import com.mega.auth.utils.exception.ResourceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapperConfiguration modelMapper;

    @Override
    public List<ListUserDto> findAllActiveUsers() {
        List<User> users = userRepository.findAllActiveUsers();
        return mapperListUserToDto(users);
    }

    @Override
    public SingleUserDto findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("User not found :" + id));
        return mapperSingleUserToDto(user);
    }

    private List<ListUserDto> mapperListUserToDto(List<User> users) {
        List<ListUserDto> listUserDtos = new ArrayList<>();

        for (User user : users) {
            ListUserDto listUserDto = modelMapper
                    .modelMapper()
                    .map(user, ListUserDto.class);
            listUserDto.setId(user.getId());
            listUserDto.setUsername(user.getUsername());
            listUserDto.setEmail(user.getEmail());
            listUserDto.setPhone(user.getPhone());
            listUserDto.setAddress(user.getAddress());
            listUserDto.setPhoto(user.getPhoto());
            listUserDtos.add(listUserDto);
        }

        return listUserDtos;
    }

    private SingleUserDto mapperSingleUserToDto(User user) {
        SingleUserDto singleUserDto = modelMapper
                .modelMapper()
                .map(user, SingleUserDto.class);
        singleUserDto.setId(user.getId());
        singleUserDto.setUsername(user.getUsername());
        singleUserDto.setEmail(user.getEmail());
        singleUserDto.setPhone(user.getPhone());
        singleUserDto.setAddress(user.getAddress());
        singleUserDto.setPhoto(user.getPhoto());
        return singleUserDto;
    }
}
