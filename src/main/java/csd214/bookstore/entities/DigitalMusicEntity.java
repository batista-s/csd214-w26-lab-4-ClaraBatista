package csd214.bookstore.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
@DiscriminatorValue("DIGITAL_MUSIC")
public class DigitalMusicEntity extends MusicCollectionEntity {
    @Column(name = "link")
    private String link;

    public DigitalMusicEntity() {
    }

    public DigitalMusicEntity(String name, double price, String title, String artist, String genre, int year, String productId, double price1, String name1, String link) {
        super(name, price, title, artist, genre, year, productId, price1, name1);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "DigitalMusicEntity{" +
                "link='" + link + '\'' +
                '}' + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DigitalMusicEntity that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(link, that.link) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), link);
    }
}