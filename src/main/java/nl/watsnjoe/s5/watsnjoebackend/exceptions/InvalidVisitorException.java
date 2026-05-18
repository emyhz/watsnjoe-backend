package nl.watsnjoe.s5.watsnjoebackend.exceptions;

public class InvalidVisitorException extends RuntimeException {
  public InvalidVisitorException(long id) {
    super("Visitor not found with id: " + id);
  }
}
