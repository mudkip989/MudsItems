package mudkip989.plugins.mudsitems;

import org.bukkit.configuration.file.*;
import org.bukkit.plugin.java.*;

import java.io.*;
import java.util.logging.*;

public class FileManager {
    JavaPlugin plugin;
    String fileName;
    FileConfiguration fileConfig;
    File configFile;

    public FileManager(JavaPlugin plugin, String fileName){
        this.plugin = plugin;
        this.fileName = fileName;

        saveDefaultConfig();
    }

    public void reloadConfig(){
        if(configFile == null) configFile = new File(plugin.getDataFolder(), fileName);

        fileConfig = YamlConfiguration.loadConfiguration(configFile);
        InputStream defaultStream = plugin.getResource(fileName);
        if(defaultStream != null){
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            fileConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig(){
        if(fileConfig == null) reloadConfig();
        return fileConfig;
    }

    public void saveConfig(){
        if(fileConfig == null || configFile == null) return;
        try{
            getConfig().save(configFile);
        }
        catch(IOException error){
            plugin.getLogger().log(Level.SEVERE, "DF Spigot: Could not save config to " + configFile, error);
        }
    }

    public void saveDefaultConfig(){
        if(configFile == null) configFile = new File(plugin.getDataFolder(), fileName);

        if(!configFile.exists()){
            plugin.saveResource(fileName, false);
        }
    }
}