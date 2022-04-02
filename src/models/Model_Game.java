package models;



import java.util.HashMap;

import constants.ObjectType;



public class Model_Game extends ObjectModel
{
  {
    super.type = ObjectType.GAME.ordinal();
  }
  
  @Override
  public HashMap<String, Integer> parse(int[] payload, int offset) throws ArrayIndexOutOfBoundsException
  {
    fields.put("type", super.type);
    fields.put("players", payload[offset + 0]);
		fields.put("timeToRebirth", payload[offset + 1]);
		fields.put("blocks", payload[offset + 2]);
		fields.put("timeToReload", payload[offset + 4]);
		fields.put("timeToNewWave", payload[offset + 5]);

    return fields;
  }
}
