package com.danit.erp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper mapper = new ModelMapper();
    mapper.getConfiguration()
      .setMatchingStrategy(MatchingStrategies.STRICT)
      .setFieldMatchingEnabled(true)
      .setSkipNullEnabled(true)
      .setFieldAccessLevel(AccessLevel.PRIVATE);
    return mapper;
  }
}