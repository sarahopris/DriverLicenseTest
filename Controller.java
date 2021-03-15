package ubb;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller{
    List<Integer> random; //list of id-s of questions (26 numbers between 1 and 40)
    FragenFileRepository fragen;
    Integer correctAnswers;
    Integer falseAnswers;
    int currentQuestion;

    public Controller(FragenFileRepository fragen) {
        this.fragen = fragen;
        int addedNumber = 0;
        random = new ArrayList<>();
        currentQuestion = 0;
        correctAnswers = 0;
        falseAnswers = 0;
        while (addedNumber < 26)
        {
            Random rand = new Random();
            Integer current = rand.nextInt(fragen.getFragen().getFragenRepo().size() + 1);
            if (!random.contains(current) && !current.equals(0))
            {
                random.add(current);
                addedNumber++;
            }
        }
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public Integer getFalseAnswers() {
        return falseAnswers;
    }

    public void generateNewRandom()
    {
        int addedNumber = 0;
        while (addedNumber < 26)
        {
            Random rand = new Random();
            Integer current = rand.nextInt(fragen.getFragen().getFragenRepo().size());
            if (!random.contains(current))
            {
                random.add(current);
                addedNumber++;
            }
        }
        currentQuestion = 0;
    }

    public boolean answer(String answer)
    {
        //do the finalise
        if (random.isEmpty())
            return false;
        Fragen current = fragen.get(random.get(currentQuestion));
        if(current.richtigeAntw.equals(answer))
        {
            correctAnswers++;
            random.remove(currentQuestion);
            nextQuestion();
            return true;
        }
        falseAnswers++;
        random.remove(currentQuestion);
        nextQuestion();
        return false;
    }

    public boolean finish()
    {
        return random.isEmpty();
    }

    public void nextQuestion()
    {
        if (currentQuestion >= random.size() - 1)
            currentQuestion = 0;
        else
            currentQuestion++;
    }

    public Fragen getQuestion()
    {
        return fragen.get(random.get(currentQuestion));
    }

    public Integer size(){return random.size();}
}
