package com.example.crud.Address;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("")
    public Iterable<UserAddressResponse> getAllAddresses() {
        return addressService.getUserAddresses();
    }

    @PostMapping("")
    public ResponseEntity<AddressUpdateResponse> addAddress(@RequestBody NewAddressRequest userAddress) {
        return ResponseEntity.ok(addressService.addAddress(userAddress));
    }

    @PutMapping("/update")
    public ResponseEntity<AddressUpdateResponse> updateAddress(@RequestBody UpdateAddressRequest userAddress) {
        return ResponseEntity.ok(addressService.updateAddress(userAddress));
    }

    @PostMapping("/makeDefault")
    public ResponseEntity<?> makeAddressDefault(@RequestParam int addressId) {
        return ResponseEntity.ok(addressService.makeAddressDefault(addressId));
    }


}
