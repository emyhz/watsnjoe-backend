package nl.watsnjoe.s5.watsnjoebackend.controller.converters;

import nl.watsnjoe.s5.watsnjoebackend.controller.dtos.CreateVisitorResponse;
import nl.watsnjoe.s5.watsnjoebackend.controller.dtos.GetVisitorResponse;
import nl.watsnjoe.s5.watsnjoebackend.domain.Visitor;

public class VisitorConverter {

    public VisitorConverter() {}

    public static CreateVisitorResponse visitorToCreateVisitorResponse(Visitor visitor) {
        return new CreateVisitorResponse(visitor.getId());
    }

    public static GetVisitorResponse visitorToGetVisitorResponse(Visitor visitor) {
        return new GetVisitorResponse(
                visitor.getId(),
                visitor.getFirstName(),
                visitor.getLastName(),
                visitor.getEmail(),
                visitor.getPhoneNumber(),
                visitor.getPreferredLanguage() != null
                        ? visitor.getPreferredLanguage().getLanguage()
                        : "nl"
        );
    }
}
