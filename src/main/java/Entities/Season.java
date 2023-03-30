package Entities;

import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Season {

    private long ID;

    private long SERIE_ID;
    private int Number;
    private LocalDate DebutDate;
    private Image Thumbnail;
    List<Episode> episodeList;

    public Season(long SERIE_ID, int number, LocalDate debutDate, Image thumbnail) {
        this.SERIE_ID = SERIE_ID;
        Number = number;
        DebutDate = debutDate;
        Thumbnail = thumbnail;
    }

    public Season(long ID, long SERIE_ID, int number, LocalDate debutDate, Image thumbnail, List<Episode> episodeList) {
        this.ID = ID;
        this.SERIE_ID = SERIE_ID;
        Number = number;
        DebutDate = debutDate;
        Thumbnail = thumbnail;
        this.episodeList = episodeList;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public LocalDate getDebutDate() {
        return DebutDate;
    }

    public void setDebutDate(LocalDate debutDate) {
        DebutDate = debutDate;
    }

    public Image getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        Thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Season{" +
                "ID=" + ID +
                ", Number=" + Number +
                ", DebutDate=" + DebutDate +
                ", Thumbnail=" + Thumbnail +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Season season)) return false;
        return getID() == season.getID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getID());
    }

    public void add_Episode(Episode episode)
    {
        episodeList.add(episode);
    }

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }


}
