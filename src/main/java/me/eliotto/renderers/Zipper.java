package me.eliotto.renderers;

import me.eliotto.Main;
import org.bukkit.ChatColor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Zipper {

    public String getSourceDirectory() {
        return SRC_DIR;
    }

    public String getZipDirectory() {
        return ZIP_DIR;
    }

    public ZipFile getFile() {
        return zip;
    }

    private String SRC_DIR;
    private String ZIP_DIR;
    private Logger logger;
    private ZipFile zip;
    private Main plugin;

    public Zipper(String src, Logger logger, Main plugin){

        this.plugin = plugin;
        this.logger = logger;
        this.SRC_DIR = src;
        this.ZIP_DIR = src.concat(".zip");
        zipFolderStructure(SRC_DIR, ZIP_DIR);
    }

    private void zipFolderStructure(String SRC_DIR, String ZIP_DIR){
        try (FileOutputStream fos = new FileOutputStream(ZIP_DIR);
             ZipOutputStream zos = new ZipOutputStream(fos)) {
            Path sourcePath = Paths.get(SRC_DIR);
            this.logger.info(" Empieza la compresion del pack de texturas");
            Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>(){
                @Override
                public FileVisitResult preVisitDirectory(final Path dir, final BasicFileAttributes attrs) throws IOException, IOException {
                    if(!sourcePath.equals(dir)){
                        logger.info("Directory- " + dir);
                        zos.putNextEntry(new ZipEntry(sourcePath.relativize(dir).toString() + "/"));
                        zos.closeEntry();
                    }
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
                    logger.info("File Name- " + sourcePath.relativize(file).toString());
                    zos.putNextEntry(new ZipEntry(sourcePath.relativize(file).toString()));
                    Files.copy(file, zos);
                    zos.closeEntry();
                    return FileVisitResult.CONTINUE;
                }});
            this.zip = new ZipFile(ZIP_DIR);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            this.logger.severe(ChatColor.RED+" Se ha producido un error en la compresion del pack de texturas");
            this.plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }
}
