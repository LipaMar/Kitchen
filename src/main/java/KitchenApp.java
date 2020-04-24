public class KitchenApp {

    public static void main(String[] args) {
        Repository db = new Repository();
        KitchenFrame menu;
        if (db.isConnected())
            menu = new KitchenFrame();

    }

}
