package core;

import java.time.LocalDate;

public class Product {
	public String name;
	public float price;
	public long id;
	public Unit unit;

	private String _man_name;
	private String _color;
	private float _weight;
	private float _volume;
	private int _discount;
	private LocalDate _manufacturing_date;
	private LocalDate _expiration_date

	public void Product(String name, float price, long id, int count)
	{
		this.name = name.toLowerCase();
		this.price = price;
		this.id = id;
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

	public void setManufactureDate(LocalDate date)
	{
		this._manufacturer_date = date
	}

	public LocalDate getManufactureDate()
	{
		return _manufacture_date;
	}

	public void setExpirationDate(LocalDate date)
	{
		this._expiration_date = date
	}

	public LocalDate getExpirationDate()
	{
		return _expiration_date;
	}
}
