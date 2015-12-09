
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Score implements Serializable{
    private int score;
    private String name;
    private ArrayList<Score> scores = new ArrayList<Score>();

    Comparator<Score> scoresCompare = new Comparator<Score>() {
        @Override
        public int compare(Score one, Score two){
            if (one.getScore() < two.getScore())
                return 1;
            else
                return -1;
        }
    };
    public Score(int aScore, String aName){
        score = aScore;
        name = aName;
    }

    public void add(Score s){
        scores.add(s);
    }

    public void sortList(){
        Collections.sort(scores, scoresCompare);
    }

    public int getScore(){
        return score;
    }

    public String getName() {
        return name;
    }

    public void write() {
        try {
            FileWriter fw = new FileWriter("HighscoreX.txt");
            BufferedWriter output = new BufferedWriter(fw);
            int sz = scores.size();
            for (int i = 0; i < sz; i++){
                output.write(scores.get(i).toString());
                output.newLine();
            }
            output.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void reset() {
        scores = new ArrayList<Score>();
    }

    public void read() {
        try {
            Scanner in = new Scanner(new File("HighscoreX.txt"));
            while(in.hasNextLine()) {
                while(in.hasNext()) {
                    //System.out.println("Got this far");
                    String aName = in.next();
                    int aScore = in.nextInt();
                    scores.add(new Score(aScore, aName));
                    in.nextLine();
                }
            }
        }
        catch (FileNotFoundException e){
            //System.out.println("You dun goofed.");
        }
    }

    public String toString() {
        return getName() + "\t" + "\t" + getScore();
    }

    public void newArrayList() {
        scores = new ArrayList<Score>();
    }

    public ArrayList<Score> getScores(){
        return scores;
    }

    public void saveScore(Score s) throws ClassNotFoundException, IOException, EOFException, FileNotFoundException{
        read();
        scores.add(s);
        Collections.sort(scores, scoresCompare);
        write();
    }
}
