package me.eliotto.renderers;

public class Zipper {

    private String SRC_DIR;
    private String ZIP_DIR;

    public Zipper(String src){

        this.SRC_DIR = src;
        this.ZIP_DIR = src.concat(".zip");
        zipFolderStructure(SRC_DIR, ZIP_DIR);
    }

    private static void zipFolderStructure(String SRC_DIR, String ZIP_DIR){

    }
}
