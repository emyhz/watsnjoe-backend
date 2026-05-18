package nl.watsnjoe.s5.watsnjoebackend.persistence;

import nl.watsnjoe.s5.watsnjoebackend.domain.Visitor;

import java.util.List;
import java.util.Optional;

public interface VisitorRepository {
    long create(Visitor visitor);
    void delete(long visitorId);
    Optional<Visitor> get(long visitorId);
    List<Visitor> getAll();
    void update(Visitor visitor);
    Optional<Visitor> getByEmail(String email);
    Optional<Visitor> getByPhoneNumber(String phoneNumber);
}
