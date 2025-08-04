package com.abhishekvermaa10.controller;

import com.abhishekvermaa10.dto.OwnerDTO;
import com.abhishekvermaa10.dto.OwnerIdDTO;
import com.abhishekvermaa10.dto.UpdatePetDTO;
import com.abhishekvermaa10.exception.OwnerNotFoundException;
import com.abhishekvermaa10.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OwnerController {

    @Autowired
    private  OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
//to add new owner
    @PostMapping("/owners")
    public ResponseEntity<OwnerIdDTO> saveOwner(@RequestBody OwnerDTO ownerDTO) {
        OwnerIdDTO OwnerIdDTO = ownerService.saveOwner(ownerDTO);
        return ResponseEntity.ok(OwnerIdDTO);
    }
//to fetch owner details

    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerDTO> getOwnerById(@PathVariable int ownerId) throws OwnerNotFoundException {
        OwnerDTO ownerDTO = ownerService.findOwner(ownerId);
        return ResponseEntity.ok(ownerDTO);
    }

//    to update pet details of owner
    @PutMapping("/{ownerId}/pet")
    public ResponseEntity<Void> updatePet(@PathVariable int ownerId, @RequestBody String updatePetDTO) throws OwnerNotFoundException {
        ownerService.updatePetDetails(ownerId,updatePetDTO);
        return  ResponseEntity.ok().build();
    }

//    to delete owner details
    @DeleteMapping("/{ownerId}")
    public ResponseEntity<Void> deleteOwnerById(@PathVariable int ownerId) throws OwnerNotFoundException {
         ownerService.deleteOwner(ownerId);
         return  ResponseEntity.ok().build();
    }
//    to fetch all owner details

    @GetMapping("/allOwners")
    public ResponseEntity<List<OwnerDTO>> getAllOwners(){
        List<OwnerDTO> owners = ownerService.findAllOwners();
        return ResponseEntity.ok(owners);
    }

//    find average age of pety

}