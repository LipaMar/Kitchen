public class KitchenApp {

    public static void main(String[] args) {
        Repository db = new Repository();
        MainFrame menu;
        if (db.isConnected())
            menu = new MainFrame();

    }

}
