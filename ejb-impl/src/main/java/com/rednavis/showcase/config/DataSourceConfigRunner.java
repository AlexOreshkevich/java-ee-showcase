package com.rednavis.showcase.config;

import com.google.common.io.Resources;
import java.io.InputStream;
import org.yaml.snakeyaml.Yaml;

/**
 * Example of how to read YAML files from classpath.
 */
public class DataSourceConfigRunner {

  public static void main(String[] args) throws Exception {
    Yaml yaml = new Yaml();
    try (InputStream in = Resources.getResource("datasource.yml").openStream()) {
      DataSourceConfig dataSourceConfig = yaml.loadAs(in, DataSourceConfig.class);
      System.out.println(dataSourceConfig);
    }
  }
}
