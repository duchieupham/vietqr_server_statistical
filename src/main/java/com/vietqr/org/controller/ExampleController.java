package com.vietqr.org.controller;

import com.vietqr.org.dto.ResponseMessageDTO;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ExampleController {
    private static final Logger logger = Logger.getLogger(ExampleController.class);

    @GetMapping("/example")
    public ResponseEntity<ResponseMessageDTO> example() {
        logger.info("ExampleController.example");
        return ResponseEntity.ok(new ResponseMessageDTO("200", "Example"));
    }

}
