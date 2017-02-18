package de.deadlocker8.budgetmaster.logic;

public class Payment
{	
	private int ID;
	private int amount;
	private String date;
	private int categoryID;
	private String name;	
	private int repeatInterval;
	private String repeatEndDate;
	private int repeatMonthDay;

	public Payment(int ID, int amount, String date, int categoryID, String name, int repeatInterval, String repeatEndDate, int repeatMonthDay)
	{		
		this.ID = ID;		
		this.amount = amount;
		this.date = date;
		this.categoryID = categoryID;
		this.name = name;
		this.repeatInterval = repeatInterval;
		this.repeatEndDate = repeatEndDate;
		this.repeatMonthDay = repeatMonthDay;
	}

	public int getID()
	{
		return ID;
	}
	
	public boolean isIncome()
	{
		return amount > 0;
	}
	
	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	public String getDate()
	{
		return date;
	}

	public void setDate(String date)
	{
		this.date = date;
	}

	public int getCategoryID()
	{
		return categoryID;
	}

	public void setCategoryID(int categoryID)
	{
		this.categoryID = categoryID;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getRepeatInterval()
	{
		return repeatInterval;
	}

	public void setRepeatInterval(int repeatInterval)
	{
		this.repeatInterval = repeatInterval;
	}

	public String getRepeatEndDate()
	{
		return repeatEndDate;
	}

	public void setRepeatEndDate(String repeatEndDate)
	{
		this.repeatEndDate = repeatEndDate;
	}

	public int getRepeatMonthDay()
	{
		return repeatMonthDay;
	}

	public void setRepeatMonthDay(int repeatMonthDay)
	{
		this.repeatMonthDay = repeatMonthDay;
	}
	
	public boolean isRepeating()
	{
		if(repeatInterval != 0)
		{
			return true;
		}
		 
		if(repeatMonthDay != 0)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public String toString()
	{
		return "Payment [ID=" + ID + ", amount=" + amount + ", date=" + date + ", categoryID=" + categoryID + ", name=" + name + ", repeatInterval=" + repeatInterval + ", repeatEndDate=" + repeatEndDate + ", repeatMonthDay=" + repeatMonthDay + "]";
	}
}