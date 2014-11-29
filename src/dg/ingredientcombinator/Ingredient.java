package dg.ingredientcombinator;

import java.util.ArrayList;
import android.util.Log;

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
		//System.out.println(m_name);
		Log.d("printing", "Ingredient name: " + m_name);
		for (int i=0; i<m_attrs.size(); i++)
		{
			//System.out.println(m_attrs.get(i));
			Log.d("printing", "Ingredient attribute " + i + ": " + m_attrs.get(i));
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ingredient) {
			Ingredient other = (Ingredient)obj;
			if (!m_name.equals(other.getName()) || (m_attrs.size() != other.getAttrs().size())) {
				return false;
			}
			
			ArrayList<String> other_attrs = other.getAttrs();
			
			for (int i=0; i<m_attrs.size(); i++) {
				if (!m_attrs.get(i).equals(other_attrs.get(i))) {
					return false;
				}
			}
			
			return true;
		}
		
		return false;
	}
}
