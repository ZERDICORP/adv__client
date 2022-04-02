package models;



import java.util.HashMap;

import constants.ObjectType;



public class Model_Init extends ObjectModel
{
  {
    super.type = ObjectType.INIT.ordinal();
  }
  
  @Override
  public HashMap<String, Integer> parse(int[] payload, int offset) throws ArrayIndexOutOfBoundsException
  {
    fields.put("type", super.type);
    fields.put("id", payload[offset + 0]);
    fields.put("mapWidth", payload[offset + 1]);
    fields.put("mapHeight", payload[offset + 2]);
		fields.put("livingSectorWidth", payload[offset + 3]);
		fields.put("livingSectorHeight", payload[offset + 4]);

    return fields;
  }
}
