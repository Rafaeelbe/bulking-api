package io.github.rafaeelbe.bulking.service;

import io.github.rafaeelbe.bulking.dto.UserRequestDto;
import io.github.rafaeelbe.bulking.dto.UserResponseDto;
import io.github.rafaeelbe.bulking.model.UserEntity;
import io.github.rafaeelbe.bulking.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository iUserRepository;

    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> userEntities = iUserRepository.findAll();

        return userEntities.stream()
                .map(userEntity -> new UserResponseDto(
                        userEntity.getId(),
                        userEntity.getName(),
                        userEntity.getEmail()
                ))
                .toList();
    }

    public UserResponseDto getUserById(Integer id) {
        UserEntity userEntity = iUserRepository.findById(id).orElseThrow();

        return new UserResponseDto(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail()
        );
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(userRequestDto.name());
        userEntity.setEmail(userRequestDto.email());

        userEntity = iUserRepository.save(userEntity);

        return new UserResponseDto(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getEmail()
        );
    }

    public void deleteUser(Integer id) {
        iUserRepository.deleteById(id);
    }








}
