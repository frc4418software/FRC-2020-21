package frc.robot.teamlibraries;

public class DriveInputPipeline {
    // An array containing all of the values
    // for standard tank drive, 0:left and 1:right
    // for standard arcade drive, 0:forward and 1:turnangle
    private double[] values;

    // The distance from 0 in which an input should be treated as
    // in the deadzone
    private double deadzone = .05;

    // The amplitude of the bell curve
    // How fast the values fall towards 0
    private double magA = 1.5;
    // The width of the bell curve
    // How wide the magnetic range is
    private double magC = 0.1;
    // Where the bell curve crosses the x-axis
    // When to cutoff the bell curve function and switch to just 0
    private double magCutoff;





    public enum InputMapModes {
        IMM_LINEAR, IMM_SQUARE, IMM_CUBE, IMM_S
    }

    // Constructors simply set values
    public DriveInputPipeline(double valueA, double valueB) {
        setValues(valueA, valueB);
        setMagCutoff();
    }

    public DriveInputPipeline(double[] values) {
        setValues(values);
        setMagCutoff();
    }

    // Setters
    public void setValues(double valueA, double valueB) {
        values = new double[]{valueA, valueB};
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    // Set the mag cutoff value
    private void setMagCutoff() {
        // Solving for x in an upside down bell curve equation
        magCutoff = Math.sqrt(-2 * Math.pow(magC, 2) * Math.log(1 / magA));
    }
    
    // Getters
    public double[] getValues() {
        return values;
    }





    // Input map ---------------------------------------------
    
    // Apply a custom curve function to the input
    private double inputMap(double value, InputMapModes inputMapMode) {
        switch(inputMapMode) {
            case IMM_SQUARE: // square the input
                value = Math.pow(value, 2);
                break;
            case IMM_CUBE: // cube the input
                value = Math.pow(value, 3);
                break;
            case IMM_S: // apply an s shaped curve to the input
                // TODO
                break;
            default: // apply no curve
                //DriverStation.reportWarning("Using the default input map for some reason, use a different one or fix this ya big dum dum.", false);
            case IMM_LINEAR: // linear is also the defalt, but, without warning messages
                // nothing happens yo, 1:1 mapping
        }
    
        return value;
    }

    // Allow for two values to be mapped at once using different mappings
    public void inputMapWrapper(InputMapModes inputMapModeFor0, InputMapModes inputMapModeFor1) {
        // apply the map for the first value
        inputMap(values[0], inputMapModeFor0);

        // apply the map for the second value
        inputMap(values[1], inputMapModeFor1);
    }

    // Allow for two values to be mapped at once using the same mapping
    public void inputMapWrapper(InputMapModes inputMapModeForBoth) {
        inputMapWrapper(inputMapModeForBoth, inputMapModeForBoth);
    }



  

    // Deadzones ---------------------------------------------

    // apply a deadzone to the inputs
    public void applyDeadzones() {
        // if the value is within the deadzone set it to 0
        if(Math.abs(values[0]) < deadzone) {
            values[0] = 0;
        }

        // second verse, same as the first
        if(Math.abs(values[1]) < deadzone) {
            values[1] = 0;
        }
    }





    // magnetic inputs ---------------------------------------

    // A fancy function that causes the left and right values to snap to the average
    // when they are close to the average
    public void magnetizeTankDrive() {
        double avg = (values[0] + values[1]) / 2;
        double absDiff = Math.abs(values[0] - values[1]); // The difference between the two inputs
        double magnetism; // how strongly the output is magnetized to a value

        // the function dips below 0
        // if in the part of the function above 0
        if(absDiff > magCutoff) {
            // when the difference between the inputs is great, set magnetism to very nearly 1
            // when the difference is not much, near 0.2, have the magnetism begin fall towards 0 
            magnetism = -magA * Math.pow( Math.E, 
                                            (-Math.pow(absDiff, 2 )) / 
                                            (2 * Math.pow( magC, 2 )) ) + 1;
        } else { // else, in the part of the function below 0
            // set the magnetism to 0
            // this creates a small zone in which the magnetism will be 0
        magnetism = 0;
        }

        // the output is attracted to the input if the magnetism is near 1
        // the output is attracted to the average if the magnetism is near 0
        values[0] = values[0]*magnetism + avg*(1-magnetism);
        values[1] = values[1]*magnetism + avg*(1-magnetism);
    }





    // Arcade drive? -----------------------------------------

    // An implementation of arcade drive
    // I have no idea how this works
    public double[] convertArcadeDriveToTank(double[] values) {
        double fwd = values[0];
        double rcw = values[1];
        double left;
        double right;
        double a = 0;
        double b = 1;

        if(fwd >= 0) {
            if(rcw >= 0) {
                left = convertArcadeDriveToTankL(fwd, rcw, a, b);
                right = convertArcadeDriveToTankR(fwd, rcw, a, b);
            } else {
                left = convertArcadeDriveToTankR(fwd, -rcw, a, b);
                right = convertArcadeDriveToTankL(fwd, -rcw, a, b);
            }
        } else {
            if(rcw >= 0) {
                left = -convertArcadeDriveToTankR(-fwd, rcw, a, b);
                right = -convertArcadeDriveToTankL(-fwd, rcw, a, b);
            } else {
                left = -convertArcadeDriveToTankL(-fwd, -rcw, a, b);
                right = -convertArcadeDriveToTankR(-fwd, -rcw, a, b);
            }
        }

        return new double[]{left, right};
    }

    private double convertArcadeDriveToTankL(double fwd, double rcw, double a, double b) {
        return fwd + b*rcw*(1-fwd);
    }

    private double convertArcadeDriveToTankR(double fwd, double rcw, double a, double b) {
        return fwd - b*rcw + fwd*rcw*(b-a-1);
    }
}