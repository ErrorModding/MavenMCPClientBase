import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;

import net.minecraft.client.main.Main;


/** Edited by BlackDev
 * I added macOS Support for Intel Macs
 * Main Creddits to Marcelektro and thank you for doing this.
 * you btw need to use this specific jdk
 * https://github.com/AdoptOpenJDK/openjdk8-binaries/releases/tag/jdk8u242-b08
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
        // Detect the operating system
        String osName = System.getProperty("os.name").toLowerCase();
        String osArch = System.getProperty("os.arch").toLowerCase();
        String nativePath;

        if (osName.contains("win")) {
            nativePath = "../natives/windows";
        } else if (osName.contains("mac")) {
        // Check for Apple Silicon (M1, M2) or Intel
        if (osArch.contains("aarch64") || osArch.contains("arm64")) {
            nativePath = "../natives/osx_silicon";
        } else {
            nativePath = "../natives/osx";  // Path for Intel Macs
        }
        } else if (osName.contains("nix") || osName.contains("nux")) {
            nativePath = "../natives/linux";
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + osName);
        }

        // Update the path to point to your actual native folder location
        String absoluteNativePath = new File("/Users/bluefarukon/Documents/GitHub/MavenMCP-1.8.9-all-os/natives/osx").getAbsolutePath();
        System.out.println("Native library path: " + absoluteNativePath);

        // Set the native library path for LWJGL
        System.setProperty("org.lwjgl.librarypath", absoluteNativePath);

        // Start the Minecraft client with the necessary arguments
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