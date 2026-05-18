package nl.watsnjoe.s5.watsnjoebackend.domain;

import lombok.Getter;
import lombok.Setter;

public class Faq {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String question;

    @Getter @Setter
    private String answer;

    /** Display / sort order in the accordion */
    @Getter @Setter
    private int displayOrder;

    /** Whether this FAQ is currently visible */
    @Getter @Setter
    private boolean active;

    public Faq(Long id, String question, String answer, int displayOrder, boolean active) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.displayOrder = displayOrder;
        this.active = active;
    }

    public Faq() {}

    @Override
    public String toString() {
        return "FAQ{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", displayOrder=" + displayOrder +
                ", active=" + active +
                '}';
    }
}
