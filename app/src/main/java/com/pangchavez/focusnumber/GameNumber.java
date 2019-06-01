package com.pangchavez.focusnumber;

import android.util.Log;

import java.util.Arrays;
import java.util.Random;

// TODO: Create level where the game mode changes randomly
// TODO: Make an efficient way to randomize numbers with big numbers
    // For numbers with less than 100 items as range, create an array instead and randomize the index.

public class GameNumber {
    // Numbers will be randomly generated from 0 to 65,535
    // When the limit has been reached, the levels will reset

    // These strings are for the view
    final String BIGGER_MODE = "BIGGER";
    final String BIGGEST_MODE = "BIGGEST";
    final String SMALLER_MODE = "SMALLER";
    final String SMALLEST_MODE = "SMALLEST";

    // These strings are to check the mode of the game
    public static final String BIG_MODE = "BIG";
    public static final String SMALL_MODE = "SMALL";

    final int chancesGiven = 5;
    int numberOfChoices;
    int _level;
    String _mode;
    int[] numbers;
    int[] sortedNumbers;
    int correctAnswer;
    long score;
    int chances;
    int consecutiveCorrectAnswer;
    boolean wrongAnswer;

    public GameNumber(int modeByNumber)
    {
        _level = 1;
        _mode = BIG_MODE;
        numbers = new int[modeByNumber];
        score = 0;
        chances = chancesGiven;
        consecutiveCorrectAnswer = 0;
        wrongAnswer = false;
        numberOfChoices = modeByNumber;
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

    public int getNumberWithPosition(int position)
    {
        return numbers[position - 1];
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
            case 7:
                // Changing mode
                RandomMode();
                createNewNumbers(101, 999);
                break;
            case 8:
                // Changing mode
                RandomMode();
                range = createLevelRanges(10, 10, 10);
                rangeStart = giveRandomNumberFromArray(range);
                createNewNumbers(rangeStart, rangeStart + 9);
                break;
            case 9:
                RandomMode();
                // Three digit numbers grouped by hundreds (100 - 199, 200 - 299, ...)
                range = createLevelRanges(100, 10, 100);
                rangeStart = giveRandomNumberFromArray(range);
                createNewNumbers(rangeStart, rangeStart + 9);
                break;
            case 10:
                RandomMode();
                // Three digit numbers grouped by tens (101 - 109, 110 - 119, 120 - 129, ...)
                range = createLevelRanges(100, 100, 10);
                rangeStart = giveRandomNumberFromArray(range);
                createNewNumbers(rangeStart, rangeStart + 9);
                break;
            case 11:
                // Four digit numbers grouped by thousands (1000 - 1999, 2000 - 2999, ...)
                range = createLevelRanges(1000, 10, 1000);
                rangeStart = giveRandomNumberFromArray(range);
                createNewNumbers(rangeStart, rangeStart + 9);
                break;
            case 12:
                // Four digit numbers grouped by hundreds (1000 - 1100, 1101 - 1200, 1201 - 1300, ...)
                range = createLevelRanges(1000, 100, 100);
                rangeStart = giveRandomNumberFromArray(range);
                createNewNumbers(rangeStart, rangeStart + 9);
                break;
            case 13:
                // Four digit numbers grouped by tens (1000 - 1010, 1011 - 1020, 1201 - 1300, ...)
                // 1000 - 5000
                range = createLevelRanges(1000, 500, 10);
                rangeStart = giveRandomNumberFromArray(range);
                createNewNumbers(rangeStart, rangeStart + 9);
                break;
            case 14:
                // Four digit numbers grouped by tens (1000 - 1010, 1011 - 1020, 1201 - 1300, ...)
                // 5000 - 10000
                range = createLevelRanges(5000, 500, 10);
                rangeStart = giveRandomNumberFromArray(range);
                createNewNumbers(rangeStart, rangeStart + 9);
                break;
        }

        // Sort the numbers as basis for correct answer
        sortedNumbers = numbers.clone();
        Arrays.sort(sortedNumbers);

        // Check the mode
        if(_mode == BIG_MODE)
        {
            correctAnswer = sortedNumbers[numberOfChoices - 1];
        } else // _mode == SMALL_MODE
        {
            correctAnswer = sortedNumbers[0];
        }
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
        for(int i = 0; i < numberOfChoices; i++)
        {
            numbers[i] = nextUniqueRandomNumber(start, end, i);
        }
    }

    private int nextUniqueRandomNumber(int start, int end, int currentSize)
    {
        int uniqueNumber;
        Random randomNumberGenerator = new Random();

        do {
            uniqueNumber = randomNumberGenerator.nextInt(end) + start;
        } while( uniqueNumber > end || contains(uniqueNumber, currentSize));

        return uniqueNumber;
    }

    private boolean contains(int number, int limit)
    {
        int counter = 0;
        boolean numberExists = false;

        while( counter <  limit && numbers[counter] != number)
        {
            ++counter;
        }

        if(counter < limit)
        {
            numberExists = true;
        }

        return numberExists;
    }

    private void evaluateLevel()
    {
        if(consecutiveCorrectAnswer > 3 && _level < 15)
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

    private void RandomMode()
    {
        Random randomNumberGenerator = new Random();
        int determiner = randomNumberGenerator.nextInt(3);

        if(determiner % 2 == 0)
        {
            _mode = BIG_MODE;
        } else {
            _mode = SMALL_MODE;
        }
    }

}
