public class TestThread {
    public static void main(String[] args) {
        MainSuite ms1 = new MainSuite();
        MainSuite ms2 = new MainSuite();

        ms1.setName("Thread - 1");
        ms2.setName("Thread - 2");

        ms1.start();
        ms2.start();

        try{
            ms1.join();
            ms2.join();
        } catch (InterruptedException e) {
            System.out.println("Interruption: " + e.getMessage());
        }

    }
}
