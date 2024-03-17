package com.example.crud.Address;

public record UpdateAddressRequest(int id, String streetLine, String province, String district, String commune, String name, String phoneNumber, Integer isDefault) {
}
