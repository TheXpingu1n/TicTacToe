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
    @Override
    public void start(Stage window) throws Exception {
        BorderPane pane = new BorderPane();
        logic = new GameLogic();
        Button[] buttons = new Button[9];
        Label label = new Label("Turn: ");
        label.setFont(Font.font("Arial", 52));
        pane.setTop(label);
        initButtons(buttons);
        GridPane grid = distributeButtons(buttons);
        grid.setPrefSize(300,300);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10,10,10,10));
        pane.setCenter(grid);
        buttons[0].setOnAction((event) -> {
            if(logic.getGrid(0) == 0) {
                buttons[0].setText(turnofwho());
                logic.setGrid(0, logic.XorO(turnofwho()));
                change();
                label.setText("Turn: " + turnofwho());
            }
            else
                return;

            });



        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.show();
    }
    public static void main(String[] args)
    {
        launch(TicTacToeApplication.class);
    }
}
