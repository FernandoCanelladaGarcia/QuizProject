package es.ulpgc.eite.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//VISTA
public class ViewQuestionActivity extends AppCompatActivity {


  private boolean toolbarVisible;
  private boolean answerVisible;
  private ModelQuestionStore modelquestionStore;
  private boolean answerBtnClicked;

  private Toolbar toolbarScreen;
  private Button buttonTrue, buttonFalse, buttonCheat, buttonNext;
  private TextView labelQuestion, labelAnswer;
  private QuestionPresenter presenter;
  //private QuizApp quizApp;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_question);


    labelQuestion = (TextView) findViewById(R.id.labelQuestion);
    labelAnswer = (TextView) findViewById(R.id.labelAnswer);
    toolbarScreen = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbarScreen);

    /**
     * Creacion de boton TRUE
     */

    buttonTrue = (Button) findViewById(R.id.buttonTrue);
    buttonTrue.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.onTrueBtnClicked();
      }
    });

    /**
     * Creacion de boton FALSE
     */
    buttonFalse = (Button) findViewById(R.id.buttonFalse);
    buttonFalse.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.onFalseBtnClicked();
      }
    });

    /**
     * Creacion de boton CHEAT
     */

    buttonCheat = (Button) findViewById(R.id.buttonCheat);
    buttonCheat.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.onCheatBtnClicked();
      }
    });

    /**
     * Creacion de boton NEXT
     */
    buttonNext = (Button) findViewById(R.id.buttonNext);
    buttonNext.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        presenter.onNextBtnClicked();
      }
    });

    //Creamos el presentador
    presenter = new QuestionPresenter(this);
    onScreenStarted();

  }

  /**
   * Iniciamos la pantalla
   */
  private void onScreenStarted() {
    //quizApp = (QuizApp) getApplication();
    modelquestionStore = new ModelQuestionStore();
    
    setButtonLabels();
    checkVisibility();
    showToolbar();
    presenter.setQuestion();
    //setQuestion(getQuestionStore().getCurrentQuestion());
    if(isAnswerBtnClicked()){
      //setAnswer(getQuestionStore().getCurrentAnswer());
      presenter.setAnswer();
    }
  }


  private void setButtonLabels(){

//    setTrueButton(getQuestionStore().getTrueLabel());
//    setFalseButton(getQuestionStore().getFalseLabel());
//    setCheatButton(getQuestionStore().getCheatLabel());
//    setNextButton(getQuestionStore().getNextLabel());
    presenter.getTrue();
    presenter.getFalse();
    presenter.getNext();
    presenter.getCheat();
  }

  private void checkVisibility(){
    checkToolbarVisibility();
    checkAnswerVisibility();
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

  private void hideAnswer() {
    labelAnswer.setVisibility(View.INVISIBLE);
  }

  private void showAnswer() {
    labelAnswer.setVisibility(View.VISIBLE);
  }

  private void hideToolbar() {
    toolbarScreen.setVisibility(View.GONE);
  }

  private void showToolbar(){
    toolbarScreen.setVisibility(View.VISIBLE);
  }

  //De privado a publico para poder trabajarlo con el presentador
  public void setAnswer(String text) {
    labelAnswer.setText(text);
    setAnswerVisibility(true);
    setAnswerBtnClicked(true);

    checkAnswerVisibility();
  }

  //De privado a publico para poder trabajarlo con el presentador
  public void setCheatButton(String label) {
    buttonCheat.setText(label);
  }
  //De privado a publico para poder trabajarlo con el presentador
  public void setFalseButton(String label) {
    buttonFalse.setText(label);
  }
  //De privado a publico para poder trabajarlo con el presentador
  public void setNextButton(String label) {
    buttonNext.setText(label);
  }

  //De privado a publico para poder trabajarlo con el presentador
  public void setQuestion(String text) {
    labelQuestion.setText(text);
  }
  //De privado a publico para poder trabajarlo con el presentador
  public void setTrueButton(String label) {
    buttonTrue.setText(label);
  }

  private void onCheatBtnClicked() {
    goToCheatScreen();
  }

  private void onFalseBtnClicked() {
    presenter.onFalseBtnClicked();
  }

  private void onNextBtnClicked(){
    presenter.onNextBtnClicked();
  }

  private void onTrueBtnClicked() {
    presenter.onTrueBtnClicked();
  }

//  private void onAnswerBtnClicked(boolean answer) {
//    getQuestionStore().setCurrentAnswer(answer);
//    setAnswer(getQuestionStore().getCurrentAnswer());
//    setAnswerVisibility(true);
//    setAnswerBtnClicked(true);
//
//    checkAnswerVisibility();
//  }

  private ModelQuestionStore getQuestionStore() {
    return modelquestionStore;
  }

  /*
  private ModelQuestionStore getModelQuestionStore() {
    return quizApp.getModelQuestionStore();
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

}
