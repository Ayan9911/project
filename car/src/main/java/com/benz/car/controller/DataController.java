package com.benz.car.controller;
import com.benz.car.dto.UserRequestDTO;
import com.benz.car.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping("/store")
    public ResponseEntity<String> storeData(@RequestBody UserRequestDTO dto,
                                            @RequestHeader("fileType") String fileType) {
        dataService.store(dto, fileType);
        return ResponseEntity.ok("Stored successfully in " + fileType);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateData(@RequestBody UserRequestDTO dto,
                                             @RequestHeader("fileType") String fileType) {
        dataService.update(dto, fileType);
        return ResponseEntity.ok("Updated successfully in " + fileType);
    }

    @GetMapping("/read")
    public ResponseEntity<String> readData(@RequestHeader("fileType") String fileType) {
        String data = dataService.read(fileType); //
        return ResponseEntity.ok(data);
    }
}