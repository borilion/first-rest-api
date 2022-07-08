package com.in28minutes.springboot.firstrestapi.survey;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SurveyResourceIT {
  // 20220708082114
  // http://localhost:1888/surveys/survey1/questions/question1

  // {
  // "id": "Question1",
  // "description": "Most Popular Cloud Platform Today",
  // "options": [
  // "AWS",
  // "Azure",
  // "Google Cloud",
  // "Oracle Cloud"
  // ],
  // "correctAnswer": "AWS"
  // }

  @Test
  void retrieveSpecificSurveyQuestion_basicScenario() {

  }
}
