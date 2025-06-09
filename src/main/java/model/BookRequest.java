package model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.javafaker.Faker;
import lombok.*;

import java.util.List;
import java.util.Locale;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With

public class BookRequest {

    private String userId;
    private String isbn;

    public static BookRequest deleteBook(){

        String isbn = "";
        String userID = "";
        BookRequest book = BookRequest.builder()
                .userId(userID)
                .isbn(isbn)
                .build();

        return book;
    }
}
