package com.example.crud.Address;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAddressResponse {
    private int addressId;
    private String streetLine;
    private Integer isDefault;

}
