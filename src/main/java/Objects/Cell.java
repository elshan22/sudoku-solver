package Objects;

import java.util.ArrayList;
import java.util.List;

public class Cell {

    public int row, column, value;
    public Region region;
    public List<Integer> validValues;

    public Cell(int value, int row, int column, Region region) {
        this.value = value;
        this.row = row;
        this.column = column;
        this.region = region;
        validValues = new ArrayList<>();
        if (value != 0) validValues.add(value);
        else for (int i = 1 ; i <= 9 ; i++) validValues.add(i);
    }

}
