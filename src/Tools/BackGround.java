package Tools;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class BackGround {

    private static ImageView imgBg;

    public static ImageView getImgBg() {
        return imgBg;
    }

    public static void setImgBg(ImageView imgBg) {
        BackGround.imgBg = imgBg;
    }

    public static void initBackground(String path) {
        imgBg = new ImageView(path);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getBounds(); // Récupération de la taille de l'écran
        imgBg.setFitHeight(primaryScreenBounds.getHeight());
        imgBg.setFitWidth( primaryScreenBounds.getWidth());
    }
}
