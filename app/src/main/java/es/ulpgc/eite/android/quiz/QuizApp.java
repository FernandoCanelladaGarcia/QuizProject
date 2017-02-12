package es.ulpgc.eite.android.quiz;

import android.app.Application;
import android.content.Intent;


public class QuizApp extends Application {

  private QuestionState questionState;
  private CheatState cheatState;
  private ModelQuestionStore modelQuestionStore;

  @Override
  public void onCreate() {
    super.onCreate();

    questionState = new QuestionState();
    questionState.toolbarVisible = false;
    questionState.answerVisible = false;
    modelQuestionStore = new ModelQuestionStore();
  }

  public ModelQuestionStore getModelQuestionStore(){
    return modelQuestionStore;
  }

  public boolean isAnswerBtnClicked() {
    return questionState.answerBtnClicked;
  }

  public void setAnswerBtnClicked(boolean clicked) {
    questionState.answerBtnClicked = clicked;
  }

  public boolean isAnswerVisible() {
    return questionState.answerVisible;
  }

  public boolean isToolbarVisible() {
    return questionState.toolbarVisible;
  }

  public void setAnswerVisibility(boolean visible) {
    questionState.answerVisible = visible;
  }

  public void goToCheatScreen(ViewQuestionActivity activity){
    cheatState = new CheatState();
    cheatState.toolbarVisible = false;
    cheatState.answerVisible = false;
    cheatState.answerBtnClicked = questionState.answerBtnClicked;

    activity.startActivity(new Intent(activity, ViewCheatActivity.class));
  }


  public void backToQuestionScreen(ViewCheatActivity activity){
    activity.finish();
  }



  private class QuestionState {
    boolean toolbarVisible;
    boolean answerVisible;
    boolean answerBtnClicked;

  }

  private class CheatState {
    boolean toolbarVisible;
    boolean answerVisible;
    boolean answerBtnClicked;
  }

}
