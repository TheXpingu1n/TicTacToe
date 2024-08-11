package com.example.tictactoeapplication;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TicTacToeApplication extends Application{
    private boolean isX;
    private GameLogic logic;
    public void initButtons(Button [] button)
    {
        for(int i =0; i<9; i++)
        {
            button[i] = new Button(" ");
            button[i].setFont(Font.font("Monospaced", 40));
        }
    }
    public GridPane distributeButtons(Button[] buttons)
    {
        GridPane pane = new GridPane();
        int counter = 0;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                GridPane.setConstraints(buttons[counter], i, j);
                counter++;
            }

        }
        pane.getChildren().addAll(buttons);
        return pane;
    }
    public String turnofwho()
    {
        if(this.isX)
            return "X";
        else
            return "O";

    }
    public void change()
    {
        if(this.isX)
            this.isX = false;
        else
            this.isX = true;
    }
    public void play(Button[] buttons, int index, Stage window,Label label, Label endofgame, Scene whowins)
    {
        if(logic.getGrid(index) == 0)
        {
            buttons[index].setText(turnofwho());
            logic.setGrid(index, logic.XorO(turnofwho()));
            int win = logic.tracker();
            if(logic.isDraw())
            {
                endofgame.setText("Draw :(");
                window.setScene(whowins);
            }
            if(win == 1)
            {
                endofgame.setText("Player X Wins!!");
                window.setScene(whowins);
                return;
            }
            if (win == 2)
            {
                endofgame.setText("Player O Wins!!");
                window.setScene(whowins);
                return;
            }
            change();
            label.setText("Turn: " + turnofwho());
        }
        else
            return;
    }
    @Override
    public void start(Stage window) throws Exception {
        BorderPane pane = new BorderPane();
        logic = new GameLogic();
        isX = true;
        Label endofgame = new Label();
        StackPane endmsg = new StackPane();
        Button[] buttons = new Button[9];
        Label label = new Label("Turn:" + turnofwho());
        label.setFont(Font.font("Arial", 52));
        endofgame.setFont(Font.font("Arial", 36));
        endmsg.getChildren().add(endofgame);
        endmsg.setAlignment(Pos.CENTER);
        endmsg.setPrefSize(300, 150);
        endmsg.setPadding(new Insets(10,10,10,10));
        pane.setTop(label);
        initButtons(buttons);
        GridPane grid = distributeButtons(buttons);
        grid.setPrefSize(300,300);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10,10,10,10));
        pane.setCenter(grid);
        Scene whowins = new Scene(endmsg);
        for (int i = 0; i < 9; i++)
        {
            int finalI = i;
            buttons[i].setOnAction((event) ->
            {
                play(buttons, finalI, window, label, endofgame, whowins);
                logic.printAtConsole();
                System.out.println();
            });
        }

        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.show();
    }
    public static void main(String[] args)
    {
        launch(TicTacToeApplication.class);
    }
}
