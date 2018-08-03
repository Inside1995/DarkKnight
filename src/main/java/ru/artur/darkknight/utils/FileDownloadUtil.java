package ru.artur.darkknight.utils;

import java.io.*;

public class FileDownloadUtil {

    public static byte[] downloadNoneAvatar(String type) {
        byte[] noneAvatar = null;
        try {
            ClassLoader classLoader = FileDownloadUtil.class.getClassLoader();
            File file = new File(classLoader.getResource(type + ".png").getFile());
            InputStream inputStream = new FileInputStream(file);
            noneAvatar = new byte[inputStream.available()];
            while (inputStream.available() > 0) {
                inputStream.read(noneAvatar);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return noneAvatar;
    }
}
