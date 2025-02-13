package com.MarvelMan.JournalApplication.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SentimentData {
   private String email;
   private String sentiment;
}
