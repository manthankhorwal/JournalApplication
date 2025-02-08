package com.MarvelMan.JournalApplication.cache;

import com.MarvelMan.JournalApplication.entity.ConfigJournalAppEntity;
import com.MarvelMan.JournalApplication.repository.ConfigJournalAppRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum Keys{
        WEATHER_API,
        WEATHER_API_URL
    }

  public Map<String,String> appCache;

  @Autowired
  private ConfigJournalAppRepository configJournalAppRepository;

  @PostConstruct
  public void init(){
      appCache=new HashMap<>();
      List<ConfigJournalAppEntity> list=configJournalAppRepository.findAll();
      for(ConfigJournalAppEntity e:list){
          appCache.put(e.getKey(),e.getValue());
      }

  }
}
