package droidexpression.assistant;

public class Mathematics {

    public final double PI = 3.141592654;
    public final double E = 2.7182818284590452354;
    private Formulas form = new Formulas();

    public double valueFunction(String function, double value,
                                int unities) {
        double result = Double.POSITIVE_INFINITY;
        if (function.equals("sen") || function.equals("sin")) {
            result = form.sinus(value, unities);
        } else if (function.equals("cos")) {
            result = form.cosine(value, unities);
        } else if (function.equals("tan")) {
            result = form.tangent(value, unities);
        } else if (function.equals("cot")) {
            result = form.cotangent(value, unities);
        } else if (function.equals("sec")) {
            result = form.secant(value, unities);
        } else if (function.equals("csc")) {
            result = form.cosecant(value, unities);
        } else if (function.equals("asen") || function.equals("asin")) {
            result = form.arcSinus(value, unities);
        } else if (function.equals("acos")) {
            result = form.arcCosine(value, unities);
        } else if (function.equals("atan")) {
            result = form.arcTangent(value, unities);
        } else if (function.equals("acot")) {
            result = form.arcCotangent(value, unities);
        } else if (function.equals("asec")) {
            result = form.arcSecant(value, unities);
        } else if (function.equals("acsc")) {
            result = form.arcCosecant(value, unities);
        } else if (function.equals("senh") || function.equals("sinh")) {
            result = form.hyperbolicSinus(value, unities);
        } else if (function.equals("cosh")) {
            result = form.hyperbolicCosine(value, unities);
        } else if (function.equals("tanh")) {
            result = form.hyperbolicTangent(value, unities);
        } else if (function.equals("coth")) {
            result = form.hyperbolicCotangent(value, unities);
        } else if (function.equals("sech")) {
            result = form.hyperbolicSecant(value, unities);
        } else if (function.equals("csch")) {
            result = form.hyperbolicCosecant(value, unities);
        } else if (function.equals("asenh") || function.equals("asinh")) {
            result = form.hyperbolicArcSinus(value, unities);
        } else if (function.equals("acosh")) {
            result = form.hyperbolicArcCosine(value, unities);
        } else if (function.equals("atanh")) {
            result = form.hyperbolicArcTangent(value, unities);
        } else if (function.equals("acoth")) {
            result = form.hyperbolicArcCotangent(value, unities);
        } else if (function.equals("asech")) {
            result = form.hyperbolicArcSecant(value, unities);
        } else if (function.equals("acsch")) {
            result = form.hyperbolicArcCosecant(value, unities);
        } else if (function.startsWith("log")) {
            String val = function.substring(3);
            if (val.equals("")) {
                result = form.logBase10(value);
            } else {
                double base = Double.parseDouble(val);
                result = form.logBN(value, base);
            }
        } else if (function.equals("ln")) {
            result = form.ln(value);
        } else if (function.equals("e")) {
            result = form.e(value);
        } else if (function.equals("sqrt")) {
            result = form.squareRoot(value);
        } else if (function.equals("cbrt")) {
            result = form.cubicRoot(value);
        } else if (function.equals("abs")) {
            result = form.absolute(value);
        } else if (function.equals("gd")) {
            result = form.gudermannian(value, unities);
        } else if (function.equals("agd")) {
            result = form.arcGudermannian(value, unities);
        } else if (function.equals("rnd")) {
            result = form.random(value);
        }
        return result;
    }

}
