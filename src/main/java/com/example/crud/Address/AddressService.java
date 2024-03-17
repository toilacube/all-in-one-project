package com.example.crud.Address;

import com.example.crud.Entity.User;
import com.example.crud.Entity.UserAddress;
import com.example.crud.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService implements IAddressService{
    private final UserAddressRepository userAddressRepository;
    private final UserService userService;
    @Override
    public AddressUpdateResponse addAddress(NewAddressRequest userAddress) {
           User user = userService.getUser();
            if(user != null) {
                UserAddress newUserAddress = UserAddress.builder()
                        .addressLine(userAddress.streetLine())
                        .province(userAddress.province())
                        .province(userAddress.province())
                        .district(userAddress.district())
                        .commune(userAddress.commune())
                        .isDefault(userAddress.isDefault())
                        .user(user)
                        .build();
                userAddressRepository.save(newUserAddress);

                if (newUserAddress.getIsDefault() == 1){
                    this.makeAddressDefault(newUserAddress.getId());
                }

                return AddressUpdateResponse.builder()
                        .message("Address Added Successfully")
                        .fullAddress(newUserAddress.getAddressLine() + ", " + newUserAddress.getCommune() + ", " + newUserAddress.getDistrict() + ", " + newUserAddress.getProvince())
                        .build();
            }
        return AddressUpdateResponse.builder()
                .message("Failed to add address")
                .build();
    }

    @Override
    public AddressUpdateResponse makeAddressDefault(int addressId) {
        List<UserAddress> userAddresses = userAddressRepository.findAllByUserId(userService.getUser().getId());
        boolean isAddressExist = false;
        UserAddress defaultAddress = new UserAddress();
        for(UserAddress userAddress : userAddresses) {
            if(userAddress.getId() == addressId) {
                userAddress.setIsDefault(1);
                isAddressExist = true;
                userAddressRepository.save(userAddress);
                defaultAddress = userAddress;
                break;
            }
        }
        if(isAddressExist) {
            for(UserAddress userAddress : userAddresses) {
                   if(userAddress.getId() != addressId) {
                        userAddress.setIsDefault(0);
                        userAddressRepository.save(userAddress);
                    }
                }
            return AddressUpdateResponse.builder()
                    .message("Address Updated Successfully")
                    .fullAddress(defaultAddress.getAddressLine() + ", " + defaultAddress.getCommune() + ", " + defaultAddress.getDistrict() + ", " + defaultAddress.getProvince())
                    .build();
            }
        return AddressUpdateResponse.builder()
                .message("Address Updated Successfully")
                .build();
    }


    @Override
    public Iterable<UserAddressResponse> getUserAddresses() {
        User user = userService.getUser();
        if(user != null) {
            Iterable <UserAddress> userAddresses = userAddressRepository.findAllByUserId(user.getId());
            List <UserAddressResponse> userAddressResponses = new java.util.ArrayList<>();
            for(UserAddress userAddress : userAddresses) {
                UserAddressResponse userAddressResponse = UserAddressResponse.builder()
                        .addressId(userAddress.getId())
                        .streetLine(userAddress.getAddressLine())
                        .isDefault(userAddress.getIsDefault())
                        .build();
                userAddressResponses.add(userAddressResponse);
            }
            return userAddressResponses;
        }
        return null;
    }

    @Override
    public AddressUpdateResponse updateAddress(UpdateAddressRequest newUserAddress) {
        User user = userService.getUser();
        if(user != null) {
           UserAddress oldUserAddress = userAddressRepository.findById(newUserAddress.id()).orElse(null);
           if(oldUserAddress != null){
               oldUserAddress.setAddressLine(newUserAddress.streetLine());
               oldUserAddress.setProvince(newUserAddress.province());
               oldUserAddress.setDistrict(newUserAddress.district());
               oldUserAddress.setCommune(newUserAddress.commune());
               oldUserAddress.setIsDefault(newUserAddress.isDefault());
               oldUserAddress.setName(newUserAddress.name());
               oldUserAddress.setPhoneNumber(newUserAddress.phoneNumber());
               userAddressRepository.save(oldUserAddress);

               if (oldUserAddress.getIsDefault() == 1){
                   this.makeAddressDefault(oldUserAddress.getId());
               }
               return AddressUpdateResponse.builder()
                       .message("Address Updated Successfully")
                       .fullAddress(oldUserAddress.getAddressLine() + ", " + oldUserAddress.getCommune() + ", " + oldUserAddress.getDistrict() + ", " + oldUserAddress.getProvince())
                       .build();
           }


        }
        return AddressUpdateResponse.builder()
                .message("Address Updated Successfully")
                .build();
    }
}
