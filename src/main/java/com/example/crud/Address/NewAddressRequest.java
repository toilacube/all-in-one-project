package com.example.crud.Address;

import lombok.Builder;
import lombok.Data;


@Builder
public record NewAddressRequest (
        String streetLine, String province, String district, String commune
        , String name, String phoneNumber, Integer isDefault){

}
