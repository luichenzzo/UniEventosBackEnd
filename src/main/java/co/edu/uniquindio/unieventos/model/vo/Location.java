package co.edu.uniquindio.unieventos.model.vo;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Location {
    @EqualsAndHashCode.Include
    private String name;
    private double price;
    private int ticketsSold;
    private int maxCapacity;//COMN

    @Builder
    public Location(String name, double price, int ticketsSold,int maxCapacity) {
        this.name = name;
        this.price = price;
        this.ticketsSold = ticketsSold;
        this.maxCapacity = maxCapacity;
    }

    public boolean isCapacityAvaible(int quantity){
        return ticketsSold + quantity<= maxCapacity;

    }
}
