package nl.watsnjoe.s5.watsnjoebackend.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Destination {


    @Setter @Getter
    private Long id;
    @Setter @Getter
    private Long roomNumber;
    @Setter @Getter
    private String roomName;
    @Setter@Getter
    private String name;
    @Getter @Setter
    private int floorNumber;
    @Getter @Setter
    private String estimatedWalkTime;


    public Destination(Long id, Long roomNumber, String roomName, String name, int floorNumber, String estimatedWalkTime){
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomName = roomName;
        this.name = name;
        this.floorNumber = floorNumber;
        this.estimatedWalkTime = estimatedWalkTime;
    }

    public Destination(){}

    @Override
    public String toString() {
        return "Destination{" +
                "id=" + id +
                ", RoomNumber=" + roomNumber +
                ", RoomName='" + roomName + '\'' +
                ", name=" + name +
                ", floorNumber=" + floorNumber +
                ", estimatedWalkTime='" + estimatedWalkTime + '\'' +
                '}';
    }

}
