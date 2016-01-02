package gujarat.papancatur.controller;

import android.app.Activity;
import android.widget.ImageView;

/**
 * Created by Gujarat Santana on 02/01/16.
 */
public class PapanCaturManager {
    private static final String TAG = "PapnCaturManager";
    public static PapanCaturManager instance;
    private PapanCaturManager loginManager;

    private PapanCaturManager() {}

    public static PapanCaturManager getInstance() {
        if (instance == null) instance = new PapanCaturManager();
        return instance;
    }

    public int getResourcePositionfromCodePosition(Activity activity, String position){
        int resourceId = activity.getResources().getIdentifier(position, "id", activity.getPackageName());
//        Log.d(TAG, "getResourcePositionfromCodePosition() returned: " + resourceId);
        return resourceId;
    }

    public int getResourceChessDrawableFromChessName(Activity activity, String chessName){
        int drawableResourceId = activity.getResources().getIdentifier(chessName, "drawable", activity.getPackageName());
//        Log.d(TAG, "getResourceChessDrawableFromChessName() returned: " + drawableResourceId);
        return drawableResourceId;
    }

    public void setImageToPosition(Activity activity,int resourceChessDrawable,int resoucePositionChess){
//        Log.d(TAG, "setImageToPosition() called with: " + "activity = [" + activity + "], resourceChessDrawable = [" + resourceChessDrawable + "], resoucePositionChess = [" + resoucePositionChess + "]");
        ImageView chessPosition = (ImageView) activity.findViewById(resoucePositionChess);
        chessPosition.setImageResource(resourceChessDrawable);
//        Picasso.with(activity).load(resourceChessDrawable).memoryPolicy(MemoryPolicy.NO_CACHE).into(chessPosition);

    }

    private void removeImageFromImageView(Activity activity,int resourceChessDrawable,int resoucePositionChess){
        ImageView chessPosition = (ImageView) activity.findViewById(resoucePositionChess);
        chessPosition.setImageResource(0);
        chessPosition.setImageResource(android.R.color.transparent);
    }


    public String getChessNameDrawable(String codeChessName){
        switch (codeChessName){
            case "K":
                return "king_putih";
            case "k":
                return "king_item";
            case "Q":
                return "patih_putih";
            case "q":
                return "patih_item";
            case "B":
                return "kuncung_putih";
            case "b":
                return "kuncung_item";
            case "N":
                return "kuda_putih";
            case "n":
                return "kuda_item";
            case "R":
                return "bom_putih";
            case "r":
                return "bom_item";
            default:
                break;
        }
        return "";
    }


    public void cleanPapanCatur(Activity activity, String currentPosition){
        String [] codeChessArray = ConverterManager.getInstance().getCodeChessArray(currentPosition);

        for(int i=0;i<codeChessArray.length;i++){
            String codeChessName = ConverterManager.getInstance().getChessNameFromString(codeChessArray[i]);
            String postionChess = ConverterManager.getInstance().getPositionChess(codeChessArray[i]);


            String chessNameDrawable = getChessNameDrawable(codeChessName);
            int resourceChessDrawable = getResourceChessDrawableFromChessName(activity, chessNameDrawable);
            int resoucePositionChess = getResourcePositionfromCodePosition(activity,postionChess);

            removeImageFromImageView(activity, resourceChessDrawable,resoucePositionChess);

        }
    }



    public void setChessPosition(Activity activity, String sourcePosition){
        String [] codeChessArray = ConverterManager.getInstance().getCodeChessArray(sourcePosition);

        for(int i=0;i<codeChessArray.length;i++){
            String codeChessName = ConverterManager.getInstance().getChessNameFromString(codeChessArray[i]);
            String postionChess = ConverterManager.getInstance().getPositionChess(codeChessArray[i]);


            String chessNameDrawable = getChessNameDrawable(codeChessName);
            int resourceChessDrawable = getResourceChessDrawableFromChessName(activity, chessNameDrawable);
            int resoucePositionChess = getResourcePositionfromCodePosition(activity,postionChess);



            // set resourceChessDrawable to resoucePositionChess;
            setImageToPosition(activity,resourceChessDrawable,resoucePositionChess);

        }


    }

}
