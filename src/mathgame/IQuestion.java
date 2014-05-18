/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame;

/**
 *
 * @author devinbost
 */
public interface IQuestion {
    void SetOperator(QuestionTypeEnum questionType);
    void SetQuestionValues(int digits);
    @Override String toString();
    double ComputeAnswer();
    boolean CheckAnswer(double answer);
    double GetAnswer();
}
