package com.sandeep.productservice.commons;

import com.sandeep.productservice.dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {

    RestTemplate restTemplate;

    AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token) {
        //Call UserService validate API to validate the token.
        String url = "http://localhost:8081/users/validate/" + token;
        ResponseEntity<UserDto> response = restTemplate.postForEntity(url, null, UserDto.class);

        if(response.getBody()==null)
        {
            //Token is invalid
            return null;
        }
        return response.getBody();
    }

}
