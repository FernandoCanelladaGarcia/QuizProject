package es.ulpgc.eite.android.quiz;

/**
 * Created by Fernando on 12/02/2017.
 */

public class QuestionPresenter {
    private ModelQuestionStore model;
    private ViewQuestionActivity qView;

    public QuestionPresenter(ViewQuestionActivity view){
        this.model = new ModelQuestionStore();
        qView = view;
    }

    public void onTrueBtnClicked(){
        model.onAnswerBtnClicked(true);
        qView.setAnswer(model.getCurrentAnswer());
    }

    public void onFalseBtnClicked(){
        model.onAnswerBtnClicked(false);
        qView.setAnswer(model.getCurrentAnswer());
    }
    public void onNextBtnClicked(){
        qView.setQuestion(model.getNextQuestion());
        qView.setAnswer("");
    }

    public void onCheatBtnClicked(){

    }

    public void getTrue(){
        qView.setTrueButton(model.getTrueLabel());
    }
    public void getFalse(){
        qView.setFalseButton(model.getFalseLabel());
    }
    public void getCheat(){
        qView.setCheatButton(model.getCheatLabel());
    }
    public void getNext(){
        qView.setNextButton(model.getNextLabel());
    }
    public void setQuestion(){
        qView.setQuestion(model.getCurrentQuestion());
    }
    public void setAnswer(){
        qView.setAnswer(model.getCurrentAnswer());
    }
}
