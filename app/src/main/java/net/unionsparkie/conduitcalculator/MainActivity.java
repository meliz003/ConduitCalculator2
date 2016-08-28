package net.unionsparkie.conduitcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static class calc {

        //Method to get inverse sine
        public static double getOffset(int x, double y){
            double multiplier = 1 / Math.sin(Math.toRadians(x));
            return multiplier * y;
        }

        //Method to return a whole number which in this case is in inches
        public static int getInches(double v){
            double decimal = v % 1;
            if (decimal >= .921875){
                return (int) v + 1;

            } else {
                return (int) v;
            }
        }

        //Gets a fraction estimate to an eighth increment
        public static String getFractions(double v){
            double decimal = v % 1;
            if (decimal < .0625){
                return "";
            } else if(decimal >= .0625 && decimal < .171875) {
                return "1/8";
            } else if(decimal >= .171875 && decimal < .296875 ) {
                return "1/4";
            } else if (decimal >= .296875 && decimal < .421875) {
                return "3/8";
            } else if (decimal >= .42185 && decimal < .546875) {
                return "1/2";
            } else if (decimal >=.546875 && decimal < .671875) {
                return "5/8";
            } else if (decimal >= .671875 && decimal < .796875) {
                return "3/4";
            } else if (decimal >= .796875 & decimal < .921875) {
                return "7/8";
            } else {
                return "";
            }
        }
    }


    int angleValue = 0;
    double offsetValue = 0;
    double offsetFraction = 0;
    double result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SeekBar seekAngle = (SeekBar) findViewById(R.id.seekAngle);
        final SeekBar seekOffset = (SeekBar) findViewById(R.id.seekOffset);
        final SeekBar seekFraction = (SeekBar) findViewById(R.id.seekFraction);

        final TextView textAngle = (TextView) findViewById(R.id.textAngle);
        final TextView textOffset = (TextView) findViewById(R.id.textOffset);
        final TextView textFinal = (TextView) findViewById(R.id.textFinal);
        final TextView textFraction = (TextView) findViewById(R.id.textFraction);


        assert textFinal != null;
        textFinal.setText(String.valueOf(calc.getInches(calc.getOffset(angleValue,offsetValue+offsetFraction))) + " " + calc.getFractions(calc.getOffset(angleValue,offsetValue+offsetFraction)) + '"');


        assert seekAngle != null;
        seekAngle.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int p=0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                assert textAngle != null;
                p = progress;
                angleValue = p;
                textAngle.setText(String.valueOf(angleValue));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                assert textAngle != null;
                textAngle.setText(String.valueOf(p));

                angleValue = p;
                int inches = calc.getInches(calc.getOffset(angleValue,offsetValue+offsetFraction));
                String fraction = calc.getFractions(calc.getOffset(angleValue,offsetValue+offsetFraction));

                assert textFinal != null;
                textFinal.setText(String.valueOf(inches)  + "  " + fraction + '"');

            }
        });
        assert seekOffset != null;
        seekOffset.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            int p = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p = progress;
                offsetValue = p;

                int inches = calc.getInches(p);

                assert textOffset != null;
                textOffset.setText(String.valueOf(inches));

            }
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int inches = calc.getInches(p);
                String fraction = calc.getFractions(p);

                String fractionF = calc.getFractions(calc.getOffset(angleValue,offsetValue+offsetFraction));
                int inchesF = calc.getInches(calc.getOffset(angleValue,offsetValue+offsetFraction));

                assert textOffset != null;
                textOffset.setText(String.valueOf(inches));


                assert textFinal != null;
                textFinal.setText(String.valueOf(inchesF)  + "  " + fractionF + '"');
            }
        });

        assert seekFraction != null;
        seekFraction.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            double p = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                p = progress * .125;

                String fraction = calc.getFractions(p);

                assert textFraction != null;
                textFraction.setText(String.valueOf(fraction));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                offsetFraction = p;

                String fractionF = calc.getFractions(calc.getOffset(angleValue,offsetValue+offsetFraction));
                int inchesF = calc.getInches(calc.getOffset(angleValue,offsetValue+offsetFraction));


                textFinal.setText(String.valueOf(inchesF)  + "  " + fractionF + '"');

            }
        });
    }
}
