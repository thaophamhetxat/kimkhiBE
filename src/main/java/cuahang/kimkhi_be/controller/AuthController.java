package cuahang.kimkhi_be.controller;

import cuahang.kimkhi_be.dto.request.ChangeAvatar;
import cuahang.kimkhi_be.dto.request.SignInForm;
import cuahang.kimkhi_be.dto.request.SignUpForm;
import cuahang.kimkhi_be.dto.response.JwtResponse;
import cuahang.kimkhi_be.dto.response.ResponseMessage;
import cuahang.kimkhi_be.model.Role;
import cuahang.kimkhi_be.model.RoleName;
import cuahang.kimkhi_be.model.Users;
import cuahang.kimkhi_be.security.jwt.JwtProvider;
import cuahang.kimkhi_be.security.jwt.JwtTokenFilter;
import cuahang.kimkhi_be.security.userpincal.UserPrinciple;
import cuahang.kimkhi_be.service.RoleServiceImpl;
import cuahang.kimkhi_be.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RequestMapping("/kimkhi/auth")
@RestController
@CrossOrigin("*")
public class AuthController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm) {
        if (userService.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("The username is existed"), HttpStatus.OK);
        }
        if (userService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("The email is existed"), HttpStatus.OK);
        }
        if (signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()) {
            signUpForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/kimkhiupload.appspot.com/o/andanh.PNG?alt=media&token=3d1f58dd-1a6a-417e-a763-af4c53aa0f38");
        }
        Users users = new Users(signUpForm.getName(), signUpForm.getUsername(), signUpForm.getEmail(),signUpForm.getAvatar(), passwordEncoder.encode(signUpForm.getPassword()));
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(pmRole);
                    break;
                default:
                    Role userRole = roleService.findByName(RoleName.USER).orElseThrow(() -> new RuntimeException("Role not found"));
                    roles.add(userRole);
            }
        });
        users.setRoles(roles);
        userService.save(users);
        return new ResponseEntity<>(new ResponseMessage("Create success!"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(token, userPrinciple.getName(), userPrinciple.getAuthorities()));
    }

    @PutMapping("/change-avatar")
    public ResponseEntity<?> changeAvatar(HttpServletRequest request, @Valid @RequestBody ChangeAvatar changeAvatar){

        String jwt = jwtTokenFilter.getJwt(request);
        String username = jwtProvider.getUerNameFromToken(jwt);
        Users users;
        try {
            if(changeAvatar.getAvatar()==null){
                return new ResponseEntity<>(new ResponseMessage("no"), HttpStatus.OK);
            } else {
                users = userService.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User Not Found -> username"+username));
                users.setAvatar(changeAvatar.getAvatar());
                userService.save(users);
            }
            return new ResponseEntity<>(new ResponseMessage("yes"), HttpStatus.OK);
        } catch (UsernameNotFoundException exception){
            return new ResponseEntity<>(new ResponseMessage(exception.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
