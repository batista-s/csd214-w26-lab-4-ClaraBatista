package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
public class MusicCollectionEntity extends ProductEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "artist")
    private String artist;

    @Column(name = "genre")
    private String genre;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "name")
    private String name;

    public MusicCollectionEntity() {
    }

    public MusicCollectionEntity(String name, double price, String title, String artist, String genre, int year, String productId) {
        super(name, price);
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MusicCollectionEntity{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                ", productId='" + productId + '\'' +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MusicCollectionEntity that)) return false;
        if (!super.equals(o)) return false;
        return year == that.year && Double.compare(price, that.price) == 0 && Objects.equals(title, that.title) && Objects.equals(artist, that.artist) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, artist, genre, year, price);
    }
}