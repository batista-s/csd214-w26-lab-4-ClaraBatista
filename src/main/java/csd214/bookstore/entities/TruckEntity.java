package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
@DiscriminatorValue("TRUCK")
public class TruckEntity extends VehicleEntity {
    @Column(name = "towing_capacity")
    private double towingCapacity;

    public TruckEntity() {
    }

    public TruckEntity(String name, double price, String make, String model, int year, int mileage, double towingCapacity) {
        super(name, price, make, model, year, mileage);
        this.towingCapacity = towingCapacity;
    }

    public double getTowingCapacity() {
        return towingCapacity;
    }

    public void setTowingCapacity(double towingCapacity) {
        this.towingCapacity = towingCapacity;
    }

    @Override
    public String toString() {
        return "TruckEntity{" +
                "towingCapacity=" + towingCapacity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TruckEntity that)) return false;
        if (!super.equals(o)) return false;
        return Double.compare(towingCapacity, that.towingCapacity) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), towingCapacity);
    }
}