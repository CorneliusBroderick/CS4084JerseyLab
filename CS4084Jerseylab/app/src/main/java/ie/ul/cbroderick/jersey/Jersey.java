package ie.ul.cbroderick.jersey;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Cornelius Broderick on 16/10/2018.
 */
public class Jersey {

  private String mPlayerName = "ANDROID";
  private int mPlayerId = 17; 
  private String mJerseyType;
  private int mImageResourceId;
  private Random random = new Random();

  public static final HashMap<String, Integer> sJerseyImageMap;
  static {
    sJerseyImageMap = new HashMap<>();
    sJerseyImageMap.put("Blue BasketBall Jersey", R.drawable.blue_jersey);
    sJerseyImageMap.put("Green Hurling Jersey", R.drawable.green_jersey);
    sJerseyImageMap.put("Purple Hurling Jersey", R.drawable.purple_jersey);
    sJerseyImageMap.put("Blue BasketBall Jersey", R.drawable.red_jersey);

  }

  public Jersey() {
    mJerseyType = getRandomJerseyName();
    mImageResourceId = sJerseyImageMap.get(mJerseyType);
  }

  private String getRandomJerseyName() {
    Object[] jerseys = Jersey.sJerseyImageMap.keySet().toArray();
    return (String)jerseys[random.nextInt(jerseys.length)];
  }

  public String getName() {
    return mPlayerName;
  }


  public int getImageResourceId() {
    return mImageResourceId;
  }
}
