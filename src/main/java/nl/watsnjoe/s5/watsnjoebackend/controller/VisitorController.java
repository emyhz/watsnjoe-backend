package nl.watsnjoe.s5.watsnjoebackend.controller;


import nl.watsnjoe.s5.watsnjoebackend.business.VisitorService;
import nl.watsnjoe.s5.watsnjoebackend.controller.converters.VisitorConverter;
import nl.watsnjoe.s5.watsnjoebackend.controller.dtos.CreateVisitorRequest;
import nl.watsnjoe.s5.watsnjoebackend.controller.dtos.CreateVisitorResponse;
import nl.watsnjoe.s5.watsnjoebackend.controller.dtos.GetVisitorResponse;
import nl.watsnjoe.s5.watsnjoebackend.controller.dtos.UpdateVisitorRequest;
import nl.watsnjoe.s5.watsnjoebackend.domain.Visitor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/visitors")
@CrossOrigin(origins = "*")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    /** register a new visitor */
    @PostMapping
    public ResponseEntity<Object> createVisitor(@RequestBody CreateVisitorRequest request) {
        try {
            Visitor visitor = visitorService.createVisitor(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPhoneNumber(),
                    request.getLanguageCode()
            );
            CreateVisitorResponse response = VisitorConverter.visitorToCreateVisitorResponse(visitor);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**get all visitors */
    @GetMapping
    public ResponseEntity<List<GetVisitorResponse>> getAllVisitors() {
        List<Visitor> visitors = visitorService.getAllVisitors();
        List<GetVisitorResponse> response = new ArrayList<>();
        for (Visitor visitor : visitors) {
            response.add(VisitorConverter.visitorToGetVisitorResponse(visitor));
        }
        return ResponseEntity.ok(response);
    }


    @GetMapping("{id}")
    public ResponseEntity<GetVisitorResponse> getVisitor(@PathVariable long id) {
        Optional<Visitor> visitorOptional = visitorService.getVisitor(id);
        if (visitorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(VisitorConverter.visitorToGetVisitorResponse(visitorOptional.get()));
    }

    /** look up returning visitor by email (return-visit phone screen) */
    @GetMapping("/email")
    public ResponseEntity<Object> getVisitorByEmail(@RequestParam String email) {
        Optional<Visitor> visitorOptional = visitorService.getVisitorByEmail(email);
        if (visitorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Visitor not found");
        }
        return ResponseEntity.ok(VisitorConverter.visitorToGetVisitorResponse(visitorOptional.get()));
    }

    @GetMapping("/phone")
    public ResponseEntity<Object> getVisitorByPhone(@RequestParam String phoneNumber) {
        Optional<Visitor> visitorOptional = visitorService.getVisitorByPhoneNumber(phoneNumber);
        if (visitorOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Visitor not found");
        }
        return ResponseEntity.ok(VisitorConverter.visitorToGetVisitorResponse(visitorOptional.get()));
    }

    /**update visitor details */
    @PutMapping("{id}")
    public ResponseEntity<Object> updateVisitor(@PathVariable("id") long id,
                                                @RequestBody UpdateVisitorRequest request) {
        try {
            request.setId(id);
            visitorService.updateVisitor(
                    id,
                    request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getPhoneNumber(),
                    request.getLanguageCode()
            );
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**remove a visitor */
    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteVisitor(@PathVariable long id) {
        visitorService.deleteVisitor(id);
        return ResponseEntity.noContent().build();
    }
}
