import Controllers.ProgramController;
import Controllers.Variations.Variation;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Variation.loadVariations();
        ProgramController.run();
    }
}
