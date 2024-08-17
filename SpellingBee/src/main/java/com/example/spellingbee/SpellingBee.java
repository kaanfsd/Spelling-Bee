package com.example.spellingbee;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;


public class SpellingBee extends Application {
    public static int puan = 0;
    public static int score = 0;
    public static String wordPool = "VPSOZLUK2.txt";
    public static String playableGames = "playableLetters.txt";
    public static String pangramsShort = "PangramsWithoutRepetition.txt";
    public static String pangrams = "Pangrams.txt";
    @Override
    public void start(Stage stage) throws IOException {

        ArrayList<String> knownWords = new ArrayList<String>(); // ArrayList to store known words.

        Label enterLetters = new Label();
        enterLetters.setText("Generate your own game*");
        enterLetters.setFont(Font.font(15));
        enterLetters.setLayoutX(130);
        enterLetters.setLayoutY(370);

        TextField ownLetters = new TextField();
        ownLetters.setLayoutX(140);
        ownLetters.setLayoutY(400);
        AtomicReference<ArrayList<String>> randomGame = new AtomicReference<>(new ArrayList<>(getRandomGame()));

        Label note = new Label();
        note.setText("*You should enter 7 Turkish characters");
        note.setLayoutY(465);
        note.setLayoutX(80);

        Label note2 = new Label();
        note2.setText("*You should enter the center letter end of the letters ");
        note2.setLayoutY(480);
        note2.setLayoutX(80);

        Label newGameLetters = new Label();
        newGameLetters.textProperty().bind(ownLetters.textProperty());

        AtomicReference<StringBuffer> pangramLetters = new AtomicReference<>(new StringBuffer(randomGame.toString()));

        ArrayList<String> sixLetters = new ArrayList<String>();
        for(int i=0; i<6;i++){
            sixLetters.add(randomGame.get().get(i));
        }

        ListView<String> vBox = new ListView<>();
        vBox.setLayoutX(400);
        vBox.setLayoutY(50);
        vBox.setMaxHeight(600);

        TextField text = new TextField();
        text.setOnMouseClicked(mouseEvent ->{
            text.setEditable(true);
        } );
        text.setLayoutX(125);
        text.setLayoutY(40);
        text.setFont(Font.font(15));

        Button btnDelete = new Button("Delete");
        btnDelete.setLayoutX(120);
        btnDelete.setLayoutY(328);
        btnDelete.setFont(Font.font(15));
        btnDelete.setOnAction(actionEvent -> {
            if (text.getLength()==0){
            } else
                text.setText(text.getText().substring(0, text.getText().length()-1));
        });

        Label lblC = new Label();
        lblC.setText(randomGame.get().get(6).toUpperCase());
        lblC.setLayoutX(215);
        lblC.setLayoutY(185);
        lblC.setFont(Font.font(18));
        lblC.setOnMouseClicked(mouseEvent -> {text.appendText(lblC.getText().toLowerCase());});

        Polygon centerPolygon = new Polygon();
        centerPolygon.getPoints().addAll(new Double[]{
                200.0, 170.0, 240.0, 170.0, 260.0, 200.0, 240.0, 230.0, 200.0, 230.0, 180.0, 200.0,});
        centerPolygon.setFill(Color.rgb(225,225,0));
        centerPolygon.setOnMouseEntered(mouseEvent -> {centerPolygon.setFill(Color.rgb(206,170,251));});
        centerPolygon.setOnMouseExited(mouseEvent -> {centerPolygon.setFill(Color.rgb(225,225,0));});
        centerPolygon.setOnMouseClicked(mouseEvent -> {text.appendText(lblC.getText().toLowerCase());});

        Label lblH1 = new Label(randomGame.get().get(0).toUpperCase());
        lblH1.setOnMouseClicked(mouseEvent -> {text.appendText(lblH1.getText().toLowerCase());});
        lblH1.setLayoutX(215);
        lblH1.setLayoutY(115);
        lblH1.setFont(Font.font(18));

        Polygon h1 = new Polygon();
        h1.getPoints().addAll(new Double[]{
                200.0, 100.0, 240.0, 100.0, 260.0, 130.0, 240.0, 160.0, 200.0, 160.0, 180.0, 130.0,});
        h1.setFill(Color.rgb(185,185,185));
        h1.setOnMouseEntered(mouseEvent -> {h1.setFill(Color.rgb(206,170,251));});
        h1.setOnMouseExited(mouseEvent -> {h1.setFill(Color.rgb(185,185,185));});
        h1.setOnMouseClicked(mouseEvent -> {text.appendText(lblH1.getText().toLowerCase());});

        Label lblH2 = new Label(randomGame.get().get(1).toUpperCase());
        lblH2.setLayoutX(285);
        lblH2.setLayoutY(150);
        lblH2.setFont(Font.font(18));
        lblH2.setOnMouseClicked(mouseEvent -> {text.appendText(lblH2.getText().toLowerCase());});

        Polygon h2 = new Polygon();
        h2.getPoints().addAll(new Double[]{
                270.0, 135.0, 310.0, 135.0, 330.0, 165.0, 310.0, 195.0, 270.0, 195.0, 250.0, 165.0});
        h2.setFill(Color.rgb(185,185,185));
        h2.setOnMouseEntered(mouseEvent -> {h2.setFill(Color.rgb(206,170,251));});
        h2.setOnMouseExited(mouseEvent -> {h2.setFill(Color.rgb(185,185,185));});
        h2.setOnMouseClicked(mouseEvent -> {text.appendText(lblH2.getText().toLowerCase());});

        Label lblH3 = new Label(randomGame.get().get(2).toUpperCase());
        lblH3.setLayoutX(285);
        lblH3.setLayoutY(220);
        lblH3.setFont(Font.font(18));
        lblH3.setOnMouseClicked(mouseEvent -> {text.appendText(lblH3.getText().toLowerCase());});

        Polygon h3 = new Polygon();
        h3.getPoints().addAll(new Double[]{
                270.0, 205.0, 310.0, 205.0, 330.0, 235.0, 310.0, 265.0, 270.0, 265.0, 250.0, 235.0});
        h3.setFill(Color.rgb(185,185,185));
        h3.setOnMouseEntered(mouseEvent -> {h3.setFill(Color.rgb(206,170,251));});
        h3.setOnMouseExited(mouseEvent -> {h3.setFill(Color.rgb(185,185,185));});
        h3.setOnMouseClicked(mouseEvent -> {text.appendText(lblH3.getText().toLowerCase());});

        Label lblH4 = new Label(randomGame.get().get(3).toUpperCase());
        lblH4.setLayoutX(215);
        lblH4.setLayoutY(255);
        lblH4.setFont(Font.font(18));
        lblH4.setOnMouseClicked(mouseEvent -> {text.appendText(lblH4.getText().toLowerCase());});

        Polygon h4 = new Polygon();
        h4.getPoints().addAll(new Double[]{
                200.0, 240.0, 240.0, 240.0, 260.0, 270.0, 240.0, 300.0, 200.0, 300.0, 180.0, 270.0,});
        h4.setFill(Color.rgb(185,185,185));
        h4.setOnMouseEntered(mouseEvent -> {h4.setFill(Color.rgb(206,170,251));});
        h4.setOnMouseExited(mouseEvent -> {h4.setFill(Color.rgb(185,185,185));});
        h4.setOnMouseClicked(mouseEvent -> {text.appendText(lblH4.getText().toLowerCase());});

        Label lblH5 = new Label(randomGame.get().get(4).toUpperCase());
        lblH5.setLayoutX(145);
        lblH5.setLayoutY(220);
        lblH5.setFont(Font.font(18));
        lblH5.setOnMouseClicked(mouseEvent -> {text.appendText(lblH5.getText().toLowerCase());});

        Polygon h5 = new Polygon();
        h5.getPoints().addAll(new Double[]{
                130.0, 205.0, 170.0, 205.0, 190.0, 235.0, 170.0, 265.0, 130.0, 265.0, 110.0, 235.0
        });
        h5.setFill(Color.rgb(185,185,185));
        h5.setOnMouseEntered(mouseEvent -> {h5.setFill(Color.rgb(206,170,251));});
        h5.setOnMouseExited(mouseEvent -> {h5.setFill(Color.rgb(185,185,185));});
        h5.setOnMouseClicked(mouseEvent -> {text.appendText(lblH5.getText().toLowerCase());});


        Label lblH6 = new Label(randomGame.get().get(5).toUpperCase());
        lblH6.setLayoutX(145);
        lblH6.setLayoutY(150);
        lblH6.setFont(Font.font(18));
        lblH6.setOnMouseClicked(mouseEvent -> {
            text.appendText(lblH6.getText().toLowerCase());
        });

        Polygon h6 = new Polygon();
        h6.getPoints().addAll(new Double[]{
                130.0, 135.0, 170.0, 135.0, 190.0, 165.0, 170.0, 195.0, 130.0, 195.0, 110.0, 165.0
        });
        h6.setFill(Color.rgb(185,185,185));
        h6.setOnMouseEntered(mouseEvent -> {h6.setFill(Color.rgb(206,170,251));});
        h6.setOnMouseExited(mouseEvent -> {h6.setFill(Color.rgb(185,185,185));});
        h6.setOnMouseClicked(mouseEvent -> {text.appendText(lblH6.getText().toLowerCase());});

        Label Score = new Label();
        Score.setText("Score : 0" );
        Score.setLayoutX(400);
        Score.setLayoutY(30);

        Alert alert = new Alert(Alert.AlertType.NONE);

        Button buttonNewGame = new Button("New game");
        buttonNewGame.setLayoutX(35);
        buttonNewGame.setLayoutY(42);
        buttonNewGame.setOnAction(actionEvent -> {
            Score.setText("Score : 0");
            score=0; puan =0;
            randomGame.set(new ArrayList<>(getRandomGame()));
            pangramLetters.set(new StringBuffer(randomGame.toString()));
            for (int i =knownWords.size()-1;i<=knownWords.size();i--){
                if(knownWords.size()==0){
                }else
                    knownWords.remove(i);
            }
            ObservableList<String> items = FXCollections.observableArrayList (knownWords);
            vBox.setItems(items);
            String shuffleFor = sixLetters.toString();
            sixLetters.clear();
            for(int i=0; i<6;i++){
                sixLetters.add(randomGame.get().get(i));
            }

            lblC.setText(randomGame.get().get(6).toUpperCase());
            lblH1.setText(randomGame.get().get(1).toUpperCase());
            lblH2.setText(randomGame.get().get(2).toUpperCase());
            lblH3.setText(randomGame.get().get(3).toUpperCase());
            lblH4.setText(randomGame.get().get(4).toUpperCase());
            lblH5.setText(randomGame.get().get(5).toUpperCase());
            lblH6.setText(randomGame.get().get(0).toUpperCase());
        });

        Button generate = new Button();
        generate.setText("Generate");
        generate.setOnAction(actionEvent -> {
            if ((ownLetters.getText().toLowerCase().length()==7) && regexValid(ownLetters.getText().toLowerCase())){
                Score.setText("Score : 0");
                score=0; puan =0;
                for (int i=0;i<7;i++){
                    randomGame.get().remove(i);
                    randomGame.get().add(i,newGameLetters.textProperty().get().substring(i,i+1));
                }
                for (int j=0;j<6;j++){
                    sixLetters.remove(j);
                    sixLetters.add(j,newGameLetters.textProperty().get().substring(j,j+1));
                }
                for (int i =knownWords.size()-1;i<=knownWords.size();i--){
                    if(knownWords.size()==0){
                    }else
                        knownWords.remove(i);
                }
                ObservableList<String> items = FXCollections.observableArrayList (knownWords);
                vBox.setItems(items);
                pangramLetters.get().delete(0, pangramLetters.get().length());

                for (int i=0;i<7;i++)
                    pangramLetters.get().insert(i, randomGame.get().get(i));

                lblC.setText(randomGame.get().get(6).toUpperCase());
                lblH1.setText(randomGame.get().get(1).toUpperCase());
                lblH2.setText(randomGame.get().get(2).toUpperCase());
                lblH3.setText(randomGame.get().get(3).toUpperCase());
                lblH4.setText(randomGame.get().get(4).toUpperCase());
                lblH5.setText(randomGame.get().get(5).toUpperCase());
                lblH6.setText(randomGame.get().get(0).toUpperCase());
            }else{
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Invalid letters");
                alert.show();}

        });

        Button btnK = new Button("Shuffle");
        btnK.setLayoutX(190);
        btnK.setLayoutY(328);
        btnK.setFont(Font.font(15));
        btnK.setOnAction(actionEvent -> {
            Collections.shuffle(sixLetters);
            lblH1.setText(sixLetters.get(0).toUpperCase());
            lblH2.setText(sixLetters.get(1).toUpperCase());
            lblH3.setText(sixLetters.get(2).toUpperCase());
            lblH4.setText(sixLetters.get(3).toUpperCase());
            lblH5.setText(sixLetters.get(4).toUpperCase());
            lblH6.setText(sixLetters.get(5).toUpperCase());
        });

        Button btnEnter = new Button("Enter");
        btnEnter.setLayoutX(265);
        btnEnter.setLayoutY(328);
        btnEnter.setFont(Font.font(15));
        btnEnter.setOnAction(actionEvent -> {
            int score = 0;
            if (text.getText().toLowerCase().contains(randomGame.get().get(6))){
                if (text.getText().toLowerCase().length()>=4){
                    if (meaningfulWordsFromPangram(pangramLetters.toString()).contains(text.getText().toLowerCase())){
                        if (!(knownWords.contains(text.getText().toLowerCase())))
                        {   ArrayList<String> pangrams = new ArrayList<String>(findPangrams(wordPool));
                            String word = text.getText().toLowerCase();
                            if (word.length()==4)
                                score +=1;
                            else if(pangrams.contains(word)){
                                score += 14;
                                alert.setAlertType(Alert.AlertType.INFORMATION);
                                alert.setContentText("You find the PANGRAM word");
                                alert.show();}
                            else if (word.length()>4)
                                score +=word.length();
                            knownWords.add(text.getText().toLowerCase());
                            ObservableList<String> items = FXCollections.observableArrayList (knownWords);
                            vBox.setItems(items); puan += score;
                            Score.setText("Score : " + puan);
                            text.clear();
                        }else {
                            alert.setAlertType(Alert.AlertType.INFORMATION);
                            alert.setContentText("This word already find");
                            alert.show();
                        }
                    }else{
                        alert.setAlertType(Alert.AlertType.INFORMATION);
                        alert.setContentText("This word is invalid");
                        alert.show();
                    }
                }else{
                    alert.setAlertType(Alert.AlertType.INFORMATION);
                    alert.setContentText("Word should be longer than 4 ");
                    alert.show();
                }

            }else {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Word doesn't contain center letter");
                alert.show();
            }
        });
        generate.setLayoutX(180);
        generate.setLayoutY(435);

        Pane root = new Pane();
        BorderPane BP = new BorderPane();
        BP.getChildren().addAll(centerPolygon,h1,h2,h3,h4,h5,h6);
        root.getChildren().addAll(note,note2,generate,ownLetters,enterLetters,Score,BP,lblC, btnDelete,
                btnK,btnEnter, vBox, lblH1, lblH2, lblH3,lblH4, lblH5,lblH6,text,buttonNewGame);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Spellling Bee");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        //System.out.println(txtToArrayList(wordPool)); //VPSOZLUK TXTYİ ARRAYLİSTE ATIYOR
        //System.out.println(wordListToPangram(wordPool)); // OLASI PANGRAMLARI BULUYOR
        //writeArrayListToTxt(pangramsShort,findPangramsLetters(wordPool)); //PANGRAMLARI TXTYE ATIYOR
        //writeArrayListToTxt(pangrams,findPangrams(wordPool));
        //System.out.println(meaningfulWordsFromPangram("aborjin")); //Bunu arayüzle uyumlu kullanıcaz
        //System.out.println(findPangrams(wordPool));
        //writePlayableGamesToTxt(); // Otomatik oynanabilir oyunları oluşturuyor
        //System.out.println(getRandomGame());
        launch();
    }

