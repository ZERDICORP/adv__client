package models;



import java.util.HashMap;

import constants.ObjectType;



public class Model_Block extends ObjectModel
{
  {
    super.type = ObjectType.BLOCK.ordinal();
  }
  
  @Override
  public HashMap<String, Integer> parse(int[] payload, int offset) throws ArrayIndexOutOfBoundsException
  {
    fields.put("type", super.type);
    fields.put("x", payload[offset + 0]);
    fields.put("y", payload[offset + 1]);
		fields.put("strength", payload[offset + 2]);

    return fields;
  }
}
