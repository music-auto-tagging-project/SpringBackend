package com.magician.music.service;

import com.magician.music.domain.User;
import com.magician.music.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //회원 가입
    @Transactional(readOnly = true)
    public User findOne(Long id){
        return userRepository.findOne(id);
    }

}
