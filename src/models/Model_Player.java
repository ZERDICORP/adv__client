package models;



import java.util.HashMap;

import constants.ObjectType;



public class Model_Player extends ObjectModel
{
  {
    super.type = ObjectType.PLAYER.ordinal();
  }
  
  @Override
  public HashMap<String, Integer> parse(int[] payload, int offset) throws ArrayIndexOutOfBoundsException
  {
    fields.put("type", super.type);
    fields.put("x", payload[offset + 0]);
    fields.put("y", payload[offset + 1]);
    fields.put("direction", payload[offset + 2]);
		fields.put("id", payload[offset + 3]);
    fields.put("isAlive", payload[offset + 4]);
		fields.put("cartridges", payload[offset + 5]);

    return fields;
  }
}
