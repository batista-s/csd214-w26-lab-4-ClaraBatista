package csd214.bookstore.pojos;

import java.util.Objects;
import java.util.Scanner;
import java.util.UUID;

/**
 * DTO for {@link csd214.bookstore.entities.VinylEntity}
 */
public class Vinyl extends MusicCollection{
    private String discColor;
    private int copies;

    public Vinyl() {}

    public Vinyl(String title, String artist, String genre, int year, double price, String discColor, int copies) {
        super(title, artist, genre, year, price, "VINYL");
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
    public void edit(Scanner input) {
        super.edit(input);
        System.out.println("Edit discColor [" + this.discColor + ":");
        setDiscColor(getInput(input, this.discColor));
        System.out.println("Edit Copies [" + this.copies + ":");
        setCopies(getInput(input, this.copies));
    }

    @Override
    public void initialize(Scanner input) {
        super.initialize(input);
        System.out.println("Enter discColor:");
        setDiscColor(getInput(input, "Undefined"));
        System.out.println("Enter Copies:");
        setCopies(getInput(input, 0));
        super.setName("VINYL");
    }


    @Override
    public void sellItem(){
        System.out.println("Selling Digital Music {color: " + discColor + super.toString() + "}");
        setCopies(getCopies()-1);
    }

    @Override
    public String toString() {
        return "Vinyl{" +
                "discColor='" + discColor + '\'' +
                ", copies=" + copies +
                '}' + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Vinyl vinyl)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(discColor, vinyl.discColor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discColor);
    }
}