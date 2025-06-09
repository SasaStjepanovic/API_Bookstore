package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import lombok.*;
import lombok.Builder;
import java.util.Locale;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With

public class UserRequest {

    private String userName;
    private String password;

    public static UserRequest createUser(){
        Faker faker = new Faker(new Locale("en-US"));

        UserRequest user = UserRequest.builder()
                .userName(faker.name().firstName())
                .password("tesT123!@#")
                .build();

        return user;
    }

    public static UserRequest createEmptyUser(){

        String userName = "";

        UserRequest user = UserRequest.builder()
                .userName(userName)
                .password("tesT123!@#")
                .build();

        return user;
    }

    public static UserRequest createEmptyPassword(){
        Faker faker = new Faker(new Locale("en-US"));
        String password = "";

        UserRequest user = UserRequest.builder()
                .userName(faker.name().firstName())
                .password(password)
                .build();

        return user;
    }

    public static UserRequest generateToken(){
        String username = "";
        String password = "";

        UserRequest user = UserRequest.builder()
                .userName(username)
                .password(password)
                .build();

        return user;
    }

    public static UserRequest createPasswordNoAlphanumeric(){

        Faker faker = new Faker(new Locale("en-US"));
        String passwordWithoutAlphanumeric = "Qw123ere";

        UserRequest user = UserRequest.builder()
                .userName(faker.name().firstName())
                .password(passwordWithoutAlphanumeric)
                .build();

        return user;
    }

    public static UserRequest createPasswordNoLowercase(){

        Faker faker = new Faker(new Locale("en-US"));
        String passwordNoLowercase = "Q123!@1";

        UserRequest user = UserRequest.builder()
                .userName(faker.name().firstName())
                .password(passwordNoLowercase)
                .build();

        return user;
    }

    public static UserRequest createPasswordNoUppercase(){

        Faker faker = new Faker(new Locale("en-US"));
        String passwordNoUppercase = "as123!@1";

        UserRequest user = UserRequest.builder()
                .userName(faker.name().firstName())
                .password(passwordNoUppercase)
                .build();

        return user;
    }

    public static UserRequest createPasswordNoDigits(){

        Faker faker = new Faker(new Locale("en-US"));
        String passwordNoUppercase = "asW!@";

        UserRequest user = UserRequest.builder()
                .userName(faker.name().firstName())
                .password(passwordNoUppercase)
                .build();

        return user;
    }

    public static UserRequest createPassword7characters(){

        Faker faker = new Faker(new Locale("en-US"));
        String passwordNoUppercase = "asW!@d2";

        UserRequest user = UserRequest.builder()
                .userName(faker.name().firstName())
                .password(passwordNoUppercase)
                .build();

        return user;
    }

    public static UserRequest AuthorizationNotExistingUser(){

        Faker faker = new Faker(new Locale("en-US"));
        String password = "asW!@d2!2";
        String username = "sasa";

        UserRequest user = UserRequest.builder()
                .userName(username)
                .password(password)
                .build();

        return user;
    }

    public static UserRequest AuthorizationEmptyUser(){

        Faker faker = new Faker(new Locale("en-US"));
        String password = "asW!@d2!2";
        String username = "";

        UserRequest user = UserRequest.builder()
                .userName(username)
                .password(password)
                .build();

        return user;
    }

    public static UserRequest AuthorizationEmptyPassword(){

        Faker faker = new Faker(new Locale("en-US"));
        String password = "";
        String username = "Kjhs!22";

        UserRequest user = UserRequest.builder()
                .userName(username)
                .password(password)
                .build();

        return user;
    }


}
