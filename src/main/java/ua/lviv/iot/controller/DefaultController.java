package ua.lviv.iot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DefaultController {

    @GetMapping("/")
    public ResponseEntity<String> getAll(){
        return ResponseEntity.ok("Hello, ZV!");
    }

}