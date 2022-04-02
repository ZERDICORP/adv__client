package core;



import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import models.ObjectModel;
import models.Model_Init;
import models.Model_Player;
import models.Model_Block;
import models.Model_Enemy;
import models.Model_Game;

import tools.Tools;

import constants.LogMsg;



public class PayloadParser
{
  private List<ObjectModel> models = new ArrayList<>();
  private int offset;
  private int[] payload;

  {
    models.add(new Model_Init());
    models.add(new Model_Player());
    models.add(new Model_Block());
		models.add(new Model_Enemy());
    models.add(new Model_Game());

    offset = 0;
  }

  public PayloadParser(int[] p) { payload = p; }

  public boolean hasNext() { return offset < payload.length; }

  public HashMap<String, Integer> next()
  {
    HashMap<String, Integer> fields = null;

    for (int i = offset; i < payload.length; ++i)
    {
      if (payload[i] == -1)
      {
        for (ObjectModel model : models)
          if (model.matches(payload[offset]))
          {
						try
						{
							fields = model.parse(payload, offset + 1);
						}
						catch (ArrayIndexOutOfBoundsException e)
						{
							Tools.log(LogMsg.FAILED_TO_PARSE_OBJECT);
							e.printStackTrace();
						}
            break;
          }

        offset = i + 1;
        break;
      }
    }

    return fields;
  }
}
