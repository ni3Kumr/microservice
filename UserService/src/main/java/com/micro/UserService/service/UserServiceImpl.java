package com.micro.UserService.service;

import com.micro.UserService.entities.Hotel;
import com.micro.UserService.entities.Rating;
import com.micro.UserService.entities.User;
import com.micro.UserService.exception.ResourceNotFoundException;
import com.micro.UserService.external.service.HotelService;
import com.micro.UserService.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private HotelService hotelService;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<User> getAllUsers() {



        List<User>  userList= userRepository.findAll();

        userList = userList.stream().map(user -> {
                String ratingServiceUrl = "http://RATINGSERVICE/rating/users/" + user.getUserId();
                try{
                  ArrayList<Rating> ratingList= restTemplate.getForObject(ratingServiceUrl,ArrayList.class);
                  user.setRatings(ratingList);
                    logger.info("Ratings for user {}: {}", user.getUserId(), ratingList);

                }
                catch(RestClientException e){
                    logger.error("Error fetching ratings for user {}: {}", user.getUserId(), e.getMessage());
                    // set empty list if there's an error fetching rating
                    user.setRatings(new ArrayList<>());

                }
                return user;
        }).collect(Collectors.toList());
        return userList;
    }

    @Override
    public User saveUser(User user) {
         String randomUserId= UUID.randomUUID().toString();
         user.setUserId(randomUserId);
//        List<Rating> list = new ArrayList<>();
//        list.add(new Rating("1","6d4f8b68-0bb0-4cea-8dbc-a2526f8c23f8","1",3,"Good Hotel"));
//        user.setRatings(list);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User getUserById(String id) {


        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Resource Not Found with Id"+ id));

        // get rating from rating service  http://localhost:8083/rating/users/6d4f8b68-0bb0-4cea-8dbc-a2526f8c23f8

        Rating[] ratingUserList = restTemplate.getForObject("http://RATINGSERVICE/rating/users/"+id, Rating[].class);
        List<Rating>  ratingList= Arrays.stream(ratingUserList).toList();
          user.setRatings(ratingList);
       logger.info("{}",ratingList);

       // calling Hotel api (http://localhost:8082/hotel/6d366ca5-87e8-4710-a5a5-4748d52ee951)
      List<Rating> ratingList1 =  ratingList.stream().map(rating ->

                {
                //    String hotelUrl = "http://HOTELSERVICES/hotel/"+ rating.getHotelId();
               //  ResponseEntity<Hotel> forEntity= restTemplate.getForEntity(hotelUrl, Hotel.class);
                 Hotel hotel  = hotelService.getHotel(rating.getHotelId());

                // logger.info("Response staus Code : {}", forEntity.getStatusCode());
                 rating.setHotel(hotel);
                 return  rating;

                }


        ).collect(Collectors.toList());

    user.setRatings(ratingList1);

        return user;
    }
}
