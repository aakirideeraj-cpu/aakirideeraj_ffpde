public class FuelFraudDetection {

    public static void main(String[] args) {

        float tank = 100;
        float ecu = 118;
        float expected = 120;

        float mismatchTank = Math.abs(expected - tank);
        float mismatchECU = Math.abs(expected - ecu);

        if(mismatchTank > 5 || mismatchECU > 5){
            System.out.println("FRAUD DETECTED");
        }
        else{
            System.out.println("NORMAL");
        }
    }
}