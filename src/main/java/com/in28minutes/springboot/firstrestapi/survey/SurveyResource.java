package com.in28minutes.springboot.firstrestapi.survey;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class SurveyResource {

  SurveyService surveyService;

  public SurveyResource(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  // /surveys
  @RequestMapping(value = "/surveys")
  public List<Survey> retrieveAllSurveys() {
    return surveyService.retrieveAllSurveys();
  }

  // GET /surveys/{surveyId}
  @RequestMapping(value = "/surveys/{surveyId}", method = { RequestMethod.GET })
  public Survey retrieveSurveyById(@PathVariable String surveyId) {
    Survey survey = surveyService.retrieveSurveyById(surveyId);
    if (survey == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return survey;
  }

  // GET /surveys/{surveyId}/questions
  @RequestMapping(value = "/surveys/{surveyId}/questions", method = { RequestMethod.GET })
  public List<Question> retrieveAllSurveyQuestions(@PathVariable String surveyId) {
    return surveyService.retrieveAllSurveyQuestions(surveyId);
  }

  // POST /surveys/{surveyId}/questions
  @RequestMapping(value = "/surveys/{surveyId}/questions", method = { RequestMethod.POST })
  public ResponseEntity<Object> addNewSurveyQuestion(
      @PathVariable String surveyId,
      @RequestBody Question question) {
    String questionId = surveyService.addNewSurveyQuestion(surveyId, question);

    // /surveys/{surveyId}/questions/{questionId}
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{questionId}")
        .buildAndExpand(questionId)
        .toUri();
    return ResponseEntity.created(location).build();
  }

  // GET /surveys/{surveyId}/questions/{questionId}
  @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = { RequestMethod.GET })
  public Question retrieveSpecificSurveyQuestion(@PathVariable String surveyId, @PathVariable String questionId) {
    Question question = surveyService.retrieveSpecificSurveyQuestion(surveyId, questionId);
    if (question == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    return question;
  }

  // DELETE /surveys/{surveyId}/questions/{questionId}
  @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = { RequestMethod.DELETE })
  public ResponseEntity<Object> deleteSurveyQuestion(@PathVariable String surveyId,
      @PathVariable String questionId) {
    surveyService.deleteSurveyQuestion(surveyId, questionId);

    return ResponseEntity.noContent().build();
  }

  // PUT /surveys/{surveyId}/questions/{questionId}
  @RequestMapping(value = "/surveys/{surveyId}/questions/{questionId}", method = { RequestMethod.PUT })
  public ResponseEntity<Object> updateSurveyQuestion(@PathVariable String surveyId,
      @PathVariable String questionId,
      @RequestBody Question question) {
    surveyService.updateSurveyQuestion(surveyId, questionId, question);

    return ResponseEntity.noContent().build();
  }
}
