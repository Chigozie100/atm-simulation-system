package com.restapi.atmsimulationsystem.controllers;

import com.restapi.atmsimulationsystem.payload.requests.UserRequestDto;
import com.restapi.atmsimulationsystem.payload.responses.APIResponse;
import com.restapi.atmsimulationsystem.service.UserService;
import com.restapi.atmsimulationsystem.utils.Responder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/api/")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService service;
    private final Responder responder;

    @DeleteMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable (name = "id") Long id){
        service.deleteUser(id);
        return "user deleted";
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<APIResponse> updateUser(@PathVariable (name = "id") Long id,
                                                  @RequestBody UserRequestDto requestDto){
        return responder.okay(service.updateUser(id, requestDto));
    }

    @PostMapping("/depositMoney/{id}")
    public String depositMoney(@PathVariable (name = "id") Long id,
                               @RequestParam Integer amount){
        service.depositMoney(id, amount);
        return "money deposited succcessfully";
    }
}
