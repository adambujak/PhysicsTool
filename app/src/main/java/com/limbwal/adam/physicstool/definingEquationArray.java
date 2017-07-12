package com.limbwal.adam.physicstool;

/**
 * Created by Adam on 3/30/2017.
*
public class definingEquationArray {
    static int unitNum = 3;
    static int equationNum= 9;
    static int variants = 1;
    static int writtenEquation = 1;
    static int picVariant = 1;
    public static String[][][][][] equations = new String[unitNum][equationNum][writtenEquation][variants][picVariant];
    String[] unitItems = {"Kinematics", "Forces", "Waves"};
    String[] kinematics = {"Acceleration-Time-Velocity", "Displacement-Time-Acceleration-Velocity", "Unit 3"};
    public static String[][][][][] main(){

        return equations;
    }
    public static String[][][][][] firstLayer(String[][][][][] array, String[] firstLayerTerms, int n){
        if (n>0){
            for (int cnt = 0; cnt<100; cnt++){
                try {
                    for (int cnt1 = 0; cnt1 < 100; cnt1++) {
                        try {
                            for (int cnt2 = 0; cnt2 < 100; cnt2++) {
                                try {
                                    for (int cnt3 = 0; cnt3 < 100; cnt3++) {
                                        try {
                                            array[n][cnt][cnt1][cnt2][cnt3]=
                                        }
                                        catch (ArrayIndexOutOfBoundsException e){
                                            break;
                                        }
                                    }
                                }
                                catch (ArrayIndexOutOfBoundsException e){
                                    break;
                                }
                            }
                        }
                        catch (ArrayIndexOutOfBoundsException e){
                            break;
                        }
                    }
                }
                catch (ArrayIndexOutOfBoundsException e){
                    break;
                }
            }

         return (array, firstLayerTerms, n);
        }
        else {
            return array;
        }
    }
*/

