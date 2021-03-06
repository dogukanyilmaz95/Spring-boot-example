package com.dogukan.usermanagement.Controller;

import com.dogukan.usermanagement.Dao.UserDAO;
import com.dogukan.usermanagement.Exception.ResourceNotFoundException;
import com.dogukan.usermanagement.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserDAO userDAO;

    @CrossOrigin
    @GetMapping("/getAll")
    public List<User> getAllUser() {
        return userDAO.findAll();
    }

    @CrossOrigin
    @PostMapping("/getById")
    public User getById(@RequestBody Map<String, String> data) {
        int id = Integer.valueOf(data.get("id"));
        return userDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Map<String, String> data) {
        String name = data.get("name");
        String surname = data.get("surname");
        String email = data.get("email");


        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setSurname(surname);
        userDAO.save(user);

        return ResponseEntity.ok().body(user);
    }

    @CrossOrigin
    @PutMapping("/")
    public User update(@RequestBody Map<String, String> data) {
        int id = Integer.valueOf(data.get("id"));
        String name = data.get("name");
        String surname = data.get("surname");
        String email = data.get("email");
        User user = userDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setName(name);
        user.setEmail(email);
        user.setSurname(surname);

        return userDAO.save(user);
    }

    @CrossOrigin
    @DeleteMapping("/")
    public ResponseEntity<?> delete(@RequestBody Map<String, String> data) {
        int id = Integer.valueOf(data.get("id"));
        User user = userDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userDAO.delete(user);

        return ResponseEntity.ok().build();
    }

}
