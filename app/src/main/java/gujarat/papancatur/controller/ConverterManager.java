package gujarat.papancatur.controller;

import android.util.Log;

/**
 * Created by Gujarat Santana on 02/01/16.
 */
public class ConverterManager {
    private static final String TAG = "ConverterManager";
    public static ConverterManager instance;

    private ConverterManager() {}

    public static ConverterManager getInstance() {
        if (instance == null) instance = new ConverterManager();
        return instance;
    }


    public String[] getCodeChessArray(String source){
        String[] result = source.split(" ");
        Log.i(TAG, "getCodeChessArray: "+result);
        return result;
    }

    public String getChessNameFromString(String codeChess){
        String[] arrayCode = codeChess.split("");
        // get the CheessName
        String result = arrayCode[1];
        Log.i(TAG, "getChessNameFromString: "+result);
        return result;
    }

    public String getPositionChess(String codeChess){
        String[] arrayCode = codeChess.split("");
        // get the CheessName
        String result = arrayCode[2]+arrayCode[3];
        Log.i(TAG, "getPositionChess: "+result);
        return result;
    }


}