    public static boolean regexValid(String a) {
        String kelime = a;
        boolean bayrak = false;

        for (int i = 0; i < kelime.length(); i++) {
            int count = 0;
            for (int j = 0; j < kelime.length(); j++) {
                if(kelime.charAt(i) == kelime.charAt(j)){
                    if(i == j){
                    }
                    else{
                        count++;
                    }
                }
            }
            if(count != 0){bayrak = false; break;}
            else bayrak = true; //
        }
        if (bayrak) {
            if ((Pattern.matches("[ertyuıopğüasdfghjklşizcvbnmöç]{7}", a))) {
                bayrak = true;
            } else {
                bayrak = false;
            }
        }
        return bayrak;
    }

    public static ArrayList<String> getRandomGame(){
        ArrayList<String> a = new ArrayList<String>();
        String playableGame = getRandomGameFromPlayableLetters(wordPool);
         String[] ariKovani = new String[8];
        for (int i=0;i<7;i++){
            Character b = playableGame.charAt(i);
            String c = String.valueOf(b);
            a.add(c);
        }
        return a;
    }

    public static String getRandomGameFromPlayableLetters(String fileName) {
        return txtToArrayList(playableGames).get(getRandomInteger(
                txtToArrayList(playableGames).size(),0));
    }

    public static int getRandomInteger(int  maximum, int minimum){
        return ((int) (Math.random()*(maximum - minimum))) + minimum;

    }

