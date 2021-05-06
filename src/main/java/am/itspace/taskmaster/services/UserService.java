package am.itspace.taskmaster.services;

import am.itspace.taskmaster.dtos.UserGetDto;
import am.itspace.taskmaster.entities.User;
import am.itspace.taskmaster.mappers.MyMapper;
import am.itspace.taskmaster.repositories.UserRepo;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final MyMapper myMapper;
    private final PasswordEncoder passwordEncoder;

    public void addUser(User user) throws DuplicateMemberException {

        if (null != user && !userRepo.findByEmail(user.getEmail()).isPresent()){
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepo.save(user);
        }
        else throw new DuplicateMemberException("user already exist!");
    }

    public List<UserGetDto> getAllUsers(List<User> list) throws NotFoundException {
        if (list != null) {
            return myMapper.usersToUserDto(list);
        }
        else throw new NotFoundException("users doesn't founded");
    }

    public void updateUser(Integer id, User user){
        if (id != null && userRepo.findById(id).isPresent()){
            if (userRepo.getOne(id).getId() == id){
                user.setId(id);
                userRepo.save(user);
            }
        }
    }


}
