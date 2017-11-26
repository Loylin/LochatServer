package cn.loylin.lochat.xmpp.config;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigManager {

    private static final Log log = LogFactory.getLog(ConfigManager.class);

    private static Configuration config;

    private static ConfigManager instance;

    private ConfigManager() {
        loadConfig();
    }

    /**
     * Returns the singleton instance of ConfigManger.
     *
     * @return the instance
     */
    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                instance = new ConfigManager();
            }
        }
        return instance;
    }

    /**
     * Loads the default configuration file.
     */
    public void loadConfig() {
        loadConfig("config.xml");
    }

    /**
     * Loads the specific configuration file.
     *
     * @param configFileName the file name
     */
    public void loadConfig(String configFileName) {
        try {
            ConfigurationFactory factory = new ConfigurationFactory(
                    configFileName);
            config = factory.getConfiguration();
            log.info("Configuration loaded: " + configFileName);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            throw new RuntimeException("Configuration loading error: "
                    + configFileName, ex);
        }
    }

    /**
     * Returns the loaded configuration object.
     *
     * @return the configuration
     */
    public Configuration getConfig() {
        return config;
    }
}
