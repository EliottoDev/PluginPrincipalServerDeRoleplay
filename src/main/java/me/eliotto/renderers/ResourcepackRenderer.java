package me.eliotto.renderers;

import me.eliotto.Main;

import java.util.logging.Logger;
import java.util.zip.ZipFile;

public class ResourcepackRenderer {

    public ResourcepackRenderer(Logger logger, Main plugin){


        Zipper zipper = new Zipper("src/main/java/me/eliotto/resourcepack", logger, plugin);
        ZipFile file = zipper.getFile();
    }

    public boolean isUploaded(){

        return true;

    }
}
