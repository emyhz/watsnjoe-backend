package nl.watsnjoe.s5.watsnjoebackend.business.impl;

import nl.watsnjoe.s5.watsnjoebackend.business.VisitorService;
import nl.watsnjoe.s5.watsnjoebackend.domain.Visitor;
import nl.watsnjoe.s5.watsnjoebackend.exceptions.InvalidVisitorException;
import nl.watsnjoe.s5.watsnjoebackend.persistence.VisitorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepository visitorRepository;

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public Visitor createVisitor(String firstName, String lastName, String email,
                                 String phoneNumber, String languageCode) {
        Visitor visitor = new Visitor();
        visitor.setFirstName(firstName);
        visitor.setLastName(lastName);
        visitor.setEmail(email);
        visitor.setPhoneNumber(phoneNumber);
        visitor.setPreferredLanguage(new Locale(languageCode != null ? languageCode : "nl"));

        long newId = visitorRepository.create(visitor);
        visitor.setId(newId);
        return visitor;
    }

    @Override
    public void deleteVisitor(long visitorId) {
        visitorRepository.delete(visitorId);
    }

    @Override
    public List<Visitor> getAllVisitors() {
        return visitorRepository.getAll();
    }

    @Override
    public Optional<Visitor> getVisitor(long visitorId) {
        return visitorRepository.get(visitorId);
    }

    @Override
    public Optional<Visitor> getVisitorByEmail(String email) {
        return visitorRepository.getByEmail(email);
    }

    @Override
    public Optional<Visitor> getVisitorByPhoneNumber(String phoneNumber) {
        return visitorRepository.getByPhoneNumber(phoneNumber);
    }

    @Override
    public void updateVisitor(long id, String firstName, String lastName,
                              String email, String phoneNumber,
                              String languageCode) throws InvalidVisitorException {
        Visitor visitor = new Visitor();
        visitor.setId(id);
        visitor.setFirstName(firstName);
        visitor.setLastName(lastName);
        visitor.setEmail(email);
        visitor.setPhoneNumber(phoneNumber);
        visitor.setPreferredLanguage(new Locale(languageCode != null ? languageCode : "nl"));
        visitorRepository.update(visitor);
    }
}
