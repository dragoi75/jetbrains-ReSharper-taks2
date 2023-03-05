public record Chapter(int numberOfChapter, int numberOfQuestions, double numberOfHours) implements Comparable<Chapter> {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chapter chapter = (Chapter) o;

        if (numberOfChapter != chapter.numberOfChapter) return false;
        if (numberOfQuestions != chapter.numberOfQuestions) return false;
        return Double.compare(chapter.numberOfHours, numberOfHours) == 0;
    }

    /**
     * Comparator of two fractions Ki / Ti
     *
     * @param other the object to be compared.
     * @return 0 if equal, negative number if the fraction of other is bigger, positive number otherwise
     */
    @Override
    public int compareTo(Chapter other) {
        return Double.compare(
                (double) other.numberOfQuestions() / other.numberOfHours(),
                (double) this.numberOfQuestions() / this.numberOfHours());
    }
}
