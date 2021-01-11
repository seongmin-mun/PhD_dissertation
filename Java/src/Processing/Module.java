package Processing;

public class Module {
    public static class SortingFloat {

        public String text;
        public Float count;

        public SortingFloat(String text, Float count) {
            this.text = text;
            this.count = count;
        }

        @Override
        public String toString() {
            return text + "," + count;
        }

    }

    public static class SortingInt {

        public String text;
        public Integer count;

        public SortingInt(String text,  Integer count) {
            this.text = text;
            this.count = count;
        }

        @Override
        public String toString() {
            return text + "," + count;
        }

    }
}