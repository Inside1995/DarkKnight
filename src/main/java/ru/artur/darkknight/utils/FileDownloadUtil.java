package ru.artur.darkknight.utils;

import java.io.*;

public class FileDownloadUtil {
    private static byte[] noneAvatarCache;

    public static byte[] downloadNoneAvatar() {
        if (noneAvatarCache != null && noneAvatarCache.length > 0)
            return noneAvatarCache;
        try {
            ClassLoader classLoader = FileDownloadUtil.class.getClassLoader();
            File file = new File(classLoader.getResource("none.png").getFile());
            InputStream inputStream = new FileInputStream(file);
            noneAvatarCache = new byte[inputStream.available()];
            while (inputStream.available() > 0) {
                inputStream.read(noneAvatarCache);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return noneAvatarCache;
    }
}
