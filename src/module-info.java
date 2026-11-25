module COMP228_Lab6_Yiming_Ding {
	requires javafx.controls;
	requires java.desktop;
	requires javafx.graphics;
	requires java.sql;
	requires javafx.base;
	requires javafx.media;
	
	opens application to javafx.graphics, javafx.fxml;
}