    public static void writePlayableGamesToTxt(){
        int score =0;
        int count = 0;
        ArrayList<String> pangrams = new ArrayList<String>(findPangrams(wordPool));
        List<String> playablePangram = new ArrayList<>();
        for (String pangram : findPangramsLetters(wordPool)) {
            for (int i = 0; i < pangram.length(); i++) {
                score=0;
                count =0;
                for (String word : meaningfulWordsFromPangram(pangram)) { //SORUN BURDA
                    Character a = pangram.charAt(i);
                    String s = String.valueOf(a);
                    if (word.contains(s)) {
                        int iStringSearch = Collections.binarySearch(pangrams, word);
                        count++;
                        if (word.length()==4)
                            score +=1;
                        else if(iStringSearch>=0)
                            score +=14;
                        else if (word.length()>4)
                            score +=word.length();
                    }
                }
                if ((count >= 20 && count<=80) && (score>=100 && score<=400)){
                    String a = String.valueOf(pangram.charAt(i));
                    String pangram_ = pangram.replace(a,"");
                    playablePangram.add(pangram_ + "" + pangram.charAt(i));
                }
            }
        }
        writeArrayListToTxt(playableGames,playablePangram);
    }

    public static ArrayList<String> meaningfulWordsFromPangram(String pangramsLetters){
        String metin=pangramsLetters;
        ArrayList<String> liste = new ArrayList<String>();
        for (String word:txtToArrayList(wordPool)){
            boolean isValid = true;
            for (int j=0; j<word.length(); j++){
                Character a = word.charAt(j);
                String s = String.valueOf(a);
                if(metin.contains(s) && word.length()>=4){
                }else{isValid = false; }
            }
            if(isValid){liste.add(word);}
        }
        return liste;
    }

