package models;



import java.util.HashMap;



public abstract class ObjectModel
{ 
  protected int type;
  
  protected HashMap<String, Integer> fields;
  
  {   
    fields = new HashMap<>();
  }   
  
  public boolean matches(int type) { return this.type == type; }
 
  abstract public HashMap<String, Integer> parse(int[] payload, int offset);
}
