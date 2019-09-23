public class TestCodingRule {

    public static void main(String[] args) {
        String m = "ER-";
        for (int i = 1; i <= 5; i++) {
            String format = String.format("%05d", i);
            System.out.println(m + format);
        }
    }

}