    public static void writeArrayListToTxt(String filePathName, List<String> arrayList){
        Path pathName = Paths.get(filePathName);
        try {
            Files.write(pathName,arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String> findPangramsLetters(String fileName){
        ArrayList<String> pangrams = new ArrayList<String>();
        for (String word:txtToArrayList(fileName)){
            String filteredWord = word.toLowerCase();
            if(removeDuplicate(filteredWord.toCharArray(), word.length()).length()==7) {
                pangrams.add(removeDuplicate(filteredWord.toCharArray(), word.length()));
            }
        }
        return pangrams;
    }
    public static List<String> findPangrams(String fileName){
        ArrayList<String> pangrams = new ArrayList<String>();
        for (String word:txtToArrayList(fileName)){
            String filteredWord = word.toLowerCase();
            if(removeDuplicate(filteredWord.toCharArray(), word.length()).length()==7) {
                pangrams.add(filteredWord);
            }
        }
        return pangrams;
    }


    public static ArrayList<String> wordListToPangram(String fileName){
        String wordList = fileName;
        ArrayList<String> pangrams = new ArrayList<String>();
        for (String part:txtToArrayList(wordList)){
            String filteredWord = part.toLowerCase();
            if(removeDuplicate(filteredWord.toCharArray(), part.length()).length()==7) {
                pangrams.add(removeDuplicate(filteredWord.toCharArray(), part.length()));
            }
        }
        return pangrams;
    }

    public static String removeDuplicate(char str[], int length)
    {
        int index = 0;
        for (int i = 0; i < length; i++)
        {
            int j;
            for (j = 0; j <= i; j++)
            {
                if (str[i] == str[j])
                {
                    break;
                }
            }
            if (j == i)
            {
                str[index++] = str[i];
            }
        }
        return String.valueOf(Arrays.copyOf(str, index));
    }

    public static List<String> txtToArrayList(String fileName) {
        ArrayList<String> wordList = new ArrayList<>();
        String[] parts = readFileAsString(fileName).split("\r\n");
        for (String word : parts) {
            wordList.add(word);
        }
        return wordList;
    }

    public static String readFileAsString(String fileName) {
        String path = fileName;
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }
}
