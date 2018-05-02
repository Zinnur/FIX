package ru.zinnur.service.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.zinnur.service.models.User;

@Data
@AllArgsConstructor
@Builder
public class UserDto {

    private String name;
    private String birthDate;

    public static UserDto from(User user){
        return UserDto.builder()
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .build();
    }
}
