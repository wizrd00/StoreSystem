package core;

import java.time.LocalDate;

public class Product {
	public String name;
	public String unit;
	public float price;
	public long id;

	private boolean _avail;
	private String _man_name;
	private String _color;
	private String _manufacture_date;
	private String _expiration_date;
	private float _weight; // in grams
	private float _volume; // in liters
	private int _discount; // in percent

	public Product(String name, String unit, float price, long id)
	{
		this.name = name.toLowerCase();
		this.unit = unit;
		this.price = price;
		this.id = id;
	}

	public void setAvailable()
	{
		this._avail = true;
	}

	public boolean getAvailable()
	{
		return _avail;
	}

	public void setManufacturerName(String name)
	{
		this._man_name = name;
	}

	public String getManufacturerName()
	{
		return _man_name;
	}

	public void setColor(String color)
	{
		this._color = color;
	}

	public String getColor()
	{
		return _color;
	}

	public void setManufactureDate(String date)
	{
		this._manufacture_date = date;
	}

	public String getManufactureDate()
	{
		return _manufacture_date;
	}

	public void setExpirationDate(String date)
	{
		this._expiration_date = date;
	}

	public String getExpirationDate()
	{
		return _expiration_date;
	}

	public void setWeight(float weight)
	{
		this._weight = weight;
	}

	public float getWeight()
	{
		return _weight;
	}

	public void setVolume(float volume)
	{
		this._volume = volume;
	}

	public float getVolume()
	{
		return _volume;
	}

	public void setDiscount(int discount)
	{
		this._discount = discount;
	}

	public int getDiscount()
	{
		return _discount;
	}

	public String getSpecification()
	{
		String spec = String.format("Name:%s, Unit:%s, Price:%.2f, ID:%d", name, unit, price, id);
		return spec;
	}
}
