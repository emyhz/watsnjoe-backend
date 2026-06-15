package nl.watsnjoe.s5.watsnjoebackend.controller;

import nl.watsnjoe.s5.watsnjoebackend.business.VisitorService;
import nl.watsnjoe.s5.watsnjoebackend.domain.Visitor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VisitorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class Visitorcontrollerapitest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitorService visitorService;

    private final Visitor visitor1 = new Visitor(1L, "Anja", "de Vries",
            "anja@email.nl", "0612345678", new Locale("nl"), null);
    private final Visitor visitor2 = new Visitor(2L, "Thomas", "Jansen",
            "thomas@email.nl", "0687654321", new Locale("nl"), null);

    // ── POST /visitors ──────────────────────────────────────────────────────

    @Test
    void createVisitor_ShouldReturn201() throws Exception {
        when(visitorService.createVisitor("Anja", "de Vries",
                "anja@email.nl", "0612345678", "nl")).thenReturn(visitor1);

        String body = """
                {
                    "firstName": "Anja",
                    "lastName": "de Vries",
                    "email": "anja@email.nl",
                    "phoneNumber": "0612345678",
                    "languageCode": "nl"
                }
                """;

        mockMvc.perform(post("/visitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));

        verify(visitorService).createVisitor("Anja", "de Vries",
                "anja@email.nl", "0612345678", "nl");
    }

    // ── GET /visitors ───────────────────────────────────────────────────────

    @Test
    void getAllVisitors_ShouldReturn200() throws Exception {
        when(visitorService.getAllVisitors()).thenReturn(List.of(visitor1, visitor2));

        mockMvc.perform(get("/visitors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("Anja"))
                .andExpect(jsonPath("$[1].firstName").value("Thomas"));

        verify(visitorService).getAllVisitors();
    }

    // ── GET /visitors/{id} ──────────────────────────────────────────────────

    @Test
    void getVisitor_ShouldReturn200_WhenFound() throws Exception {
        when(visitorService.getVisitor(1L)).thenReturn(Optional.of(visitor1));

        mockMvc.perform(get("/visitors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Anja"))
                .andExpect(jsonPath("$.lastName").value("de Vries"))
                .andExpect(jsonPath("$.email").value("anja@email.nl"));

        verify(visitorService).getVisitor(1L);
    }

    @Test
    void getVisitor_ShouldReturn404_WhenNotFound() throws Exception {
        when(visitorService.getVisitor(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/visitors/99"))
                .andExpect(status().isNotFound());

        verify(visitorService).getVisitor(99L);
    }

    // ── GET /visitors/email ─────────────────────────────────────────────────

    @Test
    void getVisitorByEmail_ShouldReturn200_WhenFound() throws Exception {
        when(visitorService.getVisitorByEmail("anja@email.nl"))
                .thenReturn(Optional.of(visitor1));

        mockMvc.perform(get("/visitors/email")
                        .param("email", "anja@email.nl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("anja@email.nl"));

        verify(visitorService).getVisitorByEmail("anja@email.nl");
    }

    @Test
    void getVisitorByEmail_ShouldReturn404_WhenNotFound() throws Exception {
        when(visitorService.getVisitorByEmail("nobody@email.nl"))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/visitors/email")
                        .param("email", "nobody@email.nl"))
                .andExpect(status().isNotFound());

        verify(visitorService).getVisitorByEmail("nobody@email.nl");
    }

    // ── GET /visitors/phone ─────────────────────────────────────────────────

    @Test
    void getVisitorByPhone_ShouldReturn200_WhenFound() throws Exception {
        when(visitorService.getVisitorByPhoneNumber("0612345678"))
                .thenReturn(Optional.of(visitor1));

        mockMvc.perform(get("/visitors/phone")
                        .param("phoneNumber", "0612345678"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneNumber").value("0612345678"));

        verify(visitorService).getVisitorByPhoneNumber("0612345678");
    }

    @Test
    void getVisitorByPhone_ShouldReturn404_WhenNotFound() throws Exception {
        when(visitorService.getVisitorByPhoneNumber("0600000000"))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/visitors/phone")
                        .param("phoneNumber", "0600000000"))
                .andExpect(status().isNotFound());

        verify(visitorService).getVisitorByPhoneNumber("0600000000");
    }

    // ── PUT /visitors/{id} ──────────────────────────────────────────────────

    @Test
    void updateVisitor_ShouldReturn204() throws Exception {
        doNothing().when(visitorService).updateVisitor(
                eq(1L), any(), any(), any(), any(), any());

        String body = """
                {
                    "firstName": "Anja",
                    "lastName": "de Vries",
                    "email": "anja@email.nl",
                    "phoneNumber": "0612345678",
                    "languageCode": "nl"
                }
                """;

        mockMvc.perform(put("/visitors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isNoContent());

        verify(visitorService).updateVisitor(1L, "Anja", "de Vries",
                "anja@email.nl", "0612345678", "nl");
    }

    // ── DELETE /visitors/{id} ───────────────────────────────────────────────

    @Test
    void deleteVisitor_ShouldReturn204() throws Exception {
        doNothing().when(visitorService).deleteVisitor(1L);

        mockMvc.perform(delete("/visitors/1"))
                .andExpect(status().isNoContent());

        verify(visitorService).deleteVisitor(1L);
    }
}
