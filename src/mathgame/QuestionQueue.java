/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mathgame;
import mathgame.Questions.Question;
import java.util.*;
/**
 *
 * import mathgame.Questions.Question;
@author devinbost
 */
public class QuestionQueue extends AbstractQueue<Question>{
    Queue _questions = null;
    QuestionQueue(){
        _questions = new LinkedList();
    }
    @Override
    public Iterator<Question> iterator() {
        Iterator iter = _questions.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return null;
    }

    @Override
    public int size() {
        System.out.println(_questions.size());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return _questions.size();
    }

    @Override
    public boolean offer(Question e) {
        _questions.offer(e);
        return _questions.offer(e);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Question poll() {
        Question nextQuestion = (Question)_questions.poll();
        System.out.println(nextQuestion);
        return nextQuestion;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Question peek() {
        Question nextQuestion = (Question)_questions.peek();
        System.out.println(nextQuestion);
        return nextQuestion;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
