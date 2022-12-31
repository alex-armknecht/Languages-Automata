import java.util.List;

public record Quaternion(double a, double b, double c, double d) {   
    public static final Quaternion I = new Quaternion(0, 1, 0, 0);
    public static final Quaternion J = new Quaternion(0, 0, 1, 0);
    public static final Quaternion K = new Quaternion(0, 0, 0, 1);
    public static final Quaternion ZERO = new Quaternion(0, 0, 0, 0);

    public Quaternion {
        if (Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c) || Double.isNaN(d)) {
            throw new IllegalArgumentException();
        }  
    }

    public Quaternion plus(Quaternion otherQuaternion){
        double coefA = this.a + otherQuaternion.a;
        double coefB = this.b + otherQuaternion.b;
        double coefC = this.c + otherQuaternion.c;
        double coefD = this.d + otherQuaternion.d;
        return new Quaternion(coefA, coefB, coefC, coefD);
    }

    public Quaternion minus(Quaternion otherQuaternion){
        double coefA = this.a - otherQuaternion.a;
        double coefB = this.b - otherQuaternion.b;
        double coefC = this.c - otherQuaternion.c;
        double coefD = this.d - otherQuaternion.d;
        return new Quaternion(coefA, coefB, coefC, coefD);
    }

    public Quaternion times(Quaternion otherQuaternion){
        double coefA = this.a * otherQuaternion.a 
            - this.b * otherQuaternion.b
            - this.c * otherQuaternion.c
            - this.d * otherQuaternion.d;
        double coefB = this.a * otherQuaternion.b
            + this.b * otherQuaternion.a 
            + this.c * otherQuaternion.d 
            - this.d * otherQuaternion.c;
        double coefC = this.a * otherQuaternion.c 
            - this.b * otherQuaternion.d 
            + this.c * otherQuaternion.a 
            + this.d * otherQuaternion.b;
        double coefD = this.a * otherQuaternion.d 
            + this.b * otherQuaternion.c 
            - this.c * otherQuaternion.b 
            + this.d * otherQuaternion.a;
        return new Quaternion(coefA, coefB, coefC, coefD);
    }

    public List<Double> coefficients(){
        return List.of(this.a, this.b, this.c, this.d);
    }

    public String toString() {
        return "Quaternion[a=" 
        + this.a 
        + ", b=" 
        + this.b
        + ", c="
        + this.c
        + ", d="
        + this.d
        + "]";
    }
}