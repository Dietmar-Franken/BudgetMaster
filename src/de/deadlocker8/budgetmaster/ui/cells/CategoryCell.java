package de.deadlocker8.budgetmaster.ui.cells;

import java.util.Optional;

import de.deadlocker8.budgetmaster.logic.Category;
import de.deadlocker8.budgetmaster.ui.CategoryController;
import fontAwesome.FontIcon;
import fontAwesome.FontIconType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import tools.ConvertTo;

public class CategoryCell extends ListCell<Category>
{		
	private final double HEIGHT = 40.0;
	private CategoryController categoryController;	
	
	public CategoryCell(CategoryController categoryController)
	{
		super();
		this.categoryController = categoryController;
	}

	@Override
	protected void updateItem(Category item, boolean empty)
	{
		super.updateItem(item, empty);

		if(!empty)
		{		
			HBox hbox = new HBox();
			
			Label labelCircle = new Label(item.getName().substring(0, 1).toUpperCase());
			labelCircle.setPrefWidth(HEIGHT);
			labelCircle.setPrefHeight(HEIGHT);
			labelCircle.setAlignment(Pos.CENTER);
			labelCircle.getStyleClass().add("greylabel");
			String textColor = ConvertTo.toRGBHex(ConvertTo.getAppropriateTextColor(item.getColor()));
			labelCircle.setStyle("-fx-background-color: " + ConvertTo.toRGBHex(item.getColor()) + "; -fx-background-radius: 50%; -fx-text-fill: " + textColor + "; -fx-font-weight: bold; -fx-font-size: 20;");
			hbox.getChildren().add(labelCircle);
			
			Label labelName = new Label(item.getName());
			labelName.setPrefHeight(HEIGHT);
			labelName.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: #212121");
			labelName.setAlignment(Pos.CENTER);
			labelName.getStyleClass().add("greylabel");
			hbox.getChildren().add(labelName);
			HBox.setMargin(labelName, new Insets(0, 0, 0, 20));
			
			Region r = new Region();
			hbox.getChildren().add(r);
			HBox.setHgrow(r, Priority.ALWAYS);
			
			Button buttonEdit = new Button();
			FontIcon iconEdit = new FontIcon(FontIconType.PENCIL);
			iconEdit.setSize(16);
			buttonEdit.setGraphic(iconEdit);
			buttonEdit.setPrefHeight(HEIGHT);					
			buttonEdit.getStyleClass().add("greylabel");
			buttonEdit.setStyle("-fx-background-color: transparent");
			buttonEdit.setOnAction((e)->{
				categoryController.newCategory(true, item);
			});		
			hbox.getChildren().add(buttonEdit);
			HBox.setMargin(buttonEdit, new Insets(0, 0, 0, 25));
			
			Button buttonDelete = new Button();
			FontIcon iconDelete = new FontIcon(FontIconType.TRASH);
			iconDelete.setSize(16);
			buttonDelete.setGraphic(iconDelete);
			buttonDelete.setPrefHeight(HEIGHT);					
			buttonDelete.getStyleClass().add("greylabel");
			buttonDelete.setStyle("-fx-background-color: transparent");
			buttonDelete.setOnAction((event)->{
				 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                 alert.setTitle("Kategorie löschen");
                 alert.setHeaderText("");
                 alert.setContentText("Möchtest du diese Kategorie wirklich unwiderruflich löschen?");
                 Stage dialogStage = (Stage) alert.getDialogPane().getScene().getWindow();
                 dialogStage.getIcons().add(categoryController.getController().getIcon());
                 dialogStage.centerOnScreen();

                 Optional<ButtonType> result = alert.showAndWait();
                 if (result.get() == ButtonType.OK)
                 {
                	 categoryController.deleteCategory(item.getID());
                 }				
			});
			//don't allow category "Übertrag" to be deleted
			if(item.getID() != 2)
			{
				hbox.getChildren().add(buttonDelete);
				HBox.setMargin(buttonDelete, new Insets(0, 0, 0, 5));	
			}
			
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