package nl.watsnjoe.s5.watsnjoebackend.business;

import nl.watsnjoe.s5.watsnjoebackend.business.impl.VisitorServiceImpl;
import nl.watsnjoe.s5.watsnjoebackend.domain.Visitor;
import nl.watsnjoe.s5.watsnjoebackend.exceptions.InvalidVisitorException;
import nl.watsnjoe.s5.watsnjoebackend.persistence.VisitorRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Visitorserviceimpltest {

    private final Visitor visitor1 = new Visitor(1L, "Anja", "de Vries",
            "anja@email.nl", "0612345678", new Locale("nl"), null);
    private final Visitor visitor2 = new Visitor(2L, "Thomas", "Jansen",
            "thomas@email.nl", "0687654321", new Locale("nl"), null);

    @Test
    void getAllVisitors_ShouldReturnAllVisitors() {
        VisitorRepository repo = mock(VisitorRepository.class);
        VisitorServiceImpl service = new VisitorServiceImpl(repo);

        when(repo.getAll()).thenReturn(List.of(visitor1, visitor2));

        List<Visitor> result = service.getAllVisitors();

        assertEquals(2, result.size());
        assertTrue(result.contains(visitor1));
        assertTrue(result.contains(visitor2));
        verify(repo).getAll();
    }

    @Test
    void getVisitor_ShouldReturnVisitor_WhenExists() {
        VisitorRepository repo = mock(VisitorRepository.class);
        VisitorServiceImpl service = new VisitorServiceImpl(repo);

        when(repo.get(1L)).thenReturn(Optional.of(visitor1));

        Optional<Visitor> result = service.getVisitor(1L);

        assertTrue(result.isPresent());
        assertEquals(visitor1, result.get());
        verify(repo).get(1L);
    }

    @Test
    void getVisitor_ShouldReturnEmpty_WhenNotFound() {
        VisitorRepository repo = mock(VisitorRepository.class);
        VisitorServiceImpl service = new VisitorServiceImpl(repo);

        when(repo.get(99L)).thenReturn(Optional.empty());

        Optional<Visitor> result = service.getVisitor(99L);

        assertTrue(result.isEmpty());
        verify(repo).get(99L);
    }

    @Test
    void createVisitor_ShouldReturnNewVisitor() {
        VisitorRepository repo = mock(VisitorRepository.class);
        VisitorServiceImpl service = new VisitorServiceImpl(repo);

        when(repo.create(any(Visitor.class))).thenReturn(10L);

        Visitor result = service.createVisitor("Sara", "Bakker",
                "sara@email.nl", "0611111111", "nl");

        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("Sara", result.getFirstName());
        assertEquals("Bakker", result.getLastName());
        assertEquals("sara@email.nl", result.getEmail());
        verify(repo).create(any(Visitor.class));
    }

    @Test
    void createVisitor_ShouldDefaultToNl_WhenLanguageCodeIsNull() {
        VisitorRepository repo = mock(VisitorRepository.class);
        VisitorServiceImpl service = new VisitorServiceImpl(repo);

        when(repo.create(any(Visitor.class))).thenReturn(5L);

        Visitor result = service.createVisitor("Test", "User",
                "test@email.nl", "0600000000", null);

        assertEquals("nl", result.getPreferredLanguage().getLanguage());
        verify(repo).create(any(Visitor.class));
    }

    @Test
    void deleteVisitor_ShouldCallRepositoryDelete() {
        VisitorRepository repo = mock(VisitorRepository.class);
        VisitorServiceImpl service = new VisitorServiceImpl(repo);

        service.deleteVisitor(1L);

        verify(repo).delete(1L);
    }

    @Test
    void getVisitorByEmail_ShouldReturnVisitor_WhenExists() {
        VisitorRepository repo = mock(VisitorRepository.class);
        VisitorServiceImpl service = new VisitorServiceImpl(repo);

        when(repo.getByEmail("anja@email.nl")).thenReturn(Optional.of(visitor1));

        Optional<Visitor> result = service.getVisitorByEmail("anja@email.nl");

        assertTrue(result.isPresent());
        assertEquals(visitor1, result.get());
        verify(repo).getByEmail("anja@email.nl");
    }

    @Test
    void getVisitorByEmail_ShouldReturnEmpty_WhenNotFound() {
        VisitorRepository repo = mock(VisitorRepository.class);
        VisitorServiceImpl service = new VisitorServiceImpl(repo);

        when(repo.getByEmail("nobody@email.nl")).thenReturn(Optional.empty());

        Optional<Visitor> result = service.getVisitorByEmail("nobody@email.nl");

        assertTrue(result.isEmpty());
        verify(repo).getByEmail("nobody@email.nl");
    }

    @Test
    void getVisitorByPhoneNumber_ShouldReturnVisitor_WhenExists() {
        VisitorRepository repo = mock(VisitorRepository.class);
        VisitorServiceImpl service = new VisitorServiceImpl(repo);

        when(repo.getByPhoneNumber("0612345678")).thenReturn(Optional.of(visitor1));

        Optional<Visitor> result = service.getVisitorByPhoneNumber("0612345678");

        assertTrue(result.isPresent());
        assertEquals(visitor1, result.get());
        verify(repo).getByPhoneNumber("0612345678");
    }

    @Test
    void updateVisitor_ShouldCallRepositoryUpdate() throws InvalidVisitorException {
        VisitorRepository repo = mock(VisitorRepository.class);
        VisitorServiceImpl service = new VisitorServiceImpl(repo);

        doNothing().when(repo).update(any(Visitor.class));

        service.updateVisitor(1L, "Anja", "Updated",
                "anja@email.nl", "0612345678", "nl");

        verify(repo).update(argThat(v ->
                v.getId() == 1L &&
                        v.getFirstName().equals("Anja") &&
                        v.getLastName().equals("Updated") &&
                        v.getEmail().equals("anja@email.nl")
        ));
    }
}
