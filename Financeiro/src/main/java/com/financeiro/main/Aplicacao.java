package com.financeiro.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Aplicacao extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        AnchorPane root = FXMLLoader.load(Aplicacao.class.getResource("/com/financeiro/FXML_Aplicacao.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("CONTROLE FINANCEIRO PESSOAL");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}