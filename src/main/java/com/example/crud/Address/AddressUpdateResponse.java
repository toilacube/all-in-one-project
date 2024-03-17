package com.example.crud.Address;

import com.example.crud.Entity.UserAddress;
import lombok.Builder;

@Builder
public record AddressUpdateResponse(String message, String fullAddress) {
}
