package com.rednavis.showcase.config;

import com.google.common.io.Resources;
import com.rednavis.showcase.exception.BeanInstantiationException;
import java.io.IOException;
import java.io.InputStream;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import org.yaml.snakeyaml.Yaml;

@ApplicationScoped
public class DataSourceConfigProvider {

  @Produces
  @Dependent
  public DataSourceConfig dataSourceConfig() {
    try (InputStream in = Resources.getResource("datasource.yml").openStream()) {
      return new Yaml().loadAs(in, DataSourceConfig.class);
    } catch (IOException e) {
      throw new BeanInstantiationException(e);
    }
  }
}
