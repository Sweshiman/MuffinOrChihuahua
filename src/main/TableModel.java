package src.main;

/**
 * Created by alexanderkarlsson on 09/10/2018.
 */
public class TableModel {
    private int[] pots = new int[] {0,0,0,0};

    public TableModel(){

    }

    public void setPot(int id, int val){
        pots[id] = val;
    }
}
