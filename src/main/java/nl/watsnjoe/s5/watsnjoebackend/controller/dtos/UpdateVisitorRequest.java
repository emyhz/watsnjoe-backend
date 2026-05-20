package nl.watsnjoe.s5.watsnjoebackend.controller.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class UpdateVisitorRequest {

    @Getter @Setter
    private Long id;

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

    public UpdateVisitorRequest(Long id, String firstName, String lastName,
                                String email, String phoneNumber, String languageCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.languageCode = languageCode;
    }

    public UpdateVisitorRequest() {}
}
