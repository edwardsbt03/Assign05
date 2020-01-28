package mooc.vandy.java4android.gate.logic;

import java.util.Random;

import mooc.vandy.java4android.gate.ui.OutputInterface;

/**
 * This class uses your Gate class to fill the corral with snails.  We
 * have supplied you will the code necessary to execute as an app.
 * You must fill in the missing logic below.
 */
public class FillTheCorral {
    /**
     * Reference to the OutputInterface.
     */
    private OutputInterface mOut;

    /**
     * Constructor initializes the field.
     */
    FillTheCorral(OutputInterface out) {
        mOut = out;
    }

    // TODO -- Fill your code in here

    public void setCorralGates(Gate[] gate, Random selectDirection) {
        for (int i = 0; i < gate.length; i++) {

            //generate 0-2 for a range of the three gate states
            int setGate = selectDirection.nextInt(3);
            if (setGate == 0) {
                // open the gate out
                gate[i].open(Gate.OUT);
            } else if (setGate == 1) {
                // open the gate in
                gate[i].open(Gate.IN);
            } // else leave the gate closed
        }
    }

    public boolean anyCorralAvailable(Gate[] corral) {
        for (int i = 0; i < corral.length; i++) {
            if (corral[i].getSwingDirection() == Gate.IN) {
                // if the selected gate is in, terminate the loop
                i += corral.length;
                return true;
            }
        }
        return false;
    }

    public int corralSnails(Gate[] corral, Random rand) {
        for (int i = 0; i < corral.length; i++) {
            // print our gates and their swing states
            mOut.println("Gate "+i+": "+corral[i].toString());
        }

        // declare our initial number of snails in the pasture and our attempts count
        int pasture  = 5;
        int attempts = 1;

        while (pasture != 0) {
            // generate a random number of snails trying to enter a gate
            int in = rand.nextInt(pasture+1);
            // select a random corral
            int corralNumber = rand.nextInt(corral.length);
            if (corral[corralNumber].getSwingDirection() == Gate.IN) {
                // if the selected corral has an in gate
                // remove them from the count in the pasture and increment attempts
                pasture -= in;
                mOut.println(in+" are trying to move through corral "+corralNumber);
                attempts++;
            } else if (corral[corralNumber].getSwingDirection() == Gate.OUT) {
                // if the selected corral has an out gate
                // add them to the count in the pasture and increment attempts
                pasture += in;
                mOut.println(in+" are trying to move through corral "+corralNumber);
                attempts++;
            } else {
                // if the selected corral has a closed gate
                // just print they tried to enter and increment attempts
                mOut.println(in+" are trying to move through corral "+corralNumber);
                attempts++;
            }
        }
        // print the amount of attempts and return attempts
        mOut.println("It took "+attempts+" attempts to corral all of the snails.");
        return attempts;
    }

}
