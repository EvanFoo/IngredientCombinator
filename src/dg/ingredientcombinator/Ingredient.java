package dg.ingredientcombinator;

import java.util.ArrayList;

public class Ingredient 
{
	private String m_name = new String();
	private ArrayList<String> m_attrs = new ArrayList<String>();
	
	public Ingredient(ArrayList<String> attrs)
	{
		m_name = attrs.get(0);
		for (int i=1; i<attrs.size(); i++) {
			m_attrs.add(attrs.get(i));
		}
	}
	
	public String getName()
	{
		return m_name;
	}
	
	public ArrayList<String> getAttrs() { 
		return m_attrs;
	}
	
	public void printStuffs() {
		System.out.println(m_name);
		for (int i=0; i<m_attrs.size(); i++)
		{
			System.out.println(m_attrs.get(i));
		}
	}
}
