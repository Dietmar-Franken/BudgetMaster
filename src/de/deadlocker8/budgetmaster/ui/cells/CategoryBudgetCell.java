package de.deadlocker8.budgetmaster.ui.cells;

import de.deadlocker8.budgetmaster.logic.CategoryBudget;
import de.deadlocker8.budgetmaster.logic.Helpers;
import de.deadlocker8.budgetmaster.ui.HomeController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import tools.ConvertTo;

public class CategoryBudgetCell extends ListCell<CategoryBudget>
{
	private final double HEIGHT = 40.0;	
	private HomeController homeController;
	
	public CategoryBudgetCell(HomeController homeController)
	{
		super();
		this.homeController = homeController;
	}		

	@Override
	protected void updateItem(CategoryBudget item, boolean empty)
	{
		super.updateItem(item, empty);

		if(!empty)
		{
			String name = item.getName();
			if(item.getName().equals("NONE"))
			{
				name = "Keine Kategorie";
			}
			
			HBox hbox = new HBox();

			Label labelCircle = new Label(name.substring(0, 1).toUpperCase());
			labelCircle.setPrefWidth(HEIGHT);
			labelCircle.setPrefHeight(HEIGHT);
			labelCircle.setAlignment(Pos.CENTER);
			labelCircle.getStyleClass().add("greylabel");
			String textColor = ConvertTo.toRGBHex(ConvertTo.getAppropriateTextColor(item.getColor()));
			labelCircle.setStyle("-fx-background-color: " + ConvertTo.toRGBHex(item.getColor()) + "; -fx-background-radius: 50%; -fx-text-fill: " + textColor + "; -fx-font-weight: bold; -fx-font-size: 20;");
			hbox.getChildren().add(labelCircle);

			Label labelName = new Label(name);
			labelName.setPrefHeight(HEIGHT);
			labelName.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: #212121");
			labelName.setAlignment(Pos.CENTER);
			labelName.getStyleClass().add("greylabel");
			hbox.getChildren().add(labelName);
			HBox.setMargin(labelName, new Insets(0, 0, 0, 20));

			Region r = new Region();
			hbox.getChildren().add(r);
			HBox.setHgrow(r, Priority.ALWAYS);

			Label labelBudget = new Label(String.valueOf(Helpers.NUMBER_FORMAT.format(item.getBudget() / 100.0)).replace(".", ",") + " " + homeController.getController().getSettings().getCurrency());
			labelBudget.setPrefHeight(HEIGHT);
			labelBudget.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: #212121;");
			labelBudget.setAlignment(Pos.CENTER);
			labelBudget.getStyleClass().add("greylabel");
			hbox.getChildren().add(labelBudget);
			HBox.setMargin(labelBudget, new Insets(0, 0, 0, 20));

			hbox.setPadding(new Insets(10));
			setStyle("-fx-background: transparent; -fx-border-color: #545454; -fx-border-width: 0 0 1 0");
			setGraphic(hbox);
			setAlignment(Pos.CENTER);
		}
		else
		{
			setStyle("-fx-background: transparent");
			setText(null);
			setGraphic(null);
		}
	}
}