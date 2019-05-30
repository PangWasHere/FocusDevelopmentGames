package com.pangchavez.focusnumber;

import java.util.Random;

public class TwoNumberGame {

    final String BIGGER_MODE = "BIGGER";
    final String SMALLER_MODE = "SMALLER";
    final int chancesGiven = 3;
    int _level;
    String _mode;
    int[] numbers;
    int correctAnswer;
    long score;
    int chances;
    int consecutiveCorrectAnswer;
    boolean wrongAnswer;

    long correctNumber;

    public TwoNumberGame()
    {
        _level = 1;
        _mode = BIGGER_MODE;
        numbers = new int[2];
        score = 0;
        chances = chancesGiven;
        consecutiveCorrectAnswer = 0;
        wrongAnswer = false;
    }

    public int getChancesRemaining(){ return chances; }

    public long getScore(){ return score; }

    public int getFirstNumber()
    {
        return numbers[0];
    }

    public int getSecondNumber()
    {
        return numbers[1];
    }

    public boolean checkAnswer(int choice)
    {
        boolean isCorrect = false;
        if(choice == correctAnswer) {
            if(!wrongAnswer)
            {
                score += 10 * _level;
                consecutiveCorrectAnswer++;
            }
            isCorrect = true;
        }
        else {
            chances--;
            consecutiveCorrectAnswer = 0;
            wrongAnswer = true;
        }

        return isCorrect;
    }

    private void createNewNumbers(int start, int end)
    {

        Random randomNumberGenerator = new Random();
        numbers[0] = randomNumberGenerator.nextInt(end);
        do{
            numbers[1] = randomNumberGenerator.nextInt(end) + start;
        } while(numbers[0] == numbers[1]);
    }

    public void createNewGame()
    {
        int [] range;
        int rangeStart;
        wrongAnswer = false;

        evaluateLevel();

        switch(_level)
        {
            case 1:
                // One digit numbers
                createNewNumbers(1, 10);
                break;
            case 2:
                // Two digit numbers from 11 - 50
                createNewNumbers(11, 50);
                break;
            case 3:
                // Two digit numbers grouped by tens (11 - 19, 21 - 29, 31 - 39, ...)
                range = createLevelRanges(10, 10, 10);
                rangeStart = giveRandomNumberFromArray(range);
                createNewNumbers(rangeStart, rangeStart + 9);
                break;
            case 4:
                // Three digit numbers from 101 - 999
                createNewNumbers(101, 999);
                break;
            case 5:
                // Three digit numbers grouped by hundreds (100 - 199, 200 - 299, ...)
                range = createLevelRanges(100, 10, 100);
                rangeStart = giveRandomNumberFromArray(range);
                createNewNumbers(rangeStart, rangeStart + 9);
                break;
            case 6:
                // Three digit numbers grouped by tens (101 - 109, 110 - 119, 120 - 129, ...)
                range = createLevelRanges(100, 100, 10);
                rangeStart = giveRandomNumberFromArray(range);
                createNewNumbers(rangeStart, rangeStart + 9);
                break;
        }

        if(_mode == BIGGER_MODE)
        {
            if(numbers[0] > numbers[1])
            {
                correctAnswer = numbers[0];
            } else {
                correctAnswer = numbers[1];
            }
        } else // _mode == SMALLER_MODE
        {
            if(numbers[0] < numbers[1])
            {
                correctAnswer = numbers[0];
            } else {
                correctAnswer = numbers[1];
            }
        }
    }

    private void evaluateLevel()
    {
        if(consecutiveCorrectAnswer > (3 + _level) && _level < 7)
        {
            // Level up
            _level++;
            consecutiveCorrectAnswer = 0;
        }
    }

    private int giveRandomNumberFromArray(int [] range)
    {
        Random randomNumberGenerator = new Random();
        int index = randomNumberGenerator.nextInt(range.length);

        return range[index];
    }

    private int [] createLevelRanges(int start, int size, int difference)
    {
        int [] level = new int[size];
        for(int i = 0; i < size; i++)
        {
            level[i] = start;
            start += difference;
        }

        return  level;
    }
}
