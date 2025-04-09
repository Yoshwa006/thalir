package com.example.thalir.service;



import com.example.thalir.dto.MapperDTO;
import com.example.thalir.dto.RegisterRequest;
import com.example.thalir.exception.EmailAlreadyExistsException;
import com.example.thalir.model.User;
import com.example.thalir.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    UserRepo repo;


    @Autowired
    AuthService(UserRepo repo){
        this.repo =repo;
    }

    public void saveLoginData(RegisterRequest request){
        if(repo.existsByEmail(request.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists" + request.getEmail());
        }
        User user  = MapperDTO.toUser(request);
        repo.save(user);
    }


}
