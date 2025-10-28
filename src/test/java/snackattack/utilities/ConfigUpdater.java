package snackattack.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ConfigUpdater {
    private static final String CONFIG_PATH = "configuration.properties";

    public static void updateProperty(String key, String value) {
        try {
            Properties props = new Properties();
            FileInputStream in = new FileInputStream(CONFIG_PATH);
            props.load(in);
            in.close();

            props.setProperty(key, value);

            FileOutputStream out = new FileOutputStream(CONFIG_PATH);
            props.store(out, null);
            out.close();

            System.out.println("📝 Config dosyasi guncellendi: " + key + " = " + value);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("⚠️ Config dosyasi guncellenemedi!");
        }
    }
}
