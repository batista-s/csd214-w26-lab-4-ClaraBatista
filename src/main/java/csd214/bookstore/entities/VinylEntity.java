package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
@DiscriminatorValue("VINYL")
public class VinylEntity extends MusicCollectionEntity {
    @Column(name = "disc_color")
    private String discColor;

    @Column(name = "copies", nullable = false)
    private int copies;

    public VinylEntity() {
    }

    public VinylEntity(String name, double price, String title, String artist, String genre, int year, String productId, double price1, String name1, String discColor, int copies) {
        super(name, price, title, artist, genre, year, productId, price1, name1);
        this.discColor = discColor;
        this.copies = copies;
    }

    public String getDiscColor() {
        return discColor;
    }

    public void setDiscColor(String discColor) {
        this.discColor = discColor;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "VinylEntity{" +
                "discColor='" + discColor + '\'' +
                ", copies=" + copies +
                '}' + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof VinylEntity that)) return false;
        if (!super.equals(o)) return false;
        return copies == that.copies && Objects.equals(discColor, that.discColor) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discColor, copies);
    }
}