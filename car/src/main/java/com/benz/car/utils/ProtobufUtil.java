package com.benz.car.utils;

import com.benz.car.dto.UserRequestDTO;
import com.service.car.proto.UserDataProto.UserData;


public class ProtobufUtil {

    public static UserData toProto(UserRequestDTO dto, String fileType) {
        return UserData.newBuilder()
                .setName(dto.getName())
                .setDob(dto.getDob())
                .setSalary(dto.getSalary().doubleValue())
                .setAge(dto.getAge())
                .setFileType(fileType)
                .build();
    }
}