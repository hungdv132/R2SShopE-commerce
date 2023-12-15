package org.hurc.cms;

import org.hibernate.collection.spi.PersistentCollection;
import org.hurc.cms.dto.UserDto;
import org.hurc.cms.entity.User;
import org.modelmapper.Condition;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CmsApplication {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    //    config for model mapper , addMap
    return new ModelMapper();
  }

  public static void main(String[] args) {
    SpringApplication.run(CmsApplication.class, args);
  }

}
