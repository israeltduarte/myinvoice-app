package br.com.isertech.myinvoiceback.util;

import br.com.isertech.myinvoiceback.dto.UserDTO;
import br.com.isertech.myinvoiceback.entity.MIUser;

public class MIUserTransformer {

    private MIUserTransformer() {}

    public static MIUser fromDTO(UserDTO dto) {
        return MIUser.builder()
                .name(dto.getName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    public static MIUser fromDTO(MIUser user, UserDTO dto) {
        MIUser updatedUser = fromDTO(dto);
        updatedUser.setId(user.getId());

        return updatedUser;
    }
}
