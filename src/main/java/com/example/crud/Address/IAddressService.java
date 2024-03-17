package com.example.crud.Address;

public interface IAddressService {
    AddressUpdateResponse addAddress(NewAddressRequest userAddress);

    AddressUpdateResponse makeAddressDefault(int addressId);

    Iterable<UserAddressResponse> getUserAddresses();

    AddressUpdateResponse updateAddress(UpdateAddressRequest userAddress);
}
