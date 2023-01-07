package com.restapi.atmsimulationsystem.controllers;

import com.restapi.atmsimulationsystem.payload.requests.UserAmountRequestDto;
import com.restapi.atmsimulationsystem.payload.requests.UserRequestDto;
import com.restapi.atmsimulationsystem.payload.responses.APIResponse;
import com.restapi.atmsimulationsystem.service.UserService;
import com.restapi.atmsimulationsystem.utils.Responder;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequestMapping("/api/v1/")
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

    @PostMapping("/depositMoney")
    public String depositMoney(@RequestBody UserAmountRequestDto amount){
        service.depositMoney(amount);
        return "money deposited succcessfully";
    }

    @GetMapping("/withdrawMoney")
    public String withdrawMoney(@RequestParam (name = "amount") BigDecimal amount){
        service.withdrawMoney(amount);
        return "withdrawal successful";
    }

    @GetMapping("/checkBalance")
    public ResponseEntity<APIResponse> checkBalance(){
        return responder.okay(service.checkAccountBalance());
    }
}
