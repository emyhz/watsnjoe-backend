package nl.watsnjoe.s5.watsnjoebackend.controller;

import nl.watsnjoe.s5.watsnjoebackend.business.VisitorService;
import nl.watsnjoe.s5.watsnjoebackend.controller.dtos.CreateVisitorRequest;
import nl.watsnjoe.s5.watsnjoebackend.controller.dtos.CreateVisitorResponse;
import nl.watsnjoe.s5.watsnjoebackend.controller.dtos.GetVisitorResponse;
import nl.watsnjoe.s5.watsnjoebackend.controller.dtos.UpdateVisitorRequest;
import nl.watsnjoe.s5.watsnjoebackend.domain.Visitor;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class Visitorcontrollertest {

    private final Visitor visitor1 = new Visitor(1L, "Anja", "de Vries",
            "anja@email.nl", "0612345678", new Locale("nl"), null);
    private final Visitor visitor2 = new Visitor(2L, "Thomas", "Jansen",
            "thomas@email.nl", "0687654321", new Locale("nl"), null);

    //POST /visitors

    @Test
    void createVisitor_ShouldReturn201() {
        VisitorService service = mock(VisitorService.class);
        VisitorController controller = new VisitorController(service);

        CreateVisitorRequest request = new CreateVisitorRequest(
                "Anja", "de Vries", "anja@email.nl", "0612345678", "nl");

        when(service.createVisitor("Anja", "de Vries", "anja@email.nl",
                "0612345678", "nl")).thenReturn(visitor1);

        ResponseEntity<Object> result = controller.createVisitor(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertInstanceOf(CreateVisitorResponse.class, result.getBody());
        assertEquals(1L, ((CreateVisitorResponse) result.getBody()).getId());
        verify(service).createVisitor("Anja", "de Vries", "anja@email.nl", "0612345678", "nl");
    }

    @Test
    void createVisitor_ShouldReturn400_WhenServiceThrows() {
        VisitorService service = mock(VisitorService.class);
        VisitorController controller = new VisitorController(service);

        CreateVisitorRequest request = new CreateVisitorRequest(
                null, null, null, null, null);

        when(service.createVisitor(any(), any(), any(), any(), any()))
                .thenThrow(new RuntimeException("Invalid data"));

        ResponseEntity<Object> result = controller.createVisitor(request);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Invalid data", result.getBody());
    }

    //GET /visitors

    @Test
    void getAllVisitors_ShouldReturn200() {
        VisitorService service = mock(VisitorService.class);
        VisitorController controller = new VisitorController(service);

        when(service.getAllVisitors()).thenReturn(List.of(visitor1, visitor2));

        ResponseEntity<List<GetVisitorResponse>> result = controller.getAllVisitors();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(2, result.getBody().size());
        verify(service).getAllVisitors();
    }

    //GET /visitors/{id}

    @Test
    void getVisitor_ShouldReturn200_WhenFound() {
        VisitorService service = mock(VisitorService.class);
        VisitorController controller = new VisitorController(service);

        when(service.getVisitor(1L)).thenReturn(Optional.of(visitor1));

        ResponseEntity<GetVisitorResponse> result = controller.getVisitor(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals("Anja", result.getBody().getFirstName());
        verify(service).getVisitor(1L);
    }

    @Test
    void getVisitor_ShouldReturn404_WhenNotFound() {
        VisitorService service = mock(VisitorService.class);
        VisitorController controller = new VisitorController(service);

        when(service.getVisitor(99L)).thenReturn(Optional.empty());

        ResponseEntity<GetVisitorResponse> result = controller.getVisitor(99L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(service).getVisitor(99L);
    }

    //GET /visitors/email

    @Test
    void getVisitorByEmail_ShouldReturn200_WhenFound() {
        VisitorService service = mock(VisitorService.class);
        VisitorController controller = new VisitorController(service);

        when(service.getVisitorByEmail("anja@email.nl")).thenReturn(Optional.of(visitor1));

        ResponseEntity<Object> result = controller.getVisitorByEmail("anja@email.nl");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(service).getVisitorByEmail("anja@email.nl");
    }

    @Test
    void getVisitorByEmail_ShouldReturn404_WhenNotFound() {
        VisitorService service = mock(VisitorService.class);
        VisitorController controller = new VisitorController(service);

        when(service.getVisitorByEmail("nobody@email.nl")).thenReturn(Optional.empty());

        ResponseEntity<Object> result = controller.getVisitorByEmail("nobody@email.nl");

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(service).getVisitorByEmail("nobody@email.nl");
    }

    //GET /visitors/phone

    @Test
    void getVisitorByPhone_ShouldReturn200_WhenFound() {
        VisitorService service = mock(VisitorService.class);
        VisitorController controller = new VisitorController(service);

        when(service.getVisitorByPhoneNumber("0612345678")).thenReturn(Optional.of(visitor1));

        ResponseEntity<Object> result = controller.getVisitorByPhone("0612345678");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(service).getVisitorByPhoneNumber("0612345678");
    }

    @Test
    void getVisitorByPhone_ShouldReturn404_WhenNotFound() {
        VisitorService service = mock(VisitorService.class);
        VisitorController controller = new VisitorController(service);

        when(service.getVisitorByPhoneNumber("0600000000")).thenReturn(Optional.empty());

        ResponseEntity<Object> result = controller.getVisitorByPhone("0600000000");

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(service).getVisitorByPhoneNumber("0600000000");
    }

    //PUT /visitors/{id}

    @Test
    void updateVisitor_ShouldReturn204() {
        VisitorService service = mock(VisitorService.class);
        VisitorController controller = new VisitorController(service);

        UpdateVisitorRequest request = new UpdateVisitorRequest(
                1L, "Anja", "de Vries", "anja@email.nl", "0612345678", "nl");

        ResponseEntity<Object> result = controller.updateVisitor(1L, request);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(service).updateVisitor(1L, "Anja", "de Vries",
                "anja@email.nl", "0612345678", "nl");
    }

    @Test
    void updateVisitor_ShouldReturn400_WhenServiceThrows() {
        VisitorService service = mock(VisitorService.class);
        VisitorController controller = new VisitorController(service);

        UpdateVisitorRequest request = new UpdateVisitorRequest(
                99L, "X", "X", "x@x.nl", "000", "nl");

        doThrow(new RuntimeException("Visitor not found with id: 99"))
                .when(service).updateVisitor(eq(99L), any(), any(), any(), any(), any());

        ResponseEntity<Object> result = controller.updateVisitor(99L, request);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Visitor not found with id: 99", result.getBody());
    }

    // DELETE /visitors/{id}

    @Test
    void deleteVisitor_ShouldReturn204() {
        VisitorService service = mock(VisitorService.class);
        VisitorController controller = new VisitorController(service);

        ResponseEntity<Object> result = controller.deleteVisitor(1L);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(service).deleteVisitor(1L);
    }
}
