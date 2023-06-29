package domain;

import java.util.Objects;

public class Location {

    private final Rank rank;
    private final File file;

    private Location(final Rank rank, final File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Location from(final char rankName, final char fileName) {
        Rank rank = Rank.getRank(rankName);
        File file = File.getFile(fileName);

        return new Location(rank, file);
    }

    public char getRank() {
        return rank.getName();
    }

    public char getFile() {
        return file.getName();
    }

    public boolean isSameRank(final char rank) {
        return this.rank.isSameRank(rank);
    }

    public boolean isSameFile(final char file) {
        return this.file.isSameFile(file);
    }

    public boolean isSameLocation(final char rankIndex, final char fileIndex) {
        return rank.isSameRank(rankIndex) && file.isSameFile(fileIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return getRank() == location.getRank() && getFile() == location.getFile();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRank(), getFile());
    }
}
