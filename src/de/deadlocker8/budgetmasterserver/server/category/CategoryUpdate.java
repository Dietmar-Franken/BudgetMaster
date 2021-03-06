package de.deadlocker8.budgetmasterserver.server.category;

import static spark.Spark.halt;

import de.deadlocker8.budgetmasterserver.main.DatabaseHandler;
import javafx.scene.paint.Color;
import spark.Request;
import spark.Response;
import spark.Route;

public class CategoryUpdate implements Route
{
	private DatabaseHandler handler;

	public CategoryUpdate(DatabaseHandler handler)
	{		
		this.handler = handler;
	}

	@Override
	public Object handle(Request req, Response res) throws Exception
	{
		if(!req.queryParams().contains("id") ||!req.queryParams().contains("name") || !req.queryParams().contains("color"))
		{
			halt(400, "Bad Request");
		}	
		
		int id = -1;		
		
		try
		{				
			id = Integer.parseInt(req.queryMap("id").value());
			
			if(id < 0)
			{
				halt(400, "Bad Request");
			}
			
			try
			{				
				handler.updateCategory(id, req.queryMap("name").value(), Color.web("#" + req.queryMap("color").value()));			

				return "";
			}
			catch(IllegalStateException ex)
			{				
				halt(500, "Internal Server Error");
			}
		}		
		catch(Exception e)
		{
			halt(400, "Bad Request");
		}
		
		return "";
	}
}