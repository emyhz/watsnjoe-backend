package nl.watsnjoe.s5.watsnjoebackend.controller.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class CreateVisitorRequest {
    @NotNull
    @Getter @Setter
    private String firstName;

    @NotNull
    @Getter @Setter
    private String lastName;

    @NotNull
    @Getter @Setter
    private String email;

    @NotNull
    @Getter @Setter
    private String phoneNumber;

    @Getter @Setter
    private String languageCode;

    public CreateVisitorRequest(String firstName, String lastName,
                                String email, String phoneNumber, String languageCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.languageCode = languageCode;
    }

    public CreateVisitorRequest() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateVisitorRequest that = (CreateVisitorRequest) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
