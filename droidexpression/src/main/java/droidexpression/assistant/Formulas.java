package droidexpression.assistant;

import droidexpression.Evaluate;

public class Formulas {

    public final double PI = 3.141592654;
    public final double E = 2.7182818284590452354;

    public double sinus(double x, int unities) {
        if (unities == Evaluate.DEGREES) {
            x = toRadians(x);
        }
        double functionVal = 0;
        functionVal = Math.sin(x);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double cosine(double x, int unities) {
        if (unities == Evaluate.DEGREES) {
            x = toRadians(x);
        }
        double functionVal = 0;
        functionVal = Math.cos(x);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double tangent(double x, int unities) {
        if (unities == Evaluate.DEGREES) {
            x = toRadians(x);
        }
        double functionVal = 0;
        functionVal = sinus(x, unities) / cosine(x, unities);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double cotangent(double x, int unities) {
        if (unities == Evaluate.DEGREES) {
            x = toRadians(x);
        }
        double functionVal;
        functionVal = cosine(x, unities) / sinus(x, unities);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double secant(double x, int unities) {
        if (unities == Evaluate.DEGREES) {
            x = toRadians(x);
        }
        double functionVal = 0;
        functionVal = 1 / cosine(x, unities);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double cosecant(double x, int unities) {
        if (unities == Evaluate.DEGREES) {
            x = toRadians(x);
        }
        double functionVal = 0;
        functionVal = 1 / sinus(x, unities);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    //In arc functions x is a radian number
    public double arcSinus(double x, int unities) {
        double functionVal = 0;
        if (x >= -1 && x <= 1) {
            functionVal = Math.asin(x);
            if (unities == Evaluate.DEGREES) {
                functionVal = toDegrees(functionVal);
            }
        } else {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }

        return functionVal;
    }

    public double arcCosine(double x, int unities) {
        double functionVal = 0;
        if (x >= -1 && x <= 1) {
            functionVal = Math.acos(x);
            if (unities == Evaluate.DEGREES) {
                functionVal = toDegrees(functionVal);
            }
        } else {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double arcTangent(double x, int unities) {
        double functionVal = 0;
        functionVal = Math.atan(x);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (unities == Evaluate.DEGREES) {
            functionVal = toDegrees(functionVal);
        }
        return functionVal;
    }

    public double arcCotangent(double x, int unities) {
        double functionVal = 0;
        if (unities == Evaluate.RADIANS) {
            functionVal = (PI / 2) - arcTangent(x, unities);
        } else if (unities == Evaluate.DEGREES) {
            functionVal = 90 - arcTangent(x, unities);
        }
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double arcSecant(double x, int unities) {
        double functionVal = 0;
        if (absolute(x) >= 1) {///x>1 0 x<-1
            functionVal = arcCosine(1 / x, unities);
        } else {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double arcCosecant(double x, int unities) {
        double functionVal = 0;
        if (absolute(x) >= 1) {///x>1 0 x<-1
            functionVal = arcSinus(1 / x, unities);
        } else {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double hyperbolicSinus(double x, int unities) {
        if (unities == Evaluate.DEGREES) {
            x = toRadians(x);
        }
        double functionVal = 0;
        functionVal = Math.sinh(x);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double hyperbolicCosine(double x, int unities) {
        if (unities == Evaluate.DEGREES) {
            x = toRadians(x);
        }
        double functionVal = 0;
        functionVal = Math.cosh(x);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double hyperbolicTangent(double x, int unities) {
        double functionVal = 0;
        functionVal = (hyperbolicSinus(x, unities)) / (hyperbolicCosine(x, unities));
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double hyperbolicCotangent(double x, int unities) {
        double functionVal = 0;
        functionVal = (hyperbolicCosine(x, unities)) / (hyperbolicSinus(x, unities));
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double hyperbolicSecant(double x, int unities) {
        double functionVal = 0;
        functionVal = 1 / (hyperbolicCosine(x, unities));
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double hyperbolicCosecant(double x, int unities) {
        double functionVal = 0;
        functionVal = 1 / (hyperbolicSinus(x, unities));
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double hyperbolicArcSinus(double x, int unities) {
        double functionVal = 0;
        functionVal = ln(x + squareRoot(pow(x, 2) + 1));
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (unities == Evaluate.DEGREES) {
            functionVal = toDegrees(functionVal);
        }
        return functionVal;
    }

    public double hyperbolicArcCosine(double x, int unities) {
        double functionVal = 0;
        if (x >= 1) {
            functionVal = ln(x + squareRoot(pow(x, 2) - 1));
        } else {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (unities == Evaluate.DEGREES) {
            functionVal = toDegrees(functionVal);
        }
        return functionVal;
    }

    public double hyperbolicArcTangent(double x, int unities) {
        double functionVal = 0;
        if (pow(x, 2) < 1) {
            functionVal = (0.5) * ln((1 + x) / (1 - x));
        } else {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (unities == Evaluate.DEGREES) {
            functionVal = toDegrees(functionVal);
        }
        return functionVal;
    }

    public double hyperbolicArcCotangent(double x, int unities) {
        double functionVal = 0;
        if (pow(x, 2) > 1) {
            functionVal = (0.5) * ln((x + 1) / (x - 1));
        } else {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (unities == Evaluate.DEGREES) {
            functionVal = toDegrees(functionVal);
        }
        return functionVal;
    }

    public double hyperbolicArcSecant(double x, int unities) {
        double functionVal = 0;
        if (x > 0 && x <= 1) {
            functionVal = ln((1 / x) + squareRoot((1 / pow(x, 2)) - 1));
        } else {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (unities == Evaluate.DEGREES) {
            functionVal = toDegrees(functionVal);
        }
        return functionVal;
    }

    public double hyperbolicArcCosecant(double x, int unities) {
        double functionVal = 0;
        if (pow(x, 2) > 0) {
            functionVal = ln((1 / x) + squareRoot((1 / pow(x, 2)) + 1));
        } else {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (unities == Evaluate.DEGREES) {
            functionVal = toDegrees(functionVal);
        }
        return functionVal;
    }

    public double gudermannian(double x, int unities) {
        double functionVal = 0;
        functionVal = arcTangent(hyperbolicSinus(x, unities), unities);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double arcGudermannian(double x, int unities) {
        double functionVal = 0;
        functionVal = hyperbolicArcSinus(tangent(x, unities), unities);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double ln(double x) {
        double functionVal = 0;
        if (x > 0) {
            functionVal = Math.log(x);
        } else {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double logBase10(double x) {
        double functionVal = 0;
        if (x > 0) {
            functionVal = Math.log10(x);
        } else {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double logBN(double x, double base) {
        double functionVal = 0;
        if (x > 0) {
            functionVal = (ln(x)) / (ln(base));
        } else {
            functionVal = Double.POSITIVE_INFINITY;
        }
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double e(double x) {
        double functionVal = 0;
        functionVal = Math.pow(E, x);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double pow(double number, double pow) {
        double functionVal = 0;
        functionVal = Math.pow(number, pow);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double squareRoot(double x) {
        double functionVal = 0;
        functionVal = Math.sqrt(x);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double cubicRoot(double x) {
        double functionVal = 0;
        functionVal = Math.cbrt(x);
        if (Double.isNaN(functionVal)) {
            functionVal = Double.POSITIVE_INFINITY;
        }
        return functionVal;
    }

    public double absolute(double value) {
        double abs = 0;
        if (value >= 0) {
            abs = value;
        } else {
            abs = -value;
        }
        if (Double.isNaN(abs)) {
            abs = Double.POSITIVE_INFINITY;
        }
        return abs;
    }

    public double toRadians(double degree) {
        double radians = 0;
        radians = (PI * degree) / 180;
        if (Double.isNaN(radians)) {
            radians = Double.POSITIVE_INFINITY;
        }
        return radians;
    }

    public double toDegrees(double radians) {
        double degree = 0;
        degree = (radians * 180) / PI;
        if (Double.isNaN(degree)) {
            degree = Double.POSITIVE_INFINITY;
        }
        return degree;
    }

    public double random(double x) {
        return x * Math.random();
    }

}
