package nl.watsnjoe.s5.watsnjoebackend.persistence.mock;

import nl.watsnjoe.s5.watsnjoebackend.domain.Visitor;
import nl.watsnjoe.s5.watsnjoebackend.exceptions.InvalidVisitorException;
import nl.watsnjoe.s5.watsnjoebackend.persistence.VisitorRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FakeVisitorRepository implements VisitorRepository {

    private static long nextId = 1;
    private final List<Visitor> savedVisitors;

    public FakeVisitorRepository() {
        this.savedVisitors = new ArrayList<>();
        seedData();
    }

    private void seedData() {
        Visitor anja = new Visitor();
        anja.setFirstName("Anja");
        anja.setLastName("de Vries");
        anja.setEmail("anja.devries@email.nl");
        anja.setPhoneNumber("0612345678");
        anja.setPreferredLanguage(new Locale("nl"));
        create(anja);

        Visitor thomas = new Visitor();
        thomas.setFirstName("Thomas");
        thomas.setLastName("Jansen");
        thomas.setEmail("thomas.jansen@email.nl");
        thomas.setPhoneNumber("0687654321");
        thomas.setPreferredLanguage(new Locale("nl"));
        create(thomas);

        Visitor sarah = new Visitor();
        sarah.setFirstName("Sarah");
        sarah.setLastName("Williams");
        sarah.setEmail("sarah.williams@email.co.uk");
        sarah.setPhoneNumber("0698765432");
        sarah.setPreferredLanguage(new Locale("en"));
        create(sarah);
    }

    @Override
    public long create(Visitor visitor) {
        visitor.setId(nextId++);
        this.savedVisitors.add(visitor);
        return visitor.getId();
    }

    @Override
    public void delete(long visitorId) {
        Optional<Visitor> found = this.get(visitorId);
        if (found.isPresent()) {
            this.savedVisitors.remove(found.get());
        }
    }

    @Override
    public Optional<Visitor> get(long visitorId) {
        for (Visitor visitor : savedVisitors) {
            if (visitor.getId() == visitorId) {
                return Optional.of(visitor);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Visitor> getAll() {
        return Collections.unmodifiableList(this.savedVisitors);
    }

    @Override
    public void update(Visitor visitor) throws InvalidVisitorException {
        Optional<Visitor> found = this.get(visitor.getId());
        if (found.isEmpty()) {
            throw new InvalidVisitorException(visitor.getId());
        }
        Visitor existing = found.get();
        existing.setFirstName(visitor.getFirstName());
        existing.setLastName(visitor.getLastName());
        existing.setEmail(visitor.getEmail());
        existing.setPhoneNumber(visitor.getPhoneNumber());
        existing.setPreferredLanguage(visitor.getPreferredLanguage());
        existing.setPrimaryResident(visitor.getPrimaryResident());
    }

    @Override
    public Optional<Visitor> getByEmail(String email) {
        for (Visitor visitor : savedVisitors) {
            if (visitor.getEmail().equalsIgnoreCase(email)) {
                return Optional.of(visitor);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Visitor> getByPhoneNumber(String phoneNumber) {
        for (Visitor visitor : savedVisitors) {
            if (visitor.getPhoneNumber().equals(phoneNumber)) {
                return Optional.of(visitor);
            }
        }
        return Optional.empty();
    }

}
