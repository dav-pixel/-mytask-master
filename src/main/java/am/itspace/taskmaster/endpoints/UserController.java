package am.itspace.taskmaster.endpoints;

import am.itspace.taskmaster.dtos.UserGetDto;
import am.itspace.taskmaster.entities.AuthenticationRequest;
import am.itspace.taskmaster.entities.AuthenticationResponse;
import am.itspace.taskmaster.entities.User;
import am.itspace.taskmaster.repositories.UserRepo;
import am.itspace.taskmaster.security.UserDetailsServiceImpl;
import am.itspace.taskmaster.services.UserService;
import am.itspace.taskmaster.util.JwtUtil;
import javassist.NotFoundException;
import javassist.bytecode.DuplicateMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final JwtUtil jwtUtil;

    @PostMapping
    public void addUser(@RequestBody User user){
        try {
            userService.addUser(user);
        } catch (DuplicateMemberException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        Optional<User> byEmail = userRepo.findByEmail(authenticationRequest.getEmail());
        if (byEmail.isPresent()) {
            if (passwordEncoder.matches(authenticationRequest.getPassword(), byEmail.get().getPassword())) {
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(authenticationRequest.getEmail());
                String jwt = jwtUtil.generateToken(userDetails);
                return ResponseEntity.ok(new AuthenticationResponse(jwt));
            }
        }
        else {
            throw new DuplicateMemberException("user does not exist!");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorizing does not succeedded");
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserGetDto>> getAllUsers(){
        try {
            List<UserGetDto> list = userService.getAllUsers(userRepo.findAll());
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable ("id") Integer id, @RequestBody User user){
        userService.updateUser(id, user);
    }



}