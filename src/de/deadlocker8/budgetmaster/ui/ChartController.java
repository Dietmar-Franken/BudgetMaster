package de.deadlocker8.budgetmaster.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class ChartController implements Refreshable
{
	@FXML private AnchorPane anchorPaneMain;

	private Controller controller;

	public void init(Controller controller)
	{
		this.controller = controller;

		anchorPaneMain.setStyle("-fx-background-color: #F4F4F4;");
	}

	@Override
	public void refresh()
	{
		//TODO Auto-generated method stub
		
	}
}