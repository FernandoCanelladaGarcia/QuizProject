package es.ulpgc.eite.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends AppCompatActivity {


  private boolean toolbarVisible;
  private boolean answerVisible;
  private QuestionStore questionStore;
  private boolean answerBtnClicked;

  private Toolbar toolbarScreen;
  private Button buttonTrue, buttonFalse, buttonCheat, buttonNext;
  private TextView labelQuestion, labelAnswer;
  //private QuizApp quizApp;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_question);


    labelQuestion = (TextView) findViewById(R.id.labelQuestion);
    labelAnswer = (TextView) findViewById(R.id.labelAnswer);

    toolbarScreen = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbarScreen);

    buttonTrue = (Button) findViewById(R.id.buttonTrue);
    buttonTrue.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onTrueBtnClicked();
      }
    });
    buttonFalse = (Button) findViewById(R.id.buttonFalse);
    buttonFalse.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onFalseBtnClicked();
      }
    });
    buttonCheat = (Button) findViewById(R.id.buttonCheat);
    buttonCheat.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onCheatBtnClicked();
      }
    });
    buttonNext = (Button) findViewById(R.id.buttonNext);
    buttonNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onNextBtnClicked();
      }
    });

    onScreenStarted();

  }

  private void onScreenStarted() {
    //quizApp = (QuizApp) getApplication();
    questionStore = new QuestionStore();
    
    setButtonLabels();
    checkVisibility();

    setQuestion(getQuestionStore().getCurrentQuestion());
    if(isAnswerBtnClicked()){
      setAnswer(getQuestionStore().getCurrentAnswer());
    }
  }


  private void setButtonLabels(){
    setTrueButton(getQuestionStore().getTrueLabel());
    setFalseButton(getQuestionStore().getFalseLabel());
    setCheatButton(getQuestionStore().getCheatLabel());
    setNextButton(getQuestionStore().getNextLabel());
  }
  
  private void onCheatBtnClicked() {
    goToCheatScreen();
  }

  private void onFalseBtnClicked() {
    onAnswerBtnClicked(false);
  }

  private void onNextBtnClicked(){
    setQuestion(getQuestionStore().getNextQuestion());
  }

  private void onTrueBtnClicked() {
    onAnswerBtnClicked(true);
  }

  private void onAnswerBtnClicked(boolean answer) {
    getQuestionStore().setCurrentAnswer(answer);
    setAnswer(getQuestionStore().getCurrentAnswer());
    setAnswerVisibility(true);
    setAnswerBtnClicked(true);

    checkAnswerVisibility();
  }

  private QuestionStore getQuestionStore() {
    return questionStore;
  }

  private boolean isAnswerVisible() {
    return answerVisible;
  }

  private boolean isToolbarVisible() {
    return toolbarVisible;
  }

  private void setAnswerVisibility(boolean visible) {
    answerVisible = visible;
  }

  private boolean isAnswerBtnClicked() {
    return answerBtnClicked;
  }

  private void setAnswerBtnClicked(boolean clicked) {
    answerBtnClicked = clicked;
  }

  /*
  private boolean isAnswerBtnClicked() {
    return quizApp.isAnswerBtnClicked();
  }

  private void setAnswerBtnClicked(boolean clicked) {
    quizApp.setAnswerBtnClicked(clicked);
  }

  private QuestionStore getQuestionStore() {
    return quizApp.getQuestionStore();
  }

  private boolean isToolbarVisible() {
    return quizApp.isToolbarVisible();
  }

  private void setAnswerVisibility(boolean visible) {
    quizApp.setAnswerVisibility(visible);
  }

  private boolean isAnswerVisible() {
    return quizApp.isAnswerVisible();
  }
  */

  private void goToCheatScreen(){
    startActivity(new Intent(this, CheatActivity.class));
    //quizApp.goToCheatScreen(this);
  }

  private void checkAnswerVisibility(){
    if(!isAnswerVisible()) {
      hideAnswer();
    } else {
      showAnswer();
    }
  }

  private void checkToolbarVisibility(){
    if (!isToolbarVisible()) {
      hideToolbar();
    }
  }


  private void checkVisibility(){
    checkToolbarVisibility();
    checkAnswerVisibility();
  }


  private void hideAnswer() {
    labelAnswer.setVisibility(View.INVISIBLE);
  }

  private void hideToolbar() {
    toolbarScreen.setVisibility(View.GONE);
  }

  private void setAnswer(String text) {
    labelAnswer.setText(text);
  }

  private void setCheatButton(String label) {
    buttonCheat.setText(label);
  }

  private void setFalseButton(String label) {
    buttonFalse.setText(label);
  }

  private void setNextButton(String label) {
    buttonNext.setText(label);
  }

  private void setQuestion(String text) {
    labelQuestion.setText(text);
  }

  private void setTrueButton(String label) {
    buttonTrue.setText(label);
  }

  private void showAnswer() {
    labelAnswer.setVisibility(View.VISIBLE);
  }


}
