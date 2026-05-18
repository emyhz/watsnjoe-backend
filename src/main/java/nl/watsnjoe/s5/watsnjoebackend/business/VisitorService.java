package nl.watsnjoe.s5.watsnjoebackend.business;

import nl.watsnjoe.s5.watsnjoebackend.domain.Visitor;
import nl.watsnjoe.s5.watsnjoebackend.exceptions.InvalidVisitorException;

import java.util.List;
import java.util.Optional;

public interface VisitorService {
    Visitor createVisitor(String firstName, String lastName, String email, String phoneNumber, String languageCode);
    void deleteVisitor(long visitorId);
    List<Visitor> getAllVisitors();
    Optional<Visitor> getVisitor(long visitorId);
    Optional<Visitor> getVisitorByEmail(String email);
    Optional<Visitor> getVisitorByPhoneNumber(String phoneNumber);
    void updateVisitor(long id, String firstName, String lastName, String email, String phoneNumber, String languageCode) throws InvalidVisitorException;
}
