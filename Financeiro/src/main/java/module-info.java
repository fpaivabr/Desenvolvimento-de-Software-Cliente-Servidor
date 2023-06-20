module com.financeiro {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires jasperreports;

    opens com.financeiro.controle to javafx.fxml;
    exports com.financeiro.controle;

    opens com.financeiro.main to javafx.fxml;
    exports com.financeiro.main;

    opens com.financeiro.modelo to javafx.fxml;
    exports com.financeiro.modelo;

    exports com.financeiro.util;
    opens com.financeiro.util to javafx.fxml;
    exports com.financeiro.modelo.enumeradores;
    opens com.financeiro.modelo.enumeradores to javafx.fxml;
}