package com.sisvime.app.entity.users.dto;

import com.sisvime.app.share.CivilState;
import com.sisvime.app.share.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserUpdateDto {
    private Long Id;
    private String Document;
    private String Email;
    private String Password;
    private String Name;
    private String FatherLastName;
    private String MotherLastName;
    private LocalDateTime Birthday;
    private String PhoneNumber;
    private String Direction;
    private Gender Gender;
    private CivilState CivilState;
}
