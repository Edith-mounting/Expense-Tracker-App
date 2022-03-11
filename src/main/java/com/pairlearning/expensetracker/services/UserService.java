package com.pairlearning.expensetracker.services;

import com.pairlearning.expensetracker.Constants;
import com.pairlearning.expensetracker.exceptions.EtAuthException;
import com.pairlearning.expensetracker.pojo.User;
import com.pairlearning.expensetracker.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Map<String, String> validateUser(String email, String password) throws EtAuthException {
        if(email != null)email = email.toLowerCase();
        try{
            int userID = userRepository.findByEmail(email);
            Optional<User> user = userRepository.findById(userID);
            User newUser = new User();
            user.ifPresentOrElse((value) ->{
                newUser.setLastName(value.getLastName());
                newUser.setFirstName(value.getFirstName());
                newUser.setUserId(value.getUserId());
                newUser.setEmail(value.getEmail());
                newUser.setPassword(value.getPassword());
            },()->{
                System.out.println("Empty");
            });
            if(!BCrypt.checkpw(password,newUser.getPassword())){
                throw new EtAuthException("Invalid password");
            };
            return generateJWTToken(newUser);
        }catch (Exception e) {
            throw new EtAuthException("Invalid email");
        }
    }

    public Map<String,String> registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        if(email != null)email = email.toLowerCase();

        if(!pattern.matcher(email).matches()){
            throw new EtAuthException("Invalid Email format");
        }
        int count = userRepository.getCountByEmail(email);
        if(count > 0){
            throw new EtAuthException("Email already in use");
        }
        String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt(10));
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        return generateJWTToken(userRepository.save(newUser));
    }
    private Map<String,String> generateJWTToken(User user){
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId",user.getUserId())
                .claim("email",user.getEmail())
                .claim("firstName",user.getFirstName())
                .claim("lastName",user.getLastName())
                .compact();
        Map<String,String> mp = new HashMap<>();
        mp.put("token",token);
        return mp;
    }
}
