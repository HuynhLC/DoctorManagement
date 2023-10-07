
import controller.Manager;

public class Main {

    public static void main(String[] args) {
        String mChon[] = {"Add a doctor. ", "Update doctor.", "Delete doctor.", "Search doctor."};
        Manager d = new Manager("====== Doctor Management ======", mChon,"Exit.");
        d.run();
        }
    }