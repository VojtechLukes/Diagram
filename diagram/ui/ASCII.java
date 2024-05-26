package ui;

/**
 * Třída pro vytvoření ASCII na začátku programu.
 */
public class ASCII {

    /**
     * Metoda pro generaci ASCII na začátku programu.
     *
     * @return ASCII ve formě Stringu.
     */
    public static String printStartOfProgram() {
        String chessAscii
                = """
               .::.
               _::_
             _/____\\_        ()
             \\      /      <~-->
              \\____/        \\__/         <>_
              (____)       (____)      (\\)  )                        __/""\\
               |  |         |  |        \\__/      WWWWWW            ]___ 0  }
               |__|         |  |       (____)      |  |       __        /   }
              /    \\        |__|        |  |       |  |      (  )     /~    }
             (______)      /____\\       |__|       |__|       ||      \\____/
            (________)    (______)     /____\\     /____\\     /__\\     /____\\
            /________\\   (________)   (______)   (______)   (____)   (______)""" + "\n\n";
        return chessAscii;
    }
}
