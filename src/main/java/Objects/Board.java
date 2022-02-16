package Objects;

public class Board {

    public Cell[][] cells;
    public Region[] regions;

    public Board() {
        cells = new Cell[9][9];
        regions = new Region[9];
        for (int i = 0 ; i < 9 ; i++)
            regions[i] = new Region();
    }

    public void singleElimination() {
        updateValidity();
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0 ; i < 9 ; i++)
                for (int j = 0 ; j < 9 ; j++) {
                    if (cells[i][j].validValues.size() == 1) {
                        flag = true;
                        cells[i][j].value = cells[i][j].validValues.get(0);
                        removeValue(cells[i][j]);
                    }
                }
        }
    }

    public void nakedSingle() {
        int[] rowMap = new int[9];
        int[] rowIndex = new int[9];
        int[] colMap = new int[9];
        int[] colIndex = new int[9];
        int[] regMap = new int[9];
        int[] regIndex = new int[9];
        for (int i = 0 ; i < 9 ; i++) {
            for (int x = 0 ; x < 9 ; x++) {
                rowMap[x] = 0;
                colMap[x] = 0;
                regMap[x] = 0;
                rowIndex[x] = -1;
                colIndex[x] = -1;
                regIndex[x] = -1;
            }
            for (int j = 0 ; j < 9 ; j++) {
                if (cells[i][j].value == 0)
                    for (int num: cells[i][j].validValues) {
                        rowMap[num - 1]++;
                        rowIndex[num - 1] = j;
                    }
                if (cells[j][i].value == 0)
                    for (int num: cells[j][i].validValues) {
                        colMap[num - 1]++;
                        colIndex[num - 1] = j;
                    }
                if (regions[i].cells[j].value == 0)
                    for (int num: regions[i].cells[j].validValues) {
                        regMap[num - 1]++;
                        regIndex[num - 1] = j;
                    }
            }
            for (int x = 0 ; x < 9 ; x++) {
                if (rowMap[x] == 1) {
                    cells[i][rowIndex[x]].value = x + 1;
                    removeValue(cells[i][rowIndex[x]]);
                }
                if (colMap[x] == 1) {
                    cells[colIndex[x]][i].value = x + 1;
                    removeValue(cells[colIndex[x]][i]);
                }
                if (regMap[x] == 1) {
                    regions[i].cells[regIndex[x]].value = x + 1;
                    removeValue(regions[i].cells[regIndex[x]]);
                }
            }
        }
    }

    public void removeInvalids(Cell cell) {
        for (int i = 0 ; i < 9 ; i++) {
            cell.validValues.remove((Integer) cells[cell.row][i].value);
            cell.validValues.remove((Integer) cells[i][cell.column].value);
            cell.validValues.remove((Integer) cell.region.cells[i].value);
        }
    }

    public void removeValue(Cell cell) {
        for (int i = 0 ; i < 9 ; i++) {
            cells[cell.row][i].validValues.remove((Integer) cell.value);
            cells[i][cell.column].validValues.remove((Integer) cell.value);
            cell.region.cells[i].validValues.remove((Integer) cell.value);
        }
    }


    public void updateValidity() {
        for (int i = 0 ; i < 9 ; i++)
            for (int j = 0 ; j < 9 ; j++) {
                Cell cell = cells[i][j];
                if (cell.validValues.size() != 1) removeInvalids(cell);
            }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < 9 ; i++) {
            sb.append("|");
            for (int j = 0 ; j < 9 ; j++)
                sb.append(cells[i][j].value).append("|");
            sb.append("\n");
        }
        return sb.toString();
    }
}
