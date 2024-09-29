import java.io.File;
import java.util.Arrays;

import net.minecraft.client.main.Main;
/** Edited by BlackDev
 * I added MacOS Support for Intel Macs
 * **/

/**
 * Welcome to MCP 1.8.9 for Maven
 * This repository has been created to make working with MCP 1.8.9 easier and cleaner.
 * You can view the MCP 1.8.9 repo here: https://github.com/Marcelektro/MCP-919
 * If you have any questions regarding this, feel free to contact me here: https://marcloud.net/discord
 *
 * Have fun with the MC development ^^
 * Marcelektro
 */

public class Start {
    public static void main(String[] args) {
        // Provide natives
        // Now supports Linux, Windows, and macOS
        String osName = System.getProperty("os.name").toLowerCase();
        String nativePath;

        if (osName.contains("win")) {
            nativePath = "../natives/windows";
        } else if (osName.contains("mac")) {
            nativePath = "../natives/osx";
        } else if (osName.contains("nix") || osName.contains("nux")) {
            nativePath = "../natives/linux";
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + osName);
        }

        System.setProperty("org.lwjgl.librarypath", new File(nativePath).getAbsolutePath());

        Main.main(concat(new String[]{
                        "--version", "MavenMCP",
                        "--accessToken", "0",
                        "--assetsDir", "assets",
                        "--assetIndex", "1.8",
                        "--userProperties", "{}"},
                args)
        );
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
