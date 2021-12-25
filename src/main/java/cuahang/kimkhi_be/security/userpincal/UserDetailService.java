package cuahang.kimkhi_be.security.userpincal;


import cuahang.kimkhi_be.model.Users;
import cuahang.kimkhi_be.repository.IUserRepository;
import cuahang.kimkhi_be.service.impl.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;

    @Autowired
    IUserService iUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User not found -> user name or password"+username));
        return UserPrinciple.build(users);
    }


    //hàm lấy ra user hiện tại để thao tác với db
    public Users getCurrentUser(){
        Optional<Users>users;
        String userName;

        //lấy 1 object princical trong sercuritycontextholder
        Object princical = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //so sánh obj với userdetail nếu đúng thì gắn username = pricical.getUserName()
        if(princical instanceof UserPrinciple){
            userName = ((UserPrinciple) princical).getUsername();
        }else {
            //nếu không phải  user hiện tại thi username = pricical.tostring
            userName = princical.toString();
        }

        //kiểm tra  nếu username ton tại trong db thi ga user = HAM TÌM kiếm  trong db theo username do
        if(userRepository.existsByUsername(userName)){
            users = iUserService.findByUsername(userName);
        }else {
            //nếu chưa có thì  trả về 1 thể hiện của lớp user thong qua optional
            users  = Optional.of(new Users());
            //set cho nó 1 cái tên user an danh
            users.get().setUsername("anonymous");
        }
        return users.get();
    }
}
